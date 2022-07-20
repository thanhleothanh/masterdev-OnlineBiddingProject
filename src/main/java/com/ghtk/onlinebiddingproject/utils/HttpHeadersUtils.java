package com.ghtk.onlinebiddingproject.utils;

import com.ghtk.onlinebiddingproject.constants.PagingHeadersConstants;
import com.ghtk.onlinebiddingproject.models.responses.AuctionPagingResponse;
import org.springframework.http.HttpHeaders;

public class HttpHeadersUtils {
    public static HttpHeaders returnHttpHeaders(AuctionPagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Access-Control-Allow-Origin", "http://localhost:3000");
//        headers.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        headers.set("Access-Control-Max-Age", "3600");
//        headers.set("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,page,page_total,page_size,count");
//        headers.add("Access-Control-Expose-Headers", "xsrf-token");
        headers.set(PagingHeadersConstants.COUNT.getProp(), String.valueOf(response.getCount()));
        headers.set(PagingHeadersConstants.PAGE.getProp(), String.valueOf(response.getPage()));
        headers.set(PagingHeadersConstants.PAGE_SIZE.getProp(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeadersConstants.PAGE_TOTAL.getProp(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}
