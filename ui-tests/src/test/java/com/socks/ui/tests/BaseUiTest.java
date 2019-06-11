package com.socks.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.ProjectConfig;
import com.example.model.UserPayload;
import com.example.services.UserApiServices;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;

import java.util.Locale;

public class BaseUiTest {

    private final UserApiServices userApiServices = new UserApiServices();
    private final Faker faker = new Faker(new Locale("en"));

    @BeforeClass
    public void setUp() {
        //Configuration.browser = "chrome";
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
        Configuration.baseUrl = "http://localhost:80";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }


    @Step
    protected <T> T at(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }


    @Step
    protected UserPayload createNewUser() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password());

        userApiServices.registerUser(userPayload);
        return userPayload;
    }
}
