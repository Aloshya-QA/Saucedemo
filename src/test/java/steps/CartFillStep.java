package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.ProductsPage;

@Log4j2
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
        log.info("Open cart with products: {}", (Object) productsName);
        productsPage.addProduct(productsName);
        cartPage.open()
                .isOpened();
    }
}
