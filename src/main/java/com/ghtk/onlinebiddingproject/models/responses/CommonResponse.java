package com.ghtk.onlinebiddingproject.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private boolean success;
    private String message;
    private Object data;
    private Object errors;
}
