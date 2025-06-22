package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Button;

@Log4j2
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
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(NAVIGATION_MODULE)));
            log.info("NavigationModule is opened");
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("NavigationModule isn't opened");
        }

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
            try {
                wait.until(ExpectedConditions.visibilityOf(burgerCloseButton.getLocator()));
                log.info("BurgerMenu is opened");
            } catch (TimeoutException e) {
                log.error(e.getMessage());
                Assert.fail("BurgerMenu isn't opened");
            }
            return this;
        }

        public BurgerMenu isClosed() {
            try {
                wait.until(ExpectedConditions.invisibilityOf(burgerCloseButton.getLocator()));
                log.info("BurgerMenu is closed");
            } catch (TimeoutException e) {
                log.error(e.getMessage());
                Assert.fail("BurgerMenu isn't closed");
            }

            return this;
        }

        public BurgerMenu open() {
            log.info("Opening BurgerMenu");
            burgerMenuButton.click();
            return this;
        }

        public BurgerMenu closeBurgerMenu() {
            log.info("Closing BurgerMenu");
            burgerCloseButton.click();
            return this;
        }

        public boolean isVisibility() {
            return burgerCloseButton.getLocator().isDisplayed();
        }

        public ProductsPage clickAllItemsButton() {
            log.info("Click 'All Items' button from BurgerMenu");
            burgerAllItemsButton.click();
            return new ProductsPage(driver);
        }

        public LoginPage clickLogoutButton() {
            log.info("Click 'Log out' button from BurgerMenu");
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
            log.info("Opening cart");
            cartButton.click();
            return this;
        }

        public Cart isOpened() {
            try {
                wait.until(ExpectedConditions.textToBe(TITLE, "Your Cart"));
                log.info("Cart is opened");
            } catch (TimeoutException e) {
                log.error(e.getMessage());
                Assert.fail("Cart isn't opened");
            }
            return this;
        }
    }
}
