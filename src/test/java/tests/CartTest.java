package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends BaseTest {

    @Test(testName = "Проверка добавления товара в корзину", groups = {"Smoke", "Regression"})
    @Description("Проверяет, что добавленные товары корректно отображаются в корзине")
    @Step("Проверка названий добавленных товаров. " +
            "Ожидается: 'Sauce Labs Backpack', 'Sauce Labs Bike Light'")
    public void checkAddedProducts() {
        loginStep.auth("standard_user", "secret_sauce");
        cartFillStep.openCartWithItems("Sauce Labs Backpack", "Sauce Labs Bike Light");
        assertThat(cartPage.getProductsName())
                .contains("Sauce Labs Backpack", "Sauce Labs Bike Light");
    }

    @Test(testName = "Проверка стоимости товара в корзине", groups = {"Regression"})
    @Description("Проверяет, что после добавления товара в корзину его стоимость отображается корректно")
    @Step("Проверка стоимости товара 'Sauce Labs Bike Light'." +
            "Ожидаемая цена: 9.99")
    public void checkAddedProductsPrice() {
        loginStep.auth("standard_user", "secret_sauce");
        cartFillStep.openCartWithItems("Sauce Labs Backpack", "Sauce Labs Bike Light");
        assertThat(cartPage.getProductPrice("Sauce Labs Bike Light"))
                .isEqualTo(9.99);
    }

    @Test(testName = "Проверка удаления товара из корзины", groups = {"Regression"})
    @Description("Проверяет, что после удаления одного из товаров из корзины," +
            "количество товаров уменьшается корректно")
    @Step("Проверка количества товаров в корзине. Ожидается: 1")
    public void checkRemoveProducts() {
        SoftAssertions soft = new SoftAssertions();
        loginStep.auth(user, password);
        cartFillStep.openCartWithItems("Sauce Labs Backpack", "Sauce Labs Bike Light");
        soft.assertThat(cartPage.getCountOfProducts()).isEqualTo(2);
        cartPage.removeProduct("Sauce Labs Backpack");
        soft.assertThat(cartPage.getCountOfProducts())
                .isEqualTo(1);
        soft.assertThat(cartPage.getProductsName())
                .doesNotContain("Sauce Labs Backpack");
        soft.assertAll();
    }

    @Test(testName = "Проверка кол-ва добавленных товаров в корзину", groups = {"Regression"})
    @Description("Проверяет, что количество товаров, добавленных в корзину, отображается корректно")
    @Step("Проверка количества товаров в корзине. Ожидается: 2")
    public void checkNumberAddedProducts() {
        loginStep.auth(user, password);
        cartFillStep.openCartWithItems("Sauce Labs Backpack", "Sauce Labs Bike Light");
        assertThat(cartPage.getCountOfProducts()).isEqualTo(2);
    }

    @Test(testName = "Проверка кнопки 'Continue Shopping'", groups = {"Regression"})
    @Description("Проверяет, что кнопка 'Continue Shopping' на странице корзины " +
            "корректно возвращает пользователя на страницу товаров")
    @Step("Нажатие на кнопку 'Continue Shopping'")
    public void checkContinueShoppingButton() {
        loginStep.auth(user, password);
        cartFillStep.openCartWithItems("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.clickContinueShoppingButton()
                .isOpened();
        assertThat(productsPage.getTitle())
                .isEqualTo("Products");
    }

    @Test(testName = "Проверка кнопки 'Checkout'", groups = {"Smoke", "Regression"})
    @Description("Проверяет, что кнопка 'Checkout' на странице корзины " +
            "корректно перенаправляет на шаг оформления заказа")
    @Step("Нажатие на кнопку 'Checkout'")
    public void verifyCheckoutButton() {
        loginStep.auth(user, password);
        cartFillStep.openCartWithItems("Sauce Labs Backpack", "Sauce Labs Bike Light");
        cartPage.clickCheckoutButton()
                .isOpened();
        assertThat(checkoutPage.getTitle())
                .isEqualTo("Checkout: Your Information");
    }
}
