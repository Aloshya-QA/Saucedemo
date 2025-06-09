package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Button;

import java.util.ArrayList;
import java.util.List;

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
        return driver.findElement(TITLE).getText();
    }

    public CartPage open() {
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    @Override
    public CartPage isOpened() {
        wait.until(ExpectedConditions.visibilityOf(checkoutButton.getLocator()));
        return this;
    }

    public void removeProduct(String product) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, product))).click();
    }

    public ProductsPage clickContinueShoppingButton() {
        continueShoppingButton.click();
        return new ProductsPage(driver);
    }

    public CheckoutPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    public int getCountOfProducts() {
        return driver.findElements(PRODUCTS_NAME).size();
    }

    public List<String> getProductsName() {
        List<WebElement> productsName = driver.findElements(PRODUCTS_NAME);
        List<String> listProductsName = new ArrayList<>();
        for (WebElement webElement : productsName) {
            listProductsName.add(webElement.getText());
        }

        return listProductsName;
    }

    public Double getProductPrice(String productName) {
        String productPrice = driver.findElement(
                By.xpath(
                        String.format(PRODUCT_PRICE, productName))).getText();
        return Double.parseDouble(
                productPrice.substring(
                        productPrice.indexOf('$') + 1));
    }

    public Double getProductTotalPrice() {
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
