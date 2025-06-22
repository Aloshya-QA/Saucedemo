package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Log4j2
public class ProductsPage extends BasePage {

    private static final String
            ADD_TO_CART_BUTTON = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']" +
            "//button[text()='Add to cart']",
            REMOVE_BUTTON = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']" +
            "//button[text()='Remove']";

    private static final By
            PRODUCTS_NAME = By.cssSelector(".inventory_item_name "),
            PRODUCTS_PRICE = By.cssSelector(".inventory_item_price"),
            SORT_OPTIONS_BUTTON = By.cssSelector(".product_sort_container");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        log.info("Get ProductsPage title");
        return driver.findElement(TITLE).getText();
    }

    public ProductsPage open() {
        log.info("Opening ProductsPage");
        driver.get(BASE_URL + "inventory.html");
        return this;
    }

    @Override
    public ProductsPage isOpened() {
        try {
            wait.until(ExpectedConditions.textToBe(TITLE, "Products"));
            log.info("ProductsPage is opened");
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("ProductsPage isn't opened");
        }

        return this;
    }

    public ProductsPage addProduct(String... products) {
        log.info("Add products: {}", (Object) products);
        for (String product : products) {
            driver.findElement(By.xpath(String.format(ADD_TO_CART_BUTTON, product))).click();
        }

        return this;
    }

    public List<String> getNameAddedProducts() {
        log.info("Get products name");
        List<WebElement> cardsList = driver.findElements(By.xpath(
                "//button[text()='Remove']" +
                        "/ancestor::div[@data-test='inventory-item']" +
                        "//div[@data-test='inventory-item-name']"));
        List<String> productsName = new ArrayList<>();

        for (WebElement webElement : cardsList) {
            productsName.add(webElement.getText());
        }

        return productsName;
    }

    public ProductsPage removeProduct(String... products) {
        log.info("Remove products: {}", (Object) products);
        for (String product : products) {
            driver.findElement(By.xpath(String.format(REMOVE_BUTTON, product))).click();
        }

        return this;
    }

    public void sortProductsButton(String sortValue) {
        log.info("Click sort option '{}'", sortValue);
        Select select = new Select(driver.findElement(SORT_OPTIONS_BUTTON));
        int option = switch (sortValue.toLowerCase()) {
            case "z to a" -> 1;
            case "low to high" -> 2;
            case "high to low" -> 3;
            default -> 0;
        };

        select.getOptions().get(option).click();
    }

    public List<String> getListProductsName() {
        log.info("Get list products name");
        List<WebElement> products = driver.findElements(PRODUCTS_NAME);
        List<String> productsName = new ArrayList<>();

        for (WebElement webElement : products) {
            productsName.add(webElement.getText());
        }

        return productsName;
    }

    public List<Double> getListProductsPrice() {
        log.info("Get list products price");
        List<WebElement> products = driver.findElements(PRODUCTS_PRICE);
        List<Double> productsPrice = new ArrayList<>();

        for (WebElement webElement : products) {
            productsPrice.add(Double.parseDouble(webElement
                    .getText()
                    .substring(webElement
                            .getText()
                            .indexOf('$') + 1)));
        }

        return productsPrice;
    }

    public List<Double> getListProductsPrice(String sortValue) {
        log.info("Sort products price'{}'", sortValue);
        List<WebElement> products = driver.findElements(PRODUCTS_PRICE);
        List<Double> productsPrice = new ArrayList<>();

        if (sortValue.equalsIgnoreCase("low to high")) {
            for (WebElement webElement : products) {
                productsPrice.add(Double.parseDouble(webElement
                        .getText()
                        .substring(webElement
                                .getText()
                                .indexOf('$') + 1)));
            }
            productsPrice.sort(null);

        } else if (sortValue.equalsIgnoreCase("high to low")) {
            for (WebElement webElement : products) {
                productsPrice.add(Double.parseDouble(webElement
                        .getText()
                        .substring(webElement
                                .getText()
                                .indexOf('$') + 1)));
            }
            productsPrice.sort(Comparator.reverseOrder());
        }

        return productsPrice;
    }

    public List<String> getListProductsName(String sortValue) {
        log.info("Sort products name '{}'", sortValue);
        List<WebElement> products = driver.findElements(PRODUCTS_NAME);
        List<String> productsName = new ArrayList<>();

        if (sortValue.equalsIgnoreCase("A to Z")) {
            for (WebElement webElement : products) {
                productsName.add(webElement.getText());
            }
            productsName.sort(null);

        } else if (sortValue.equalsIgnoreCase("Z to A")) {
            for (WebElement webElement : products) {
                productsName.add(webElement.getText());
            }
            productsName.sort(Comparator.reverseOrder());
        }

        return productsName;
    }
}
