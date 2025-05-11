package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage implements NavigationModule {

    private static final String REMOVE_BUTTON = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button";
    private static final By PRODUCTS_NAME = By.xpath(
            "//div[@class= 'cart_item']/descendant::div[@class = 'inventory_item_name']");
    private static final By PRODUCTS_PRICE = By.xpath(
            "//div[@class= 'cart_item']/descendant::div[@class = 'inventory_item_price']");
    private static final By CONTINUE_SHOPPING_BUTTON = By.xpath(
            "//div[@class = 'cart_footer']/button[@id = 'continue-shopping']");
    private static final By CHECKOUT_BUTTON = By.xpath(
            "//div[@class = 'cart_footer']/button[@id = 'checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openCart() {
        driver.findElement(CART_MENU_BUTTON).click();
    }

    @Override
    public void openBurgerMenu() {
        driver.findElement(BURGER_MENU_BUTTON).click();
    }

    @Override
    public void closeBurgerMenu() {
        driver.findElement(BURGER_CLOSE_BUTTON).click();
    }

    @Override
    public void clickLogoutFromBurgerMenu() {
        driver.findElement(BURGER_LOGOUT_BUTTON).click();
    }

    @Override
    public void clickAllItemsFromBurgerMenu() {
        driver.findElement(BURGER_ALL_ITEMS_BUTTON).click();
    }

    @Override
    public void clickAboutFromBurgerMenu() {
        driver.findElement(BURGER_ABOUT_BUTTON).click();
    }

    @Override
    public void clickResetFromBurgerMenu() {
        driver.findElement(BURGER_RESET_BUTTON).click();
    }

    @Override
    public boolean isVisibleBurgerMenu() {
        return driver.findElement(BURGER_MENU).isDisplayed();
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void removeProduct(String product) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, product))).click();
    }

    public void clickContinueShoppingButton() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
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
