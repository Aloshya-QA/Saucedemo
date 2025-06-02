package tests;

import dto.CheckoutInfo;
import dto.CheckoutInfoFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends BaseTest{

    @DataProvider
    public Object[][] checkoutData() {
        CheckoutInfo data = CheckoutInfoFactory.checkoutInfo();
        return new Object[][] {
                {data.getFirstName(), data.getLastName(), data.getPostalCode()}};
    }

    @Test(dataProvider = "checkoutData",
            testName = "Проверка успешного ввода инофрмации",
            groups = {"Smoke", "Regression"} )
    @Description("Проверяет, что при корректном вводе имени," +
            " фамилии и почтового индекса пользователь переходит на шаг 'Checkout: Overview'")
    @Step("Ввод данных для оформления заказа: Имя = {firstName}," +
            " Фамилия = {lastName}," +
            " Индекс = {postalCode}")
    public void verifySuccessCheckout(String firstName, String lastName, String postalCode) {
        loginStep.auth("standard_user", "secret_sauce");
        checkoutPage.open()
                .isOpened()
                .enterCheckoutInfo(firstName, lastName, postalCode)
                .isOpened();
        assertThat(checkoutPage.getTitle())
                .isEqualTo("Checkout: Overview");
    }

    @Test(testName = "Проверка ввода информации с пустыми полями", groups = {"Regression"})
    @Description("Проверяет, что при попытке продолжить оформление заказа с пустыми полями" +
            " происходит валидация и переход на следующий шаг не выполняется")
    @Step("Попытка ввода пустых значений в форму оформления")
    public void verifyCheckoutWithEmptyFields() {
        loginStep.auth("standard_user", "secret_sauce");
        checkoutPage.open()
                .isOpened()
                .enterCheckoutInfo("", "", "")
                .isOpened();
        assertThat(checkoutPage.getTitle())
                .isNotEqualTo("Checkout: Overview");
    }

    @Test(testName = "Проверка ввода информации с пустым полем 'First Name'", groups = {"Regression"})
    @Description("Проверяет, что при попытке продолжить оформление заказа" +
            " с пустым полем 'First Name' отображается корректное сообщение об ошибке")
    @Step("Ввод данных с пустым полем 'First Name'")
    public void verifyCheckoutWithEmptyFirstNameField() {
        loginStep.auth("standard_user", "secret_sauce");
        checkoutPage.open()
                .isOpened()
                .enterCheckoutInfo("", "Gold", "12345")
                .isOpened();
        assertThat(checkoutPage.getErrorMessage())
                .isEqualTo("Error: First Name is required");
    }

    @Test(testName = "Проверка ввода информации с пустым полем 'Last Name'", groups = {"Regression"})
    @Description("Проверяет, что при попытке продолжить оформление заказа" +
            " с пустым полем 'Last Name' отображается корректное сообщение об ошибке")
    @Step("Ввод данных с пустым полем 'Last Name'")
    public void verifyCheckoutWithEmptyLastNameField() {
        loginStep.auth("standard_user", "secret_sauce");
        checkoutPage.open()
                .isOpened()
                .enterCheckoutInfo("Joe", "", "12345")
                .isOpened();
        assertThat(checkoutPage.getErrorMessage())
                .isEqualTo("Error: Last Name is required");
    }

    @Test(testName = "Проверка ввода информации с пустым полем 'Postal Code'", groups = {"Regression"})
    @Description("Проверяет, что при попытке продолжить оформление заказа" +
            " с пустым полем 'Postal Code' отображается корректное сообщение об ошибке")
    @Step("Ввод данных с пустым полем 'Postal Code'")
    public void verifyCheckoutWithEmptyPostalCodeField() {
        loginStep.auth("standard_user", "secret_sauce");
        checkoutPage.open()
                .isOpened()
                .enterCheckoutInfo("Joe", "Gold", "")
                .isOpened();
        assertThat(checkoutPage.getErrorMessage())
                .isEqualTo("Error: Postal Code is required");
    }

    @Test(testName = "Проверка стоимости товаров", groups = {"Regression"})
    @Description("Проверяет, что итоговая сумма стоимости выбранных товаров совпадает" +
            " с отображаемой на шаге оформления заказа")
    @Step("Сравнение общей суммы товаров в корзине и на странице оформления")
    public void checkProductsTotalPrice() {
        loginStep.auth("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open()
                .isOpened()
                .enterCheckoutInfo("Joe", "Gold", "12345")
                .isOpened();
        assertThat(cartPage.getProductTotalPrice())
                .isEqualTo(checkoutPage.getProductsTotalPrice());
    }

    @Test(testName = "Проверка суммы налога", groups = {"Regression"})
    @Description("Проверяет, что сумма налога на шаге оформления заказа" +
            " рассчитывается корректно от общей стоимости товаров")
    @Step("Проверка корректности расчёта налога: 8% от общей стоимости товаров")
    public void checkTaxPrice() {
        loginStep.auth("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open()
                .isOpened()
                .enterCheckoutInfo("Joe", "Gold", "12345")
                .isOpened();
        assertThat(Math.round(cartPage.getProductTotalPrice() * 0.8) / 10.0)
                .isEqualTo(checkoutPage.getTaxPrice());
    }

    @Test(testName = "Проверка итоговй суммы", groups = {"Regression"})
    @Description("Проверяет корректность расчёта итоговой суммы заказа" +
            " на этапе оформления покупки, включая товары и налог.")
    @Step("Проверяем итоговую стоимость заказа")
    public void checkTotalPrice() {
        loginStep.auth("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
        checkoutPage.open()
                .isOpened()
                .enterCheckoutInfo("Joe", "Gold", "12345")
                .isOpened();
        assertThat(cartPage.getProductTotalPrice()
                + Math.round(cartPage.getProductTotalPrice() * 0.8) / 10.0)
                .isEqualTo(checkoutPage.getTotalPrice());
    }

    @Test(testName = "Проверка кнопки 'Cancel'", groups = {"Regression"})
    @Description("Проверяет, что на первом шаге оформления заказа" +
            " кнопка 'Cancel' возвращает пользователя в корзину.")
    @Step("Нажимаем кнопку 'Cancel' на шаге Checkout Step One")
    public void checkCancelButtonCheckoutStepOne() {
        loginStep.auth("standard_user", "secret_sauce");
        checkoutPage.open()
                .isOpened()
                .clickCancelButton()
                .isOpened();
        assertThat(cartPage.getTitle())
                .isEqualTo("Your Cart");
    }

    @Test(testName = "Проверка кнопки 'Cancel' второго шага Checkout", groups = {"Regression"})
    @Description("Убеждаемся, что кнопка 'Cancel' на втором шаге оформления заказа" +
            " возвращает пользователя на страницу с товарами.")
    @Step("Нажимаем кнопку 'Cancel' на втором шаге оформления заказа")
    public void checkCancelButtonCheckoutStepTwo() {
        loginStep.auth("standard_user", "secret_sauce");
        checkoutPage.open()
                .isOpened()
                .enterCheckoutInfo("Test", "Test", "11111")
                .isOpened()
                .clickCancelSecondButton()
                .isOpened();
        assertThat(productsPage.getTitle())
                .isEqualTo("Products");
    }

    @Test(testName = "Проверка кнопки 'Finish'", groups = {"Smoke", "Regression"})

    @Description("Проверяет, что кнопка 'Finish' на втором шаге оформления заказа" +
            " завершает процесс и переводит на страницу подтверждения заказа.")
    @Step("Нажимаем кнопку 'Finish' на втором шаге Checkout")
    public void checkFinishButton() {
        loginStep.auth("standard_user", "secret_sauce");
        checkoutStep.completeOrder("Bob", "Marley", "123456");
        assertThat(checkoutPage.getTitle())
                .isEqualTo("Checkout: Complete!");
    }

    @Test(testName = "Проверка кнопки 'Back Home'", groups = {"Regression"})
    @Description("Проверяет, что после завершения заказа кнопка 'Back Home'" +
            " возвращает пользователя на страницу с товарами.")
    @Step("Нажимаем кнопку 'Back Home' после завершения заказа")
    public void checkBackHomeButton() {
        loginStep.auth("standard_user", "secret_sauce");
        checkoutStep.completeOrder("Bob", "Marley", "123456");
        checkoutPage.clickBackHomeButton()
                .isOpened();
        assertThat(productsPage.getTitle())
                .isEqualTo("Products");
    }
}
