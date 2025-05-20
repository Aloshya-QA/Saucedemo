package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test(testName = "Проверка добавления товара в корзину", groups = {"Smoke"})
    public void checkAddedProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.openCart();
        assertEquals(cartPage.getProductsName(0), "Sauce Labs Backpack");
    }

    @Test(testName = "Проверка стоимости товара в корзине", groups = {"Regression"})
    public void checkAddedProductsPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.openCart();
        assertEquals(cartPage.getProductPrice(0), 29.99);
    }

    @Test(testName = "Проверка удаления товара из корзины", groups = {"Regression"})
    public void checkRemoveProducts() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.openCart();
        softAssert.assertEquals(cartPage.getCountOfProducts(), 2);
        cartPage.removeProduct("Sauce Labs Backpack");
        softAssert.assertEquals(cartPage.getCountOfProducts(), 1);
        softAssert.assertAll();
    }

    @Test(testName = "Проверка кол-ва добавленных товаров в корзину", groups = {"Regression"})
    public void checkNumberAddedProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.openCart();
        assertEquals(cartPage.getCountOfProducts(), 2);
    }

    @Test(testName = "Проверка кнопки 'Continue Shopping'", groups = {"Regression"})
    public void checkContinueShoppingButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.openCart();
        cartPage.clickContinueShoppingButton();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Проверка кнопки 'Checkout'", groups = {"Smoke"})
    public void verifyCheckoutButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.openCart();
        cartPage.clickCheckoutButton();
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }
}
