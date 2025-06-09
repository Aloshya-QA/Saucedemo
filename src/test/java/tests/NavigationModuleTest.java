package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationModuleTest extends BaseTest{

    @Test(testName = "Проверка кнопки бургер меню", groups = {"Regression"})
    @Description("Проверяет, что при нажатии на бургер-меню в корзине" +
            " оно успешно открывается")
    @Step("Ожидаем открытия бургер-меню")
    public void checkOpenBurgerMenu() {
        loginStep.auth("standard_user", "secret_sauce");
        navigationModule.burgerMenu()
                .open()
                .isOpened();
        assertThat(navigationModule.burgerMenu().isVisibility()).isTrue();
    }

    @Test(testName = "Проверка кнопки зыкрывающей бургер меню", groups = {"Regression"})
    @Description("Проверяет, что при нажатии кнопки 'X' в бургер-меню" +
            " оно закрывается")
    @Step("Ожидаем закрытия бургер-меню")
    public void checkCloseBurgerMenu() {
        loginStep.auth("standard_user", "secret_sauce");
        navigationModule.burgerMenu()
                .open()
                .isOpened()
                .closeBurgerMenu()
                .isClosed();
        assertThat(navigationModule.burgerMenu().isVisibility())
                .isFalse();
    }

    @Test(testName = "Проверка кнопки 'Logout' в бургер меню", groups = {"Regression"})
    @Description("Проверяет, что при нажатии кнопки 'Logout' в бургер-меню" +
            " происходит логаут")
    @Step("Ожидаем логаут юзера")
    public void checkLogoutButtonFromBurgerMenu() {
        loginStep.auth("standard_user", "secret_sauce");
        navigationModule.burgerMenu()
                .open()
                .isOpened()
                .clickLogoutButton()
                .isOpened();
        assertThat(loginPage.isLoginButtonVisible())
                .isTrue();
    }

    @Test(testName = "Проверка кнопки 'All Items' в бургер меню", groups = {"Regression"})
    @Description("Проверяет, что при нажатии кнопки All Items' в бургер-меню" +
            " открывается страница товаров")
    @Step("Ожидаем переход на страницу товаров")
    public void checkAllItemsButtonFromBurgerMenu() {
        loginStep.auth("standard_user", "secret_sauce");
        navigationModule.burgerMenu()
                .open()
                .isOpened()
                .clickAllItemsButton()
                .isOpened();
        assertThat(productsPage.getTitle())
                .isEqualTo("Products");
    }

    @Test(testName = "Проверка кнопки перехода в корзину", groups = {"Smoke"})
    @Description("Проверяет, что при нажатии кнопки 'Cart' открывается корзина")
    @Step("Ожидаем переход в корзину")
    public void checkOpenCart() {
        loginStep.auth("standard_user", "secret_sauce");
        navigationModule.cart()
                .open()
                .isOpened();
        assertThat(cartPage.getTitle())
                .isEqualTo("Your Cart");
    }
}
