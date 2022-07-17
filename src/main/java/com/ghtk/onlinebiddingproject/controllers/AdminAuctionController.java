package com.ghtk.onlinebiddingproject.controllers;


import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.requests.AuctionRequestDto;
import com.ghtk.onlinebiddingproject.models.responses.AuctionPagingResponse;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.AuctionServiceImpl;
import com.ghtk.onlinebiddingproject.services.impl.BidServiceImpl;
import com.ghtk.onlinebiddingproject.utils.HttpHeadersUtils;
import com.ghtk.onlinebiddingproject.utils.converters.DtoToEntityConverter;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
import net.kaczmarzyk.spring.data.jpa.domain.LessThan;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/admin/auctions")
public class AdminAuctionController {
    @Autowired
    private AuctionServiceImpl auctionService;
    @Autowired
    private BidServiceImpl bidService;
    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    /*
     * Get auctions api cho admin
     * có thể sort theo price, time
     * có thể lọc theo thuộc tính
     * phân trang
     * */

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> getForAdmin(
            @And({
                    @Spec(path = "status", params = "status", spec = Equal.class),
                    @Spec(path = "priceStart", params = "priceStartGt", spec = GreaterThan.class),
                    @Spec(path = "priceStart", params = "priceStartLt", spec = LessThan.class),
                    @Spec(path = "timeStart", params = "timeStartGt", spec = GreaterThan.class),
                    @Spec(path = "timeStart", params = "timeStartLt", spec = LessThan.class),
                    @Spec(path = "timeEnd", params = "timeEndGt", spec = GreaterThan.class),
                    @Spec(path = "timeEnd", params = "timeEndLt", spec = LessThan.class),
                    @Spec(path = "highestPrice", params = "highestPriceGt", spec = GreaterThan.class),
                    @Spec(path = "highestPrice", params = "highestStartLt", spec = LessThan.class),
            }) Specification<Auction> spec,
            Sort sort,
            @RequestHeader HttpHeaders headers) {
        AuctionPagingResponse pagingResponse = auctionService.get(spec, headers, sort);

        List<AuctionDto> dtoResponse = entityToDtoConverter.convertToListAuctionDto(pagingResponse.getAuctions());
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpHeadersUtils.returnHttpHeaders(pagingResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminGetById(@PathVariable(value = "id") Integer id) {
        AuctionDto dtoResponse = entityToDtoConverter.convertToDto(auctionService.adminGetById(id));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminPut(@PathVariable("id") int id, @Validated @RequestBody AuctionRequestDto auctionDto) {
        Auction auction = auctionService.adminGetById(id);

        AuctionDto dtoResponse = entityToDtoConverter.convertToDto(auctionService.adminPut(auctionDto, auction));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminReviewSubmit(@PathVariable("id") int id) {
        Auction auction = auctionService.adminGetById(id);

        AuctionDto dtoResponse = entityToDtoConverter.convertToDto(auctionService.adminReviewSubmit(auction));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminDeleteById(@PathVariable Integer id) {
        auctionService.adminDeleteById(id);

        CommonResponse response = new CommonResponse(true, "Deleted auction!", null, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
