package com.ssafy.enjoytrip.enums;

public enum PageConstant {

    LIST_SIZE(20),
    NAVIGATION_SIZE(10);

    private final int value;

    PageConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
