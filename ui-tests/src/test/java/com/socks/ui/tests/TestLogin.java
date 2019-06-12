package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.example.model.UserPayload;
import com.socks.ui.CustomerLoginPopup;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.testng.annotations.Test;

public class TestLogin extends BaseUiTest {

    @Test
    public void userCanLoginWithValidCredentials() {
        //given
        UserPayload userPayload = createAndRegisterNewUser();
        //when
        MainPage.open().loginAs(userPayload.getUsername(), userPayload.getPassword());
        //then
        at(LoggedUserPage.class).logoutButton().shouldHave(Condition.text("Logout"));
    }

    @Test
    public void userCanNotLoginWithInvalidPassword() {
        //given
        UserPayload userPayload = createAndRegisterNewUser();
        String validUsername = userPayload.getUsername();
        String invalidPassword = "111111";
        //when
        MainPage.open().loginAs(validUsername, invalidPassword);
        //then
        at(CustomerLoginPopup.class).loginMessage().shouldHave(Condition.text("Invalid login credentials."));
    }

    @Test
    public void userCanNotLoginWithEmptyPassword() {
        //given
        UserPayload userPayload = createAndRegisterNewUser();
        String validUsername = userPayload.getUsername();
        String emptyPassword = "";
        //when
        MainPage.open().loginAs(validUsername, emptyPassword);
        //then
        at(CustomerLoginPopup.class).loginMessage().shouldHave(Condition.text("Invalid login credentials."));
    }

    @Test
    public void userCanNotLoginWithInvalidUsername() {
        //given
        UserPayload userPayload = createAndRegisterNewUser();
        String invalidUsername = userPayload.getUsername().concat("1");
        String validPassword = userPayload.getPassword();
        //when
        MainPage.open().loginAs(invalidUsername, validPassword);
        //then
        at(CustomerLoginPopup.class).loginMessage().shouldHave(Condition.text("Invalid login credentials."));
    }

    @Test
    public void userCanNotLoginWithEmptyUsername() {
        //given
        UserPayload userPayload = createAndRegisterNewUser();
        String emptyUsername = "";
        String validPassword = userPayload.getPassword();
        //when
        MainPage.open().loginAs(emptyUsername, validPassword);
        //then
        at(CustomerLoginPopup.class).loginMessage().shouldHave(Condition.text("Invalid login credentials."));
    }

}
