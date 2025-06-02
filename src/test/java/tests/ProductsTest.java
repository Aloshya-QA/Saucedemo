package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTest extends BaseTest {

    @Test(testName = "Проверка сортировки товара", groups = {"Regression"})
    @Description("Проверяет, что сортировка товаров по имени или стоимости работает корректно.")
    @Step("Применяем сортировку товаров")
    public void sortProductsCheck() {
        SoftAssertions soft = new SoftAssertions();
        loginStep.auth("standard_user", "secret_sauce");
        productsPage.open()
                .isOpened()
                .sortProductsButton("Z to A");
        soft.assertThat(productsPage.getListProductsName())
                .isEqualTo(productsPage.getListProductsName("Z to A"));

        productsPage.sortProductsButton("A to Z");
        soft.assertThat(productsPage.getListProductsName())
                .isEqualTo(productsPage.getListProductsName("A to Z"));

        productsPage.sortProductsButton("Low to High");
        soft.assertThat(productsPage.getListProductsPrice())
                .isEqualTo(productsPage.getListProductsPrice("Low to High"));

        productsPage.sortProductsButton("High to Low");
        soft.assertThat(productsPage.getListProductsPrice())
                .isEqualTo(productsPage.getListProductsPrice("High to Low"));
        soft.assertAll();
    }

    @Test(testName = "Проверка кнопки добавления товаров", groups = {"Regression"})
    @Description("Проверяет, что товары, добавленные на странице продуктов, отображаются в корзине")
    @Step("Добавление товаров в корзину")
    public void checkAddedProducts() {
        loginStep.auth("standard_user", "secret_sauce");
        cartFillStep.openCartWithItems(
                "Sauce Labs Backpack",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Fleece Jacket"
        );
        assertThat(productsPage.getNameAddedProducts())
                .isEqualTo(cartPage.getProductsName());
    }

    @Test(testName = "Проверка кнопки удаления товаров", groups = {"Regression"})
    @Description("Проверяет, что товары, удаленные на странице продуктов, не отображаются в корзине")
    @Step("Удаление товаров из корзины")
    public void checkRemoveProducts() {
        loginStep.auth("standard_user", "secret_sauce");
        productsPage.open()
                .isOpened()
                .addProduct("Sauce Labs Backpack",
                        "Sauce Labs Onesie",
                        "Test.allTheThings() T-Shirt (Red)",
                        "Sauce Labs Fleece Jacket")
                .removeProduct("Test.allTheThings() T-Shirt (Red)");
        cartPage.open()
                .isOpened();
        assertThat(cartPage.getProductsName())
                .doesNotContain("Test.allTheThings() T-Shirt (Red)");
    }
}
