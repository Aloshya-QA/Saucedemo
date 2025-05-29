package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import wrappers.Text;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductsPage extends BasePage implements NavigationModule{

    private static final String ADD_TO_CART_BUTTON =
            "//*[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";
    private static final By PRODUCTS_NAME = By.cssSelector(".inventory_item_name "),
            PRODUCTS_PRICE = By.cssSelector(".inventory_item_price"),
            SORT_OPTIONS_BUTTON = By.cssSelector(".product_sort_container");

    private final Text title = new Text(driver, "title");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return title.getText();
    }

    public ProductsPage open() {
        driver.get(BASE_URL + "inventory.html");
        return this;
    }

    @Override
    public ProductsPage isOpened() {
//        wait.until(ExpectedConditions.attributeContains(
//                title.getLocator(),
//                "textContent",
//                "Products"));
        wait.until(ExpectedConditions.visibilityOf(title.getLocator()));
        return this;
    }

    public void addProduct(String... products) {
        for (String product : products) {
            driver.findElement(By.xpath(String.format(ADD_TO_CART_BUTTON, product))).click();
        }
    }

    public void sortProductsButton(int option) {
        Select select = new Select(driver.findElement(SORT_OPTIONS_BUTTON));
        select.getOptions().get(option).click();

    }

    public List<String> getListProductsName() {
        List<WebElement> products = driver.findElements(PRODUCTS_NAME);
        List<String> productsName = new ArrayList<>();
        for (WebElement webElement : products) {
            productsName.add(webElement.getText());
        }

        return productsName;
    }

    public List<Double> getListProductsPrice() {
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

    public List<Double> getListProductsPriceASC() {
        List<WebElement> products = driver.findElements(PRODUCTS_PRICE);
        List<Double> productsPrice = new ArrayList<>();
        for (WebElement webElement : products) {
            productsPrice.add(Double.parseDouble(webElement
                    .getText()
                    .substring(webElement
                            .getText()
                            .indexOf('$') + 1)));
        }
        productsPrice.sort(null);

        return productsPrice;
    }

    public List<Double> getListProductsPriceDESC() {
        List<WebElement> products = driver.findElements(PRODUCTS_PRICE);
        List<Double> productsPrice = new ArrayList<>();
        for (WebElement webElement : products) {
            productsPrice.add(Double.parseDouble(webElement
                    .getText()
                    .substring(webElement
                            .getText()
                            .indexOf('$') + 1)));
        }
        productsPrice.sort(Comparator.reverseOrder());

        return productsPrice;
    }

    public List<String> getListProductsNameDESC() {
        List<WebElement> products = driver.findElements(PRODUCTS_NAME);
        List<String> productsName = new ArrayList<>();
        for (WebElement webElement : products) {
            productsName.add(webElement.getText());
        }
        productsName.sort(Comparator.reverseOrder());

        return productsName;
    }
}
