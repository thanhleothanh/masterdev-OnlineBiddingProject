package com.ghtk.onlinebiddingproject.controllers;

import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import com.ghtk.onlinebiddingproject.models.dtos.BidDto;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Bid;
import com.ghtk.onlinebiddingproject.models.entities.Item;
import com.ghtk.onlinebiddingproject.models.requests.AuctionRequestDto;
import com.ghtk.onlinebiddingproject.models.requests.BidRequestDto;
import com.ghtk.onlinebiddingproject.models.responses.AuctionPagingResponse;
import com.ghtk.onlinebiddingproject.models.responses.AuctionPagingResponseDto;
import com.ghtk.onlinebiddingproject.models.responses.AuctionTopTrendingDto;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.*;
import com.ghtk.onlinebiddingproject.utils.HttpHeadersUtils;
import com.ghtk.onlinebiddingproject.utils.converters.DtoToEntityConverter;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
import net.kaczmarzyk.spring.data.jpa.domain.LessThan;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * @PreAuthorize để kiểm tra role user trong cookie, nếu không có cookie đăng nhập từ trước thì throw, nếu có role đúng thì mới cho access controller path này
 * có thể dùng @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')") nếu muốn hoặc admin hoặc user có thể access
 * */

@RestController
@RequestMapping(path = "api/v1/auctions")
public class AuctionController {
    @Autowired
    private WebSocketServiceImpl webSocketService;
    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private AuctionServiceImpl auctionService;
    @Autowired
    private AuctionUserServiceImpl auctionUserService;
    @Autowired
    private BidServiceImpl bidService;
    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    /*
     * Get auctions api cho Home Screen
     * Không yêu cầu đăng nhập
     * chỉ lấy các bài đấu giá OPENING
     * có thể sort theo price, time
     * phân trang
     * */

    @GetMapping("")
    public ResponseEntity<CommonResponse> get(
            @Join(path = "category", alias = "c")
            @And({
                    @Spec(path = "status", params = "status", spec = Equal.class, constVal = "OPENING"),
                    @Spec(path = "c.id", params = "categoryId", spec = Equal.class),
                    @Spec(path = "priceStart", params = "priceStartGt", spec = GreaterThan.class),
                    @Spec(path = "priceStart", params = "priceStartLt", spec = LessThan.class),
                    @Spec(path = "timeStart", params = "timeStartGt", spec = GreaterThan.class),
                    @Spec(path = "timeStart", params = "timeStartLt", spec = LessThan.class),
                    @Spec(path = "highestPrice", params = "highestPriceGt", spec = GreaterThan.class),
                    @Spec(path = "highestPrice", params = "highestStartLt", spec = LessThan.class),
            }) Specification<Auction> spec,
            Sort sort,
            @RequestHeader HttpHeaders headers) {
        AuctionPagingResponse pagingResponse = auctionService.get(spec, headers, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<AuctionDto> auctionDto = entityToDtoConverter.convertToListAuctionDto(pagingResponse.getAuctions());

        AuctionPagingResponseDto dtoResponse = entityToDtoConverter.convertToDto(pagingResponse);
        dtoResponse.setAuctions(auctionDto);
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpHeadersUtils.returnHttpHeaders(pagingResponse), HttpStatus.OK);
    }

    @GetMapping("/topTrending")
    public ResponseEntity<CommonResponse> getTopTrending() {
        List<AuctionTopTrendingDto> auctionsDto = auctionService.getTopTrending();

//        List<AuctionDto> dtoResponse = entityToDtoConverter.convertToListAuctionDto(auctions);
        CommonResponse response = new CommonResponse(true, "Success", auctionsDto, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Get auctions api cho current user
     * có thể lọc theo status
     * */

    @GetMapping("/myAuctions")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> getMyAuctions(@RequestParam(name = "status", required = false) AuctionStatusConstants status) {
        List<Auction> auctions = auctionService.getMyAuctions(status);

        List<AuctionDto> dtoResponse = entityToDtoConverter.convertToListAuctionDto(auctions);
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> getById(@PathVariable(value = "id") Integer id) {
        AuctionDto dtoResponse = entityToDtoConverter.convertToDto(auctionService.getById(id));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> save(@Valid @RequestBody AuctionRequestDto auctionDto) {
        Auction auction = dtoToEntityConverter.convertToEntity(auctionDto);
        Item item = dtoToEntityConverter.convertToEntity(auctionDto.getItem());
        Auction newAuction = auctionService.save(auctionDto, auction, item);

        AuctionDto dtoResponse = entityToDtoConverter.convertToDto(newAuction);
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> put(@PathVariable("id") int id, @Valid @RequestBody AuctionRequestDto auctionDto) {
        Auction auction = auctionService.getById(id);

        AuctionDto dtoResponse = entityToDtoConverter.convertToDto(auctionService.put(auctionDto, auction));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/submit")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> submitPending(@PathVariable("id") int id) {
        Auction auction = auctionService.getById(id);

        AuctionDto dtoResponse = entityToDtoConverter.convertToDto(auctionService.submitPending(auction));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> delete(@PathVariable Integer id) {
        auctionService.deleteById(id);
        CommonResponse response = new CommonResponse(true, "Deleted auction!", null, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    /*
     * Bids related
     * */

    @GetMapping("/{id}/bids")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> getBidsByAuctionId(@PathVariable(value = "id") Integer id) {
        auctionService.getById(id);
        List<Bid> bids = bidService.getBidsByAuctionId(id);
        List<BidDto> dtoResponse = entityToDtoConverter.convertToListBidDto(bids);
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/bids")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> saveBid(@PathVariable(value = "id") Integer id, @Valid @RequestBody BidRequestDto bidDto) {
        Bid bid = dtoToEntityConverter.convertToEntity(bidDto);
        BidDto dtoResponse = entityToDtoConverter.convertToBidDto(bidService.saveBid(id, bidDto, bid));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        webSocketService.sendBid(id, response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*
     * Interested Users
     * */

    @PostMapping("/{id}/interested")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> saveInterestedUser(@PathVariable(value = "id") Integer id) {

        auctionUserService.saveInterestUser(id);
        CommonResponse response = new CommonResponse(true, "Success", null, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/interested")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> removeInterestedUser(@PathVariable(value = "id") Integer id) {

        auctionUserService.removeInterestUser(id);
        CommonResponse response = new CommonResponse(true, "Success", null, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
