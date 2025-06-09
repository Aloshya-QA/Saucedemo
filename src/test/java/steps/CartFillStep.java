package steps;

import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.ProductsPage;

public class CartFillStep {

    WebDriver driver;
    ProductsPage productsPage;
    CartPage cartPage;

    public CartFillStep(WebDriver driver) {
        this.driver = driver;
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    public void openCartWithItems(String... productsName) {
        productsPage.addProduct(productsName);
        cartPage.open()
                .isOpened();
    }
}
