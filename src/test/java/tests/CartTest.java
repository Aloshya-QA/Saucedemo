package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test
    public void checkAddedProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.openCart();
        assertEquals(cartPage.getProductsName(0), "Sauce Labs Backpack");
    }

    @Test
    public void checkAddedProductsPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.openCart();
        assertEquals(cartPage.getProductPrice(0), 29.99);
    }

    @Test
    public void checkRemoveProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.openCart();
        assertEquals(cartPage.getCountOfProducts(), 2);
        cartPage.removeProduct("Sauce Labs Backpack");
        assertEquals(cartPage.getCountOfProducts(), 1);
    }

    @Test
    public void checkNumberAddedProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.openCart();
        assertEquals(cartPage.getCountOfProducts(), 2);
    }

    @Test
    public void checkContinueShoppingButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.openCart();
        cartPage.clickContinueShoppingButton();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void verifyCheckoutButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.openCart();
        cartPage.clickCheckoutButton();
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }
}
