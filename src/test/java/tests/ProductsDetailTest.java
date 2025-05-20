package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsDetailTest extends BaseTest {

    @Test(testName = "Проверка открытия старницы подробного описания товара",
            groups = {"Regression"})
    public void checkOpenProductDetailPageSuccess() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)");
        assertTrue(productsDetailPage.isVisibleDetailsForm());
    }

    @Test(testName = "Проверка кнопки добавления товара", groups = {"Regression"})
    public void checkAddToCartButtonFromDetailsPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)");
        productsDetailPage.clickAddButton();
        productsDetailPage.openCart();
        assertEquals(cartPage.getProductsName(0), "Test.allTheThings() T-Shirt (Red)");
    }

    @Test(testName = "Проверка кнопки удаления товара", groups = {"Regression"})
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
