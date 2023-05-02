package com.ssafy.enjoytrip.enums;

public enum LoginConstant {

    LOGIN_ATTRIBUTE_NAME("userDto"),
    LOGIN_MESSAGE_KEY("msg"),
    LOGIN_NEED_MESSAGE("로그인이 필요합니다."),
    LOGIN_NOT_MATCH("로그인 정보가 일치하지 않습니다.");
    private final String value;

    LoginConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    }
