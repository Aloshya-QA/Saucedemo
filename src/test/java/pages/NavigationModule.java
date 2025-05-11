package pages;

import org.openqa.selenium.By;

public interface NavigationModule {

    By BURGER_MENU_BUTTON = By.xpath(
            "//button[@id='react-burger-menu-btn']");
    By BURGER_CLOSE_BUTTON = By.xpath(
            "//div[@class='bm-menu-wrap']/descendant::button[@id='react-burger-cross-btn']");
    By BURGER_ALL_ITEMS_BUTTON = By.id("inventory_sidebar_link");
    By BURGER_ABOUT_BUTTON = By.id("about_sidebar_link");
    By BURGER_LOGOUT_BUTTON = By.id("logout_sidebar_link");
    By BURGER_RESET_BUTTON = By.id("reset_sidebar_link");
    By BURGER_MENU = By.className("bm-menu-wrap");
    By CART_MENU_BUTTON = By.className("shopping_cart_link");

    void openCart();
    void openBurgerMenu();
    void closeBurgerMenu();
    void clickLogoutFromBurgerMenu();
    void clickAllItemsFromBurgerMenu();
    void clickAboutFromBurgerMenu();
    void clickResetFromBurgerMenu();
    boolean isVisibleBurgerMenu();
}
