package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.example.model.UserPayload;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.testng.annotations.Test;

public class TestLogin extends BaseUiTest {

    @Test
    public void userCanLoginWithValidCredentials() {
        //given
        UserPayload userPayload = createNewUser();

        //when
        MainPage.open().loginAs(userPayload.getUsername(), userPayload.getPassword());

        //then
        at(LoggedUserPage.class).logoutButton().shouldHave(Condition.text("Lgout"));
    }

}
