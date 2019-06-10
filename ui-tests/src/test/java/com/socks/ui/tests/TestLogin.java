package com.socks.ui.tests;

import com.codeborne.selenide.Selenide;
import com.example.model.UserPayload;
import com.example.services.UserApiServices;
import org.testng.annotations.Test;

public class TestLogin extends BaseUiTest {

    private UserApiServices userApiServices = new UserApiServices();

    @Test
    public void userCanLoginWithValidCredentials() {
        //given
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password());

        userApiServices.registerUser(userPayload);

        //when
        Selenide.open("http://localhost/");
        Selenide.$("#login > a").click();
        Selenide.$("#username-modal").sendKeys(userPayload.getUsername());
        Selenide.$("#password-modal").sendKeys(userPayload.getPassword());
        Selenide.$("#login-modal p button").click();

        //then
    }
}
