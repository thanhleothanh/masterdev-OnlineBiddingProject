package com.ghtk.onlinebiddingproject.controllers;


import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.requests.AuctionRequestDto;
import com.ghtk.onlinebiddingproject.models.responses.AuctionPagingResponse;
import com.ghtk.onlinebiddingproject.models.responses.AuctionPagingResponseDto;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.AuctionServiceImpl;
import com.ghtk.onlinebiddingproject.services.impl.JobSchedulerServiceImpl;
import com.ghtk.onlinebiddingproject.utils.HttpHeadersUtils;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/admin/auctions")
public class AdminAuctionController {
    @Autowired
    private AuctionServiceImpl auctionService;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;
    @Autowired
    private JobSchedulerServiceImpl jobSchedulerService;

    /*
     * Get auctions api cho admin
     * có thể sort theo price, time
     * có thể lọc theo thuộc tính
     * phân trang
     * */

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminGetAuctions(
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
        AuctionPagingResponse pagingResponse = auctionService.get(spec, headers, Sort.by(Sort.Direction.DESC, "createdAt"));

        AuctionPagingResponseDto dtoResponse = entityToDtoConverter.convertToDto(pagingResponse);
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
    public ResponseEntity<CommonResponse> adminPut(@PathVariable("id") int id, @Valid @RequestBody AuctionRequestDto auctionDto) {
        AuctionDto dtoResponse = entityToDtoConverter.convertToDto(auctionService.adminPut(auctionDto, id));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminReviewSubmit(@PathVariable("id") int id, @RequestBody @Valid AuctionRequestDto auctionRequestDto) {
        Auction approvedAuction = auctionService.adminReviewSubmit(auctionRequestDto, id);

        if (approvedAuction.getStatus().equals(AuctionStatusConstants.QUEUED)) {
            jobSchedulerService.startAuctionScheduler(approvedAuction); //scheduling a job to automatically open the auction when timeStart comes!
            jobSchedulerService.endAuctionScheduler(approvedAuction); //scheduling a job to automatically end the auction when timeEnd comes!
        }
        AuctionDto dtoResponse = entityToDtoConverter.convertToDto(approvedAuction);
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
