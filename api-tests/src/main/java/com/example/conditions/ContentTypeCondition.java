package com.example.conditions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContentTypeCondition implements Condition {

    private final ContentType contentType;

    @Override
    public void check(Response response) {
        response.then().contentType(contentType);
    }

    @Override
    public String toString() {
        return "contentType='" + contentType;
    }
}
