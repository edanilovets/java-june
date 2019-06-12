package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.example.model.UserPayload;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.testng.annotations.Test;

public class TestRegister extends BaseUiTest {

    @Test
    public void canRegisterUserWithAllFields() {
        //given
        UserPayload userPayload = createNewUser();
        //when
        MainPage.open().registerNewUser(userPayload);
        //then
        at(LoggedUserPage.class).loggedAsText()
                .shouldHave(Condition.exactText(String.format("Logged in as %s %s",
                        userPayload.getFirstname(), userPayload.getLastname())));
    }
}
