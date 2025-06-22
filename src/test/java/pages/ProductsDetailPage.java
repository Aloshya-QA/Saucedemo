package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Button;

import java.util.Objects;

@Log4j2
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
        try {
            wait.until(ExpectedConditions.visibilityOf(backToProductsButton.getLocator()));
            log.info("ProductsDetailPage is opened");
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("ProductsDetailPage isn't opened");
        }

        return this;
    }

    public ProductsDetailPage open(String productName) {
        log.info("Opening {} detail page", productName);
        String id = Objects.requireNonNull(driver.findElement(
                                By.xpath(String.format(GET_ITEM_ID, productName)))
                        .getDomAttribute("id"))
                .substring(5);

        driver.get(String.format(BASE_URL + "inventory-item.html?id=%s", id));
        return this;
    }

    public ProductsDetailPage clickAddButton() {
        log.info("Click 'Add' button from detail page");
        addToCartButton.click();
        return this;
    }

    public ProductsDetailPage clickRemoveButton() {
        log.info("Click 'Remove' button from detail page");
        removeButton.click();
        return this;
    }

    public ProductsPage clickBackToProductsButton() {
        log.info("Click 'Back To Products' button from detail page");
        backToProductsButton.click();
        return new ProductsPage(driver);
    }

    public String getBackToProductsButtonText() {
        return backToProductsButton.getLocator().getText();
    }
}
