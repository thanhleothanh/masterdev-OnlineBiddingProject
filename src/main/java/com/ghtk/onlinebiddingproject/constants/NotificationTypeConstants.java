package com.ghtk.onlinebiddingproject.constants;


import lombok.Getter;

@Getter
public enum NotificationTypeConstants {
    ACCEPT_AUCTION("duyệt bài đấu giá","AUCTION"),
    OPEN_AUCTION("mở bài đấu giá","AUCTION"),
    END_AUCTION("kết thúc đấu giá","AUCTION"),
    HIGHER_PRICE_AUCTION("trả giá cao hơn khi đấu giá", "AUCTION"),
    CREATE_REPORT("tạo bài báo cáo","REPORT"),
    ACCEPT_REPORT("duyệt báo cáo", "REPORT"),
    REJECT_REPORT("từ chối báo cáo","REPORT");


    private final String description;
    private final String entityType;
    private NotificationTypeConstants(String description,String entityType){
        this.description = description;
        this.entityType = entityType;
    }

}
