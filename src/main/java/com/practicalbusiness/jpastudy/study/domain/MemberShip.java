package com.practicalbusiness.jpastudy.study.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum MemberShip implements CodeValue {
    GOLD("G", "GOLD"), SILVER("S", "SILVER"), VIP("V", "VIP");

    private final String code;
    private final String value;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    MemberShip(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @JsonCreator
    public static MemberShip fromString(String symbol) {
        return MemberShip.valueOf(symbol);
    }
}
