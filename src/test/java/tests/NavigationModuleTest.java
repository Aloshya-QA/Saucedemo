package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.NavigationModule;

import java.time.Duration;

import static org.testng.Assert.*;

public class NavigationModuleTest extends BaseTest{

    @Test(testName = "Проверка кнопки бургер меню", groups = {"Regression"})
    @Description("Проверяет, что при нажатии на бургер-меню в корзине" +
            " оно успешно открывается")
    @Step("Ожидаем открытия бургер-меню")
    public void checkOpenBurgerMenu() {
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
//        cartPage.openCart();
//        cartPage.openBurgerMenu();
        wait.until(ExpectedConditions.visibilityOf(cartPage.buttonCloseBurgerMenu()));
        assertTrue(cartPage.buttonCloseBurgerMenu().isDisplayed());
    }

    @Test(testName = "Проверка кнопки зыкрывающей бургер меню", groups = {"Regression"})
    @Description("Проверяет, что при нажатии кнопки 'X' в бургер-меню" +
            " оно закрывается")
    @Step("Ожидаем закрытия бургер-меню")
    public void checkCloseBurgerMenu() {
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
//        cartPage.openCart();
//        cartPage.openBurgerMenu();
        wait.until(ExpectedConditions.visibilityOf(cartPage.buttonCloseBurgerMenu()));
//        cartPage.closeBurgerMenu();
        wait.until(ExpectedConditions.invisibilityOf(cartPage.buttonCloseBurgerMenu()));
        assertFalse(cartPage.buttonCloseBurgerMenu().isDisplayed());
    }

    @Test(testName = "Проверка кнопки 'Logout' в бургер меню", groups = {"Regression"})
    @Description("Проверяет, что при нажатии кнопки 'Logout' в бургер-меню" +
            " происходит логаут")
    @Step("Ожидаем логаут юзера")
    public void checkLogoutButtonFromBurgerMenu() {
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
//        cartPage.openCart();
//        cartPage.openBurgerMenu();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(NavigationModule.BURGER_MENU)));
//        cartPage.clickLogoutFromBurgerMenu();
//        assertTrue(loginPage.isLoginButtonVisible());
    }

    @Test(testName = "Проверка кнопки 'All Items' в бургер меню", groups = {"Regression"})
    @Description("Проверяет, что при нажатии кнопки All Items' в бургер-меню" +
            " открывается страница товаров")
    @Step("Ожидаем переход на страницу товаров")
    public void checkAllItemsButtonFromBurgerMenu() {
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
//        cartPage.openCart();
//        cartPage.openBurgerMenu();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(NavigationModule.BURGER_MENU)));
//        cartPage.clickAllItemsFromBurgerMenu();
//        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Проверка кнопки перехода в корзину", groups = {"Smoke"})
    @Description("Проверяет, что при нажатии кнопки 'Cart' открывается корзина")
    @Step("Ожидаем переход в корзину")
    public void checkOpenCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
//        cartPage.openCart();
//        assertEquals(cartPage.getTitle(), "Your Cart");
    }
}
