package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest{

    @Test(testName = "Проверка сортировки товара по имени", groups = {"Regression"})
    @Description("Проверяет, что сортировка товаров по имени в обратном порядке работает корректно.")
    @Step("Применяем сортировку по имени в обратном порядке")
    public void sortProductsNameByReverseOrder() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.open();
        productsPage.sortProductsButton(1);
        assertEquals(productsPage.getListProductsName(), productsPage.getListProductsNameDESC());
    }

    @Test(testName = "Проверка сортировки товара по возрастанию стоимости", groups = {"Regression"})
    @Description("Проверяет, что сортировка товаров возрастанию стоимости работает корректно.")
    @Step("Применяем сортировку по возрастанию стоимости")
    public void sortProductsPriceByLowToHigh() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.open();
        productsPage.sortProductsButton(2);
        assertEquals(productsPage.getListProductsPrice(), productsPage.getListProductsPriceASC());
    }

    @Test(testName = "Проверка сортировки товара по убыванию стоимости", groups = {"Regression"})
    @Description("Проверяет, что сортировка товаров убыванию стоимости работает корректно.")
    @Step("Применяем сортировку по убыванию стоимости")
    public void sortProductsPriceByHighToLow() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.open();
        productsPage.sortProductsButton(3);
        assertEquals(productsPage.getListProductsPrice(), productsPage.getListProductsPriceDESC());
    }
}
