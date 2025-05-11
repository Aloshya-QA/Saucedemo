package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CheckoutTest extends BaseTest{

    @Test
    public void verifySuccessCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        assertEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @Test
    public void verifyCheckoutWithEmptyFields() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("", "", "");
        assertNotEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @Test
    public void verifyCheckoutWithEmptyFirstNameField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("", "Gold", "11111");
        assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }

    @Test
    public void verifyCheckoutWithEmptyLastNameField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Joe", "", "11111");
        assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
    }

    @Test
    public void verifyCheckoutWithEmptyPostalCodeField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Joe", "Gold", "");
        assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
    }

    @Test
    public void checkProductsTotalPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        assertEquals(cartPage.getProductTotalPrice(), checkoutPage.getProductsTotalPrice());
    }

    @Test
    public void checkTaxPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        assertEquals(Math.round(cartPage.getProductTotalPrice() * 0.8) / 10.0, checkoutPage.getTaxPrice());
    }

    @Test void checkTotalPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        assertEquals(cartPage.getProductTotalPrice()
                + Math.round(cartPage.getProductTotalPrice() * 0.8) / 10.0,
                checkoutPage.getTotalPrice());
    }

    @Test
    public void checkCancelButtonCheckoutStepOne() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.clickCancelButton();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }

    @Test
    public void checkCancelButtonCheckoutStepTwo() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        checkoutPage.clickCancelButton();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkFinishButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        checkoutPage.clickFinishButton();
        assertEquals(cartPage.getTitle(), "Checkout: Complete!");
    }

    @Test
    public void checkBackHomeButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        checkoutPage.clickFinishButton();
        checkoutPage.clickBackHomeButton();
        assertEquals(productsPage.getTitle(), "Products");
    }
}
