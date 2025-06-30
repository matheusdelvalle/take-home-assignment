package com.matheusdelvalle.test.testecase.emums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Operation {
    DEPOSIT,
    WITHDRAW,
    TRANSFER;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase();
    }
}