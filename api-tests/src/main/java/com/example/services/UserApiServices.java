package com.example.services;

import com.example.assertions.AssertableResponse;
import com.example.model.UserPayload;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserApiServices extends ApiServices{

    @Step("Register User: {userPayload}")
    public AssertableResponse registerUser(UserPayload userPayload) {
        log.info("About to register user: " + userPayload);
        return new AssertableResponse(setup()
                .body(userPayload)
                .when()
                .post("register"));
    }

    @Step("Delete User with Id: {id}")
    public AssertableResponse deleteUserById(String id) {
        log.info("About to delete user with id: " + id);
        return new AssertableResponse(setup()
                .when()
                .delete("customers/" + id));
    }

    @Step
    public AssertableResponse getAllCustomers() {
        return new AssertableResponse(setup()
                .when()
                .get("customers"));
    }

    @Step("Get User by Id={id}")
    public AssertableResponse getUserById(String id) {
        return new AssertableResponse(setup()
                .when()
                .get("customers/{id}", id));
    }
}
