package com.ssafy.enjoytrip.enums;

public enum AttractionConstant {

    ATTRACTION_OFFSET(0),
    ATTRACTION_LIMIT(100);

    private final int value;

    AttractionConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
