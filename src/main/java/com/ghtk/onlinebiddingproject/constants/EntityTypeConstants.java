package com.ghtk.onlinebiddingproject.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum EntityTypeConstants {
    AUCTION(0),
    REPORT(1);
    private final int entityType;
}
