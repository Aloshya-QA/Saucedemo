package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsDetailTest extends BaseTest {

    @Test(testName = "Проверка открытия старницы подробного описания товара",
            groups = {"Regression"})
    @Description("Проверяет, что страница подробного описания товара" +
            " успешно открывается и отображает форму с деталями.")
    @Step("Открываем страницу товара: 'Test.allTheThings() T-Shirt (Red)'")
    public void checkOpenProductDetailPageSuccess() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)");
        assertTrue(productsDetailPage.isVisibleDetailsForm());
    }

    @Test(testName = "Проверка кнопки добавления товара", groups = {"Regression"})
    @Description("Проверяет, что кнопка 'Add to cart' на странице деталей товара" +
            " корректно добавляет товар в корзину.")
    @Step("Нажимаем кнопку 'Add to cart' на странице товара")
    public void checkAddToCartButtonFromDetailsPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)");
        productsDetailPage.clickAddButton();
        productsDetailPage.openCart();
        assertEquals(cartPage.getProductsName(0), "Test.allTheThings() T-Shirt (Red)");
    }

    @Test(testName = "Проверка кнопки удаления товара", groups = {"Regression"})
    @Description("Проверяет, что кнопка 'Remove' на странице деталей товара" +
            " корректно удаляет товар из корзины.")
    @Step("Нажимаем кнопку 'Remove' на странице товара")
    public void checkRemoveButtonFromDetailsPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)");
        productsDetailPage.clickAddButton();
        productsDetailPage.clickRemoveButton();
        productsDetailPage.openCart();
        assertEquals(cartPage.getCountOfProducts(), 0);
    }
}
