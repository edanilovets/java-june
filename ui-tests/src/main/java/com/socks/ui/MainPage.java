package com.socks.ui;

import com.codeborne.selenide.Selenide;
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
}
