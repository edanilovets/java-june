package com.example.services;

import com.example.assertions.AssertableResponse;
import com.example.model.UserPayload;
import io.qameta.allure.Step;

public class UserApiServices extends ApiServices{

    @Step
    public AssertableResponse registerUser(UserPayload userPayload) {
        return new AssertableResponse(setup()
                .body(userPayload)
                .when()
                .post("register"));
    }

    @Step
    public AssertableResponse getAllCustomers() {
        return new AssertableResponse(setup()
                .when()
                .get("customers"));
    }

    @Step
    public AssertableResponse getUserById(String id) {
        return new AssertableResponse(setup()
                .when()
                .get("customers/{id}", id));
    }
}
