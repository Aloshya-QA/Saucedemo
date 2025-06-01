package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Button;

public class NavigationModule extends BasePage {

    private static final By NAVIGATION_MODULE = By.cssSelector("[data-test='primary-header']");

    public NavigationModule(WebDriver driver) {
        super(driver);
    }

    public BurgerMenu burgerMenu() {
        return new BurgerMenu();
    }

    public Cart cart() {
        return new Cart();
    }

    @Override
    public NavigationModule isOpened() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(NAVIGATION_MODULE)));
        return this;
    }

    public class BurgerMenu{

        private final Button
                burgerMenuButton = new Button(driver, "react-burger-menu-btn"),
                burgerCloseButton = new Button(driver, "react-burger-cross-btn"),
                burgerAllItemsButton = new Button(driver, "inventory_sidebar_link"),
                burgerAboutButton = new Button(driver, "about_sidebar_link"),
                burgerLogoutButton = new Button(driver, "logout_sidebar_link"),
                burgerResetButton = new Button(driver, "reset_sidebar_link");

        public BurgerMenu isOpened() {
            wait.until(ExpectedConditions.visibilityOf(burgerCloseButton.getLocator()));
            return this;
        }

        public BurgerMenu isClosed() {
            wait.until(ExpectedConditions.invisibilityOf(burgerCloseButton.getLocator()));
            return this;
        }

        public BurgerMenu open() {
            burgerMenuButton.click();
            return this;
        }

        public BurgerMenu closeBurgerMenu() {
            burgerCloseButton.click();
            return this;
        }

        public boolean isVisibility() {
            return burgerCloseButton.getLocator().isDisplayed();
        }

        public ProductsPage clickAllItemsButton() {
            burgerAllItemsButton.click();
            return new ProductsPage(driver);
        }

        public LoginPage clickLogoutButton() {
            burgerLogoutButton.click();
            return new LoginPage(driver);
        }

        public void clickResetButton() {
            burgerResetButton.click();
        }

        public void clickAboutButton() {
            burgerAboutButton.click();
        }

    }

    public class Cart {

        private final Button
                cartButton = new Button(driver, "shopping_cart_container");

        public Cart open() {
            cartButton.click();
            return this;
        }

        public Cart isOpened() {
            wait.until(ExpectedConditions.textToBe(TITLE, "Your Cart"));
            return this;
        }
    }
}
