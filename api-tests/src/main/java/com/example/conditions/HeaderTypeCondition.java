package com.example.conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HeaderTypeCondition implements Condition {

    private final String headerName;
    private final String expectedValue;

    @Override
    public void check(Response response) {
        response.then().header(headerName, expectedValue);
    }

    @Override
    public String toString() {
        return "headerName='" + headerName + '\'' +
                ", expectedValue='" + expectedValue;
    }
}
