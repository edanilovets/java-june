package com.socks.ui;

import com.codeborne.selenide.Selenide;
import com.example.model.UserPayload;
import io.qameta.allure.Step;

public class MainPage {

    @Step
    public static MainPage open() {
        Selenide.open("/");
        return new MainPage();
    }

    @Step
    public void loginAs(String username, String password) {

        Selenide.$("#login > a").click();
        Selenide.$("#username-modal").sendKeys(username);
        Selenide.$("#password-modal").sendKeys(password);
        Selenide.$("#login-modal p button").click();

    }

    @Step
    public void registerNewUser(UserPayload userPayload) {

        Selenide.$("#register > a").click();
        Selenide.$("#register-username-modal").sendKeys(userPayload.getUsername());
        //Selenide.$("#register-username-modal").setValue(userPayload.getUsername());
        Selenide.$("#register-first-modal").sendKeys(userPayload.getFirstname());
        Selenide.$("#register-last-modal").sendKeys(userPayload.getLastname());
        Selenide.$("#register-email-modal").sendKeys(userPayload.getEmail());
        Selenide.$("#register-password-modal").sendKeys(userPayload.getPassword());
        Selenide.$("#register-modal p button").click();

    }

}
