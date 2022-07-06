package com.ghtk.onlinebiddingproject.utils;

import com.ghtk.onlinebiddingproject.constants.PagingHeadersConstants;
import com.ghtk.onlinebiddingproject.models.responses.AuctionPagingResponse;
import org.springframework.http.HttpHeaders;

public class HttpHeadersUtils {
    public static HttpHeaders returnHttpHeaders(AuctionPagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeadersConstants.COUNT.getProp(), String.valueOf(response.getCount()));
        headers.set(PagingHeadersConstants.PAGE.getProp(), String.valueOf(response.getPage()));
        headers.set(PagingHeadersConstants.PAGE_SIZE.getProp(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeadersConstants.PAGE_TOTAL.getProp(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}
