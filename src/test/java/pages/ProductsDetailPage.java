package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Button;

import java.util.Objects;

public class ProductsDetailPage extends BasePage implements NavigationModule{

    private static final String GET_ITEM_ID = "//div[text()='%s']/ancestor::a";
    private final WebElement
            detailsForm = driver.findElement(
                    By.xpath("//div[@class='inventory_details']"));

    private final Button
            addToCartButton = new Button(driver, "add-to-cart"),
            removeButton = new Button(driver, "remove");

    public ProductsDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isOpened() {
        wait.until(ExpectedConditions.visibilityOf(detailsForm));
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

    public void clickAddButton() {
        addToCartButton.click();
    }

    public void clickRemoveButton() {
        removeButton.click();
    }
}
