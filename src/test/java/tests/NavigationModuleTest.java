package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.NavigationModule;

import java.time.Duration;

import static org.testng.Assert.*;

public class NavigationModuleTest extends BaseTest{

    @Test
    public void checkOpenBurgerMenu() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.openCart();
        cartPage.openBurgerMenu();
        assertTrue(cartPage.isVisibleBurgerMenu());
    }

    @Test
    public void checkCloseBurgerMenu() {
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.openCart();
        cartPage.openBurgerMenu();
        wait.until(ExpectedConditions.attributeContains(
                NavigationModule.BURGER_MENU, "aria-hidden", "false"));
        cartPage.closeBurgerMenu();
        wait.until(ExpectedConditions.attributeContains(
                NavigationModule.BURGER_MENU, "aria-hidden", "true"));
        assertFalse(cartPage.isVisibleBurgerMenu());
    }

    @Test
    public void checkLogoutButtonFromBurgerMenu() {
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.openCart();
        cartPage.openBurgerMenu();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(NavigationModule.BURGER_MENU)));
        cartPage.clickLogoutFromBurgerMenu();
        assertTrue(loginPage.isLoginButtonVisible());
    }

    @Test
    public void checkAllItemsButtonFromBurgerMenu() {
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10));
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.openCart();
        cartPage.openBurgerMenu();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(NavigationModule.BURGER_MENU)));
        cartPage.clickAllItemsFromBurgerMenu();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkOpenCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }
}
