package com.socks.tests.user;

import com.example.model.UserPayload;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.conditions.Conditions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

class UserRegisterApiTests extends BaseTest{

    /***
     * Questions:
     * How to view assertions in Allure?
     *
     */

    @Test
    @DisplayName("Can Register User With Valid Credentials")
    void testCanRegisterUserWithValidCredentials() {

        UserPayload userPayload = new UserPayload()
            .setUsername(faker.name().firstName() + " " + faker.name().lastName())
            .setEmail(faker.internet().emailAddress())
            .setPassword(faker.internet().password());

        String id = userApiServices.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(body(containsString("id")))
                .shouldHave(body("id", not(blankString())))
                .getValue("id");

        assertThat(id.length()).isEqualTo(24);

    }

    @Test
    @DisplayName("Can Not Register User With Empty Username")
    void testCanNotRegisterUserWithEmptyUsername() {
        UserPayload userPayload = new UserPayload()
                .setUsername("")
                .setPassword(faker.internet().password())
                .setEmail(faker.internet().emailAddress());

        userApiServices.registerUser(userPayload)
                //.shouldHave(contentType(ContentType.JSON))
                .shouldHave(statusCode(500))
                .shouldHave(header("Content-Length", "0"))
                .shouldHave(body(is(emptyOrNullString())));
    }

}
