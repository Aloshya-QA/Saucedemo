package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage implements NavigationModule{

    private static final By FIRST_NAME_INPUT = By.xpath(
            "//div[@class='checkout_info']/descendant::input[@id='first-name']"),
            LAST_NAME_INPUT = By.xpath(
            "//div[@class='checkout_info']/descendant::input[@id='last-name']"),
            POSTAL_CODE_INPUT = By.xpath(
            "//div[@class='checkout_info']/descendant::input[@id='postal-code']"),
            CONTINUE_BUTTON = By.xpath(
            "//div[@class='checkout_buttons']/descendant::input[@id='continue']"),
            CANCEL_BUTTON = By.xpath(
            "//button[@id='cancel']"),
            ERROR_MESSAGE = By.xpath(
                    "//div[@class='checkout_info']/descendant::h3[@data-test='error']"),
            FINISH_BUTTON = By.xpath("//button[@id='finish']"),
            BACK_HOME_BUTTON = By.xpath("//button[@id='back-to-products']"),
            TOTAL_PRICE_FIELD = By.xpath(
                    "//div[@class='summary_info']/descendant::div[@class='summary_total_label']"),
            TOTAL_PRODUCTS_PRICE_FIELD = By.xpath(
                    "//div[@class='summary_info']/descendant::div[@class='summary_subtotal_label']"),
            TAX_PRICE_FIELD = By.xpath(
                    "//div[@class='summary_info']/descendant::div[@class='summary_tax_label']");

    public CheckoutPage(WebDriver driver) {
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

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void open() {
        driver.get(BASE_URL + "checkout-step-one.html");
    }

    public void enterCheckoutInfo(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_INPUT).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public void clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public void clickBackHomeButton() {
        driver.findElement(BACK_HOME_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public Double getProductsTotalPrice() {
        return Double.parseDouble(driver.findElement(TOTAL_PRODUCTS_PRICE_FIELD)
                .getText()
                .substring(driver.findElement(TOTAL_PRODUCTS_PRICE_FIELD)
                        .getText()
                        .indexOf('$') + 1));
    }

    public Double getTaxPrice() {
        return Double.parseDouble(driver.findElement(TAX_PRICE_FIELD)
                .getText()
                .substring(driver.findElement(TAX_PRICE_FIELD)
                        .getText()
                        .indexOf('$') + 1));
    }

    public Double getTotalPrice() {
        return Double.parseDouble(driver.findElement(TOTAL_PRICE_FIELD)
                .getText()
                .substring(driver.findElement(TOTAL_PRICE_FIELD)
                        .getText()
                        .indexOf('$') + 1));
    }
}
