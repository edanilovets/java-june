package com.example.assertions;

import com.example.conditions.Condition;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

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

    //If respnse as JSON
    @Step
    public <T> T asPojo(Class<T> tClass) {
        return response.as(tClass);
    }

    //IF response as String
    @Step
    public <T> T asPojoFromString(Class<T> tClass) {
        String str = response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(str, tClass);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }


}
