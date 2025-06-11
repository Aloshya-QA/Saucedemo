package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class LoginTest extends BaseTest {

    @Test(testName = "Проверка валидного логина", groups = {"Smoke", "Regression"})
    @Description("Проверяет, что при корректном вводе имени," +
            " и пароля происходит авторизация.")
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
        loginStep.auth(user, password);
        assertThat(productsPage.getTitle())
                .isEqualTo("Products");
    }

    @Test(testName = "Проверка логина с пустым паролем", groups = {"Regression"})
    @Description("Проверяет, что при попытке входа с пустым паролем" +
            " отображается соответствующее сообщение об ошибке.")
    @Step("Пробуем войти с пользователем: standard_user, пустой пароль")
    public void checkLoginWithEmptyPassword() {
        loginPage.open()
                .login(user, "");
        assertThat(loginPage.getErrorMessage())
                .isEqualTo("Epic sadface: Password is required");
    }

    @Test(testName = "Проверка логина с неверным паролем", groups = {"Regression"})
    @Description("Проверяет, что при попытке входа с неверным паролем" +
            " отображается соответствующее сообщение об ошибке.")
    @Step("Пробуем войти с пользователем: standard_user, 12314567")
    public void checkLoginWithWrongPassword() {
        loginPage.open()
                .login(user, "12314567");
        assertThat(loginPage.getErrorMessage())
                .isEqualTo("Epic sadface: Username and password" +
                        " do not match any user in this service");
    }

    @Test(testName = "Проверка логина с неверным юзером", groups = {"Regression"})
    @Description("Проверяет, что при попытке входа с неверным именем пользователя" +
            " отображается соответствующее сообщение об ошибке.")
    @Step("Пробуем войти с пользователем: standard, secret_sauce")
    public void checkLoginWithWrongUsername() {
        loginPage.open()
                .login("standard", password);
        assertThat(loginPage.getErrorMessage())
                .isEqualTo("Epic sadface: Username and password" +
                        " do not match any user in this service");
    }
}
