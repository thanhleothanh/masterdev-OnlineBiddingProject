package com.ghtk.onlinebiddingproject.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuctionStatusConstants {
    DRAFT(0),
    PENDING(1),
    QUEUED(2),
    OPENING(3),
    ENDED(4),
    SUCCESSFUL(5),
    CANCELED(-1);

    private final int status;
    private static final AuctionStatusConstants[] allValues = values();

    public static AuctionStatusConstants fromOrinal(Integer n) {
        return allValues[n];
    }
}
