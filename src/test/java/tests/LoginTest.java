package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(testName = "Проверка валидного логина", groups = {"Smoke"})
    @Description("Проверяет, что при корректном вводе имени," +
            " и пароля происходит авторизация")
    @Step("Ввод данных для валидной авторизации")
    @Epic("Авторизация")
    @Feature("Страница логина")
    @Story("Позитивный логин")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Aliaksei Lokhach")
    @Link(name = "Документация", url = "https://www.saucedemo.com/")
    @TmsLink("TMS")
    @Issue("TMS")
    public void checkSuccessLogin() {
        loginStep.auth("standard_user", "secret_sauce");
        assertThat(productsPage.getTitle())
                .isEqualTo("Products");
    }

    @Test(testName = "Проверка логина с пустым паролем", groups = {"Regression"})
    @Description("Проверяет, что при попытке входа с пустым паролем" +
            " отображается соответствующее сообщение об ошибке.")
    @Step("Пробуем войти с пользователем: standard_user, пустой пароль")
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "SO BAD");
    }

    @Test(testName = "Проверка логина с неверным паролем", groups = {"Regression"})
    @Description("Проверяет, что при попытке входа с неверным паролем" +
            " отображается соответствующее сообщение об ошибке.")
    @Step("Пробуем войти с пользователем: standard_user, 12314567")
    public void checkLoginWithWrongPassword() {
        loginPage.open();
        loginPage.login("standard_user", "12314567");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAD");
    }
    @Test(testName = "Проверка логина с неверным юзером", groups = {"Regression"})
    @Description("Проверяет, что при попытке входа с неверным именем пользователя" +
            " отображается соответствующее сообщение об ошибке.")
    @Step("Пробуем войти с пользователем: standard, secret_sauce")
    public void checkLoginWithWrongUsername() {
        loginPage.open();
        loginPage.login("standard", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAD");
    }
}
