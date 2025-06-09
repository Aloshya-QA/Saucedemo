package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Button;

import java.util.Objects;

public class ProductsDetailPage extends BasePage {

    private static final String GET_ITEM_ID = "//div[text()='%s']/ancestor::a";

    private final Button
            addToCartButton = new Button(driver, "add-to-cart"),
            backToProductsButton = new Button(driver, "back-to-products"),
            removeButton = new Button(driver, "remove");

    public ProductsDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductsDetailPage isOpened() {
        wait.until(ExpectedConditions.visibilityOf(backToProductsButton.getLocator()));
        return this;
    }

    public ProductsDetailPage open(String productName) {
        String id = Objects.requireNonNull(driver.findElement(
                                By.xpath(String.format(GET_ITEM_ID, productName)))
                        .getDomAttribute("id"))
                .substring(5);

        driver.get(String.format(BASE_URL + "inventory-item.html?id=%s", id));
        return this;
    }

    public ProductsDetailPage clickAddButton() {
        addToCartButton.click();
        return this;
    }

    public ProductsDetailPage clickRemoveButton() {
        removeButton.click();
        return this;
    }

    public ProductsPage clickBackToProductsButton() {
        backToProductsButton.click();
        return new ProductsPage(driver);
    }

    public String getBackToProductsButtonText() {
        return backToProductsButton.getLocator().getText();
    }
}
