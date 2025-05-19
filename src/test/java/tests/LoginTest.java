package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkSuccessLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products", "Not login");
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "SO BAD");
    }

    @Test
    public void checkLoginWithWrongPassword() {
        loginPage.open();
        loginPage.login("standard_user", "12314567");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAD");
    }
    @Test
    public void checkLoginWithWrongUsername() {
        loginPage.open();
        loginPage.login("standard", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAD");
    }
}
