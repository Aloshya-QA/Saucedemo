package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsDetailTest extends BaseTest {

    @Test(testName = "Проверка открытия старницы подробного описания товара",
            groups = {"Regression"})
    @Description("Проверяет, что страница подробного описания товара" +
            " успешно открывается и отображает форму с деталями.")
    @Step("Открываем страницу товара: 'Test.allTheThings() T-Shirt (Red)'")
    public void checkOpenProductDetailPageSuccess() {
        loginStep.auth("standard_user", "secret_sauce");
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)")
                .isOpened();
        assertThat(productsDetailPage.getBackToProductsButtonText())
                .isEqualTo("Back to products");
    }

    @Test(testName = "Проверка кнопки добавления товара", groups = {"Regression"})
    @Description("Проверяет, что кнопка 'Add to cart' на странице деталей товара" +
            " корректно добавляет товар в корзину.")
    @Step("Нажимаем кнопку 'Add to cart' на странице товара")
    public void checkAddToCartButtonFromDetailsPage() {
        loginStep.auth("standard_user", "secret_sauce");
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)")
                .isOpened()
                .clickAddButton();
        cartPage.open()
                .isOpened();
        assertThat(cartPage.getProductsName())
                .contains("Test.allTheThings() T-Shirt (Red)");
    }

    @Test(testName = "Проверка кнопки удаления товара", groups = {"Regression"})
    @Description("Проверяет, что кнопка 'Remove' на странице деталей товара" +
            " корректно удаляет товар из корзины.")
    @Step("Нажимаем кнопку 'Remove' на странице товара")
    public void checkRemoveButtonFromDetailsPage() {
        SoftAssertions soft = new SoftAssertions();
        loginStep.auth("standard_user", "secret_sauce");
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)")
                .isOpened()
                .clickAddButton();
        cartPage.open()
                .isOpened();
        soft.assertThat(cartPage.getCountOfProducts()).isEqualTo(1);
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)")
                .isOpened()
                .clickRemoveButton();
        cartPage.open()
                .isOpened();
        soft.assertThat(cartPage.getCountOfProducts())
                .isEqualTo(0);
        soft.assertAll();
    }

    @Test(testName = "Проверка кнопки назад", groups = {"Regression"})
    @Description("Проверяет, что кнопка 'Back to products' на странице деталей товара" +
            " возвращает юзера на страницу Products.")
    @Step("Нажимаем кнопку 'Back to products' на странице товара")
    public void checkBackToProductsButtonFromDetailsPage() {
        loginStep.auth("standard_user", "secret_sauce");
        productsDetailPage.open("Test.allTheThings() T-Shirt (Red)")
                .isOpened()
                .clickBackToProductsButton()
                .isOpened();
        assertThat(productsPage.getTitle())
                .isEqualTo("Products");
    }
}
