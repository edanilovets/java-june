package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.example.ProjectConfig;
import com.example.model.UserPayload;
import com.example.services.UserApiServices;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLogin extends BaseUiTest {

    private final UserApiServices userApiServices = new UserApiServices();

    @BeforeClass
    public void setUp() {
        //Configuration.browser = "chrome";
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
        Configuration.baseUrl = ConfigFactory.create(ProjectConfig.class).apiPath();
    }

    @Test
    public void userCanLoginWithValidCredentials() {
        //given
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password());

        userApiServices.registerUser(userPayload);

        //when
        Selenide.open("");
        Selenide.$("#login > a").click();
        Selenide.$("#username-modal").sendKeys(userPayload.getUsername());
        Selenide.$("#password-modal").sendKeys(userPayload.getPassword());
        Selenide.$("#login-modal p button").click();

        //then
        Selenide.$("#logout > a").shouldHave(Condition.text("Logout"));
    }
}
