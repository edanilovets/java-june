package com.socks.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class LoggedUserPage {

    @Step
    public SelenideElement logoutButton(){
        return Selenide.$("#logout > a");
    }

    @Step
    public SelenideElement loggedAsText(){
        return Selenide.$("#howdy > a");
    }
}
