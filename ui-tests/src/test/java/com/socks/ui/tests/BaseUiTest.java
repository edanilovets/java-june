package com.socks.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.ProjectConfig;
import com.example.model.UserPayload;
import com.example.services.UserApiServices;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.Locale;

public class BaseUiTest {

    private final UserApiServices userApiServices = new UserApiServices();
    private final Faker faker = new Faker(new Locale("en"));

    @BeforeClass
    public void setUp() {
        Configuration.browser = "com.socks.ui.drivers.SelenoidWebDriverProvider";
        Configuration.baseUrl = "http://192.168.31.250:80";
        //RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
        RestAssured.baseURI = "http://192.168.31.250:80";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterMethod
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }

    @Step
    protected <T> T at(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }


    @Step
    protected UserPayload createAndRegisterNewUser() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password())
                .setFirstname(faker.name().firstName())
                .setLastname(faker.name().lastName());

        userApiServices.registerUser(userPayload);
        return userPayload;
    }

    @Step
    protected UserPayload createNewUser() {

        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password())
                .setFirstname(faker.name().firstName())
                .setLastname(faker.name().lastName());
        return userPayload;
    }
}
