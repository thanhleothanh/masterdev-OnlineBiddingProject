package com.ghtk.onlinebiddingproject.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotificationTypeConstants {
    REVIEW_AUCTION("REVIEW_AUCTION", "AUCTION"),
    START_AUCTION("START_AUCTION", "AUCTION"),
    END_AUCTION("END_AUCTION", "AUCTION"),
    NEW_BID_AUCTION("NEW_BID_AUCTION", "AUCTION"),
    CREATE_REPORT("CREATE_REPORT", "REPORT"),
    JUDGE_REPORT("JUDGE_REPORT", "REPORT");

    private final String notificationType;
    private final String entityType;
}
