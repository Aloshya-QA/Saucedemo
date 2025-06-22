package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Button;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    private static final String
            REMOVE_BUTTON = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button",
            PRODUCT_PRICE = "//div[text() = '%s']" +
                    "/ancestor::div[@data-test='inventory-item']" +
                    "//div[@data-test='inventory-item-price']";

    private static final By
            PRODUCTS_NAME = By.xpath(
            "//div[@class= 'cart_item']/descendant::div[@class = 'inventory_item_name']"),
            PRODUCTS_PRICE = By.xpath(
            "//div[@class= 'cart_item']/descendant::div[@class = 'inventory_item_price']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final Button
            continueShoppingButton = new Button(driver, "continue-shopping"),
            checkoutButton = new Button(driver, "checkout");

    public String getTitle() {
        log.info("Get CartPage title");
        return driver.findElement(TITLE).getText();
    }

    public CartPage open() {
        log.info("Opening CartPage");
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    @Override
    public CartPage isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutButton.getLocator()));
            log.info("CartPage is opened");
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("CartPage isn't opened");
        }
        return this;
    }

    public void removeProduct(String product) {
        log.info("Remove product {} from cart", product);
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, product))).click();
    }

    public ProductsPage clickContinueShoppingButton() {
        log.info("Click 'Continue Shopping' button");
        continueShoppingButton.click();
        return new ProductsPage(driver);
    }

    public CheckoutPage clickCheckoutButton() {
        log.info("Click 'Checkout' button");
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    public int getCountOfProducts() {
        log.info("Get count of products from cart");
        return driver.findElements(PRODUCTS_NAME).size();
    }

    public List<String> getProductsName() {
        log.info("Get products name from cart");
        List<WebElement> productsName = driver.findElements(PRODUCTS_NAME);
        List<String> listProductsName = new ArrayList<>();
        for (WebElement webElement : productsName) {
            listProductsName.add(webElement.getText());
        }

        return listProductsName;
    }

    public Double getProductPrice(String productName) {
        log.info("Get products price from cart");
        String productPrice = driver.findElement(
                By.xpath(
                        String.format(PRODUCT_PRICE, productName))).getText();
        return Double.parseDouble(
                productPrice.substring(
                        productPrice.indexOf('$') + 1));
    }

    public Double getProductTotalPrice() {
        log.info("Get products total price from cart");
        List<WebElement> productsName = driver.findElements(PRODUCTS_PRICE);
        double totalPrice = 0.0;

        for (WebElement webElement : productsName) {
            totalPrice += Double.parseDouble(webElement
                    .getText()
                    .substring(webElement
                            .getText()
                            .indexOf('$') + 1));
        }

        return totalPrice;
    }
}
