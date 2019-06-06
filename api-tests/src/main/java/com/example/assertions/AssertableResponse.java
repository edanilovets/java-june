package com.example.assertions;

import com.example.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssertableResponse {

    private final Response response;

    @Step("Should Have {condition}")
    public AssertableResponse shouldHave(Condition condition) {
        condition.check(response);
        return this;
    }

    // To have ability to save values of type String from JSON
    // For other types see getInteger etc.

    @Step
    public String getValue(String path) {
        return response.jsonPath().getObject(path, String.class);
    }

    @Step
    public <T> T asPojo(Class<T> tClass) {
        return response.as(tClass);
    }


}
