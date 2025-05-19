package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class ProductsDetailPage extends BasePage implements NavigationModule{

    private static final String GET_ITEM_ID = "//div[text()='%s']/ancestor::a";
    private static final By DETAILS_FORM = By.xpath("//div[@class='inventory_details']");
    private static final By ADD_TO_CART_BUTTON = By.id("add-to-cart");
    private static final By REMOVE_BUTTON = By.id("remove");

    public ProductsDetailPage(WebDriver driver) {
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

    public boolean isVisibleDetailsForm() {
        return driver.findElement(DETAILS_FORM).isDisplayed();
    }

    public void open(String productName) {
        String id = Objects.requireNonNull(driver.findElement(
                                By.xpath(String.format(GET_ITEM_ID, productName)))
                        .getDomAttribute("id"))
                .substring(5);

        driver.get(String.format(BASE_URL + "inventory-item.html?id=%s", id));
    }

    public void clickAddButton() {
        driver.findElement(ADD_TO_CART_BUTTON).click();
    }

    public void clickRemoveButton() {
        driver.findElement(REMOVE_BUTTON).click();
    }
}
