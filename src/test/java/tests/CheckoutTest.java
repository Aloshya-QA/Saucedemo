package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
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
    @Description("Проверяет, что при корректном вводе имени," +
            " фамилии и почтового индекса пользователь переходит на шаг 'Checkout: Overview'")
    @Step("Ввод данных для оформления заказа: Имя = {firstName}, Фамилия = {lastName}, Индекс = {postalCode}")
    public void verifySuccessCheckout(String firstName, String lastName, String postalCode) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo(firstName, lastName, postalCode);
        assertEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @Test(testName = "Проверка ввода информации с пустыми полями", groups = {"Regression"})
    @Description("Проверяет, что при попытке продолжить оформление заказа с пустыми полями" +
            " происходит валидация и переход на следующий шаг не выполняется")
    @Step("Попытка ввода пустых значений в форму оформления")
    public void verifyCheckoutWithEmptyFields() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("", "", "");
        assertNotEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @Test(testName = "Проверка ввода информации с пустым полем 'First Name'", groups = {"Regression"})
    @Description("Проверяет, что при попытке продолжить оформление заказа" +
            " с пустым полем 'First Name' отображается корректное сообщение об ошибке")
    @Step("Ввод данных с пустым полем 'First Name'")
    public void verifyCheckoutWithEmptyFirstNameField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("", "Gold", "11111");
        assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }

    @Test(testName = "Проверка ввода информации с пустым полем 'Last Name'", groups = {"Regression"})
    @Description("Проверяет, что при попытке продолжить оформление заказа" +
            " с пустым полем 'Last Name' отображается корректное сообщение об ошибке")
    @Step("Ввод данных с пустым полем 'Last Name'")
    public void verifyCheckoutWithEmptyLastNameField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Joe", "", "11111");
        assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
    }

    @Test(testName = "Проверка ввода информации с пустым полем 'Postal Code'", groups = {"Regression"})
    @Description("Проверяет, что при попытке продолжить оформление заказа" +
            " с пустым полем 'Postal Code' отображается корректное сообщение об ошибке")
    @Step("Ввод данных с пустым полем 'Postal Code'")
    public void verifyCheckoutWithEmptyPostalCodeField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Joe", "Gold", "");
        assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
    }

    @Test(testName = "Проверка стоимости товаров", groups = {"Regression"})
    @Description("Проверяет, что итоговая сумма стоимости выбранных товаров совпадает" +
            " с отображаемой на шаге оформления заказа")
    @Step("Сравнение общей суммы товаров в корзине и на странице оформления")
    public void checkProductsTotalPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        assertEquals(cartPage.getProductTotalPrice(), checkoutPage.getProductsTotalPrice());
    }

    @Test(testName = "Проверка суммы налога", groups = {"Regression"})
    @Description("Проверяет, что сумма налога на шаге оформления заказа" +
            " рассчитывается корректно от общей стоимости товаров")
    @Step("Проверка корректности расчёта налога: 8% от общей стоимости товаров")
    public void checkTaxPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        assertEquals(Math.round(cartPage.getProductTotalPrice() * 0.8) / 10.0, checkoutPage.getTaxPrice());
    }

    @Test(testName = "Проверка итоговй суммы", groups = {"Regression"})
    @Description("Проверяет корректность расчёта итоговой суммы заказа" +
            " на этапе оформления покупки, включая товары и налог.")
    @Step("Проверяем итоговую стоимость заказа")
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
    @Description("Проверяет, что на первом шаге оформления заказа" +
            " кнопка 'Cancel' возвращает пользователя в корзину.")
    @Step("Нажимаем кнопку 'Cancel' на шаге Checkout Step One")
    public void checkCancelButtonCheckoutStepOne() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.clickCancelButton();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }

    @Test(testName = "Проверка кнопки 'Cancel' второго шага Checkout", groups = {"Regression"})
    @Description("Убеждаемся, что кнопка 'Cancel' на втором шаге оформления заказа" +
            " возвращает пользователя на страницу с товарами.")
    @Step("Нажимаем кнопку 'Cancel' на втором шаге оформления заказа")
    public void checkCancelButtonCheckoutStepTwo() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        checkoutPage.clickCancelButton();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Проверка кнопки 'Finish'", groups = {"Smoke"})

    @Description("Проверяет, что кнопка 'Finish' на втором шаге оформления заказа" +
            " завершает процесс и переводит на страницу подтверждения заказа.")
    @Step("Нажимаем кнопку 'Finish' на втором шаге Checkout")
    public void checkFinishButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        checkoutPage.open();
        checkoutPage.enterCheckoutInfo("Test", "Test", "11111");
        checkoutPage.clickFinishButton();
        assertEquals(cartPage.getTitle(), "Checkout: Complete!");
    }

    @Test(testName = "Проверка кнопки 'Back Home'", groups = {"Regression"})
    @Description("Проверяет, что после завершения заказа кнопка 'Back Home'" +
            " возвращает пользователя на страницу с товарами.")
    @Step("Нажимаем кнопку 'Back Home' после завершения заказа")
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
