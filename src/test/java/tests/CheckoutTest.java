package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CheckoutTest extends BaseTest{

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                {"Test", "Test", "Test"},
                {"11123", "Test", "12312313"},
                {"Test", "11231", "123"}
        };
    }

    @Test(dataProvider = "loginData", testName = "Проверка успешного ввода инофрмации", groups = {"Smoke"} )
    public void verifySuccessCheckout(String firstName, String lastName, String postalCode) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo(firstName, lastName, postalCode);
        assertEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @Test(testName = "Проверка ввода информации с пустыми полями", groups = {"Regression"})
    public void verifyCheckoutWithEmptyFields() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("", "", "");
        assertNotEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @Test(testName = "Проверка ввода информации с пустым полем 'First Name'", groups = {"Regression"})
    public void verifyCheckoutWithEmptyFirstNameField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("", "Gold", "11111");
        assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }

    @Test(testName = "Проверка ввода информации с пустым полем 'Last Name'", groups = {"Regression"})
    public void verifyCheckoutWithEmptyLastNameField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Joe", "", "11111");
        assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
    }

    @Test(testName = "Проверка ввода информации с пустым полем 'Postal Code'", groups = {"Regression"})
    public void verifyCheckoutWithEmptyPostalCodeField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Joe", "Gold", "");
        assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
    }

    @Test(testName = "Проверка стоимости товаров", groups = {"Regression"})
    public void checkProductsTotalPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        assertEquals(cartPage.getProductTotalPrice(), checkoutPage.getProductsTotalPrice());
    }

    @Test(testName = "Проверка суммы налога", groups = {"Regression"})
    public void checkTaxPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        assertEquals(Math.round(cartPage.getProductTotalPrice() * 0.8) / 10.0, checkoutPage.getTaxPrice());
    }

    @Test(testName = "Проверка итоговй суммы", groups = {"Regression"})
    public void checkTotalPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        assertEquals(cartPage.getProductTotalPrice()
                + Math.round(cartPage.getProductTotalPrice() * 0.8) / 10.0,
                checkoutPage.getTotalPrice());
    }

    @Test(testName = "Проверка кнопки 'Cancel'", groups = {"Regression"})
    public void checkCancelButtonCheckoutStepOne() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.clickCancelButton();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }

    @Test(testName = "Проверка кнопки 'Cancel' второго шага Checkout", groups = {"Regression"})
    public void checkCancelButtonCheckoutStepTwo() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        checkoutPage.clickCancelButton();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Проверка кнопки 'Finish'", groups = {"Smoke"})
    public void checkFinishButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        checkoutPage.clickFinishButton();
        assertEquals(cartPage.getTitle(), "Checkout: Complete!");
    }

    @Test(testName = "Проверка кнопки 'Back Home'", groups = {"Regression"})
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
