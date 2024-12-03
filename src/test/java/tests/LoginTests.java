package tests;

import dto.UserDtoLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;

import java.util.Random;

public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTest() {
        int i = new Random().nextInt(1000) + 1000;
        UserDtoLombok user = UserDtoLombok.builder()
                .email(i + "bob_mail@mail.com")
                .password("Pass123!")
                .build();

        new SearchPage(getDriver()).clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

    }

    @Test
    public void loginNegativeTest() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("invalid_email@mail.com")
                .password("WrongPass!")
                .build();

        new SearchPage(getDriver()).clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();


    }
}