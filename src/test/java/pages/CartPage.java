package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Button;
import wrappers.Text;

import java.util.List;

public class CartPage extends BasePage implements NavigationModule {

    private static final String
            REMOVE_BUTTON = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button";
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

    public CartPage open() {
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    @Override
    public CartPage isOpened() {
        wait.until(ExpectedConditions.visibilityOf(checkoutButton.getLocator()));
        return this;
    }

    public WebElement buttonCloseBurgerMenu() {
         return driver.findElement(BURGER_CLOSE_BUTTON);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void removeProduct(String product) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, product))).click();
    }

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public int getCountOfProducts() {
        return driver.findElements(PRODUCTS_NAME).size();
    }

    public String getProductsName(int index) {
        List<WebElement> productsName = driver.findElements(PRODUCTS_NAME);
        return productsName.get(index).getText();
    }

    public Double getProductPrice(int index) {
        List<WebElement> productsName = driver.findElements(PRODUCTS_PRICE);
        return Double.parseDouble(productsName.get(index)
                .getText()
                .substring(productsName.get(index)
                        .getText()
                        .indexOf('$') + 1));
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
