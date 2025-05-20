package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Проверка валидного логина", groups = {"Smoke"})
    public void checkSuccessLogin() {
        loginPage.open();
        loginPage.login("standard_use", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products", "Not login");
    }

    @Test(testName = "Проверка логина с пустым паролем", groups = {"Regression"})
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "SO BAD");
    }

    @Test(testName = "Проверка логина с неверным паролем", groups = {"Regression"})
    public void checkLoginWithWrongPassword() {
        loginPage.open();
        loginPage.login("standard_user", "12314567");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAD");
    }
    @Test(testName = "Проверка логина с неверным юзером", groups = {"Regression"})
    public void checkLoginWithWrongUsername() {
        loginPage.open();
        loginPage.login("standard", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAD");
    }
}
