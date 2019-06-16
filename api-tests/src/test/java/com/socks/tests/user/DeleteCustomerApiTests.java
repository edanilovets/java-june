package com.socks.tests.user;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static com.example.conditions.Conditions.contentType;
import static com.example.conditions.Conditions.statusCode;

public class DeleteCustomerApiTests extends BaseTest {

    @Test
    void testCanDeleteCustomerById() {
        String id = "5cf570c4ee11cb0001f239b5";

        userApiServices.deleteUserById(id)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON));
    }
}
