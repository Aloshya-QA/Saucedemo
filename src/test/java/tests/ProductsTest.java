package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest{

    @Test
    public void sortProductsNameByReverseOrder() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.open();
        productsPage.sortProductsButton(1);
        assertEquals(productsPage.getListProductsName(), productsPage.getListProductsNameDESC());
    }

    @Test
    public void sortProductsPriceByLowToHigh() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.open();
        productsPage.sortProductsButton(2);
        assertEquals(productsPage.getListProductsPrice(), productsPage.getListProductsPriceASC());
    }

    @Test
    public void sortProductsPriceByHighToLow() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.open();
        productsPage.sortProductsButton(3);
        assertEquals(productsPage.getListProductsPrice(), productsPage.getListProductsPriceDESC());
    }
}
