package com.ssafy.enjoytrip.util;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ResponseTemplate<T> {

    private boolean result;
    private String msg;
    private T data;
}
