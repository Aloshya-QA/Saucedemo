package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test(testName = "Проверка добавления товара в корзину", groups = {"Smoke"})
    @Description("Проверяет, что добавленные товары корректно отображаются в корзине")
    @Step("Проверка названия товара. Ожидается: 'Sauce Labs Backpack'")
    public void checkAddedProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
//        cartPage.openCart();
        assertEquals(cartPage.getProductsName(0), "Sauce Labs Backpack");
    }

    @Test(testName = "Проверка стоимости товара в корзине", groups = {"Regression"})
    @Description("Проверяет, что после добавления товара в корзину его стоимость отображается корректно")
    @Step("Проверка цены товаров с индексом 'Sauce Labs Backpack', 'Sauce Labs Bike Light'." +
            "Ожидаемая цена: 29.99")
    public void checkAddedProductsPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
//        cartPage.openCart();
        assertEquals(cartPage.getProductPrice(0), 29.99);
    }

    @Test(testName = "Проверка удаления товара из корзины", groups = {"Regression"})
    @Description("Проверяет, что после удаления одного из товаров из корзины," +
            "количество товаров уменьшается корректно")
    @Step("Проверка количества товаров в корзине. Ожидается: 1")
    public void checkRemoveProducts() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
//        cartPage.openCart();
        softAssert.assertEquals(cartPage.getCountOfProducts(), 2);
        cartPage.removeProduct("Sauce Labs Backpack");
        softAssert.assertEquals(cartPage.getCountOfProducts(), 1);
        softAssert.assertAll();
    }

    @Test(testName = "Проверка кол-ва добавленных товаров в корзину", groups = {"Regression"})
    @Description("Проверяет, что количество товаров, добавленных в корзину, отображается корректно")
    @Step("Проверка количества товаров в корзине. Ожидается: 2")
    public void checkNumberAddedProducts() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack", "Sauce Labs Bike Light");
//        cartPage.openCart();
        assertEquals(cartPage.getCountOfProducts(), 2);
    }

    @Test(testName = "Проверка кнопки 'Continue Shopping'", groups = {"Regression"})
    @Description("Проверяет, что кнопка 'Continue Shopping' на странице корзины " +
            "корректно возвращает пользователя на страницу товаров")
    @Step("Нажатие на кнопку 'Continue Shopping'")
    public void checkContinueShoppingButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
//        cartPage.openCart();
        cartPage.clickContinueShoppingButton();
//        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Проверка кнопки 'Checkout'", groups = {"Smoke"})
    @Description("Проверяет, что кнопка 'Checkout' на странице корзины корректно переходит на шаг оформления заказа")
    @Step("Нажатие на кнопку 'Checkout'")
    public void verifyCheckoutButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
//        cartPage.openCart();
        cartPage.clickCheckoutButton();
//        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }
}
