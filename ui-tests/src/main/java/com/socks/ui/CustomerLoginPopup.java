package com.socks.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class CustomerLoginPopup {

    @Step
    public SelenideElement loginMessage() {
        return Selenide.$("#login-message div");
    }

}
