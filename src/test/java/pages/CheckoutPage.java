package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Button;
import wrappers.Input;
import wrappers.Label;

import java.util.Objects;

@Log4j2
public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private final Input
            firstNameField = new Input(driver, "first-name"),
            lastNameField = new Input(driver, "last-name"),
            postalCodeField = new Input(driver, "postal-code");

    private final Button
            cancelButton = new Button(driver, "cancel"),
            continueButton = new Button(driver, "continue"),
            finishButton = new Button(driver, "finish"),
            backHomeButton = new Button(driver, "back-to-products");

    private final Label
            errorMessage = new Label(driver, "error"),
            totalPrice = new Label(driver, "total-label"),
            totalProductPrice = new Label(driver, "subtotal-label"),
            taxPrice = new Label(driver, "tax-label");

    public String getTitle() {
        log.info("Get CheckoutPage title");
        return driver.findElement(TITLE).getText();
    }

    public CheckoutPage open() {
        log.info("Opening CheckoutPage");
        driver.get(BASE_URL + "checkout-step-one.html");
        return this;
    }

    @Override
    public CheckoutPage isOpened() {
        try {
            if (Objects.requireNonNull(driver.getCurrentUrl()).contains("checkout-step-one.html")) {
                wait.until(ExpectedConditions.textToBe(TITLE, "Checkout: Your Information"));
            } else if (Objects.requireNonNull(driver.getCurrentUrl()).contains("checkout-step-two.html")) {
                wait.until(ExpectedConditions.textToBe(TITLE, "Checkout: Overview"));
            } else if (Objects.requireNonNull(driver.getCurrentUrl()).contains("checkout-complete.html")) {
                wait.until(ExpectedConditions.textToBe(TITLE, "Checkout: Complete!"));
            }
            log.info("CheckoutPage is opened");
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("CheckoutPage isn't opened");
        }

        return this;
    }

    public CheckoutPage enterCheckoutInfo(String firstName, String lastName, String postalCode) {
        log.info("Filling checkout info with credential: {}, {}, {}", firstName, lastName, postalCode);
        firstNameField.fill(firstName);
        lastNameField.fill(lastName);
        postalCodeField.fill(postalCode);
        continueButton.click();
        return this;
    }

    public CartPage clickCancelButton() {
        log.info("Click 'Cancel' button");
        cancelButton.click();
        return new CartPage(driver);
    }

    public ProductsPage clickCancelSecondButton() {
        log.info("Click 'Cancel' button");
        cancelButton.click();
        return new ProductsPage(driver);
    }

    public CheckoutPage clickFinishButton() {
        log.info("Click 'Finish' button");
        finishButton.click();
        return this;
    }

    public ProductsPage clickBackHomeButton() {
        log.info("Click 'Back to Home' button");
        backHomeButton.click();
        return new ProductsPage(driver);
    }

    public String getErrorMessage() {
        log.info("Get error message");
        return errorMessage.getText();
    }

    public Double getProductsTotalPrice() {
        log.info("Get products total price");
        return Double.parseDouble(totalProductPrice
                .getText()
                .substring(totalProductPrice
                        .getText()
                        .indexOf('$') + 1));
    }

    public Double getTaxPrice() {
        log.info("Get products tax price");
        return Double.parseDouble(taxPrice
                .getText()
                .substring(taxPrice
                        .getText()
                        .indexOf('$') + 1));
    }

    public Double getTotalPrice() {
        log.info("Get total price");
        return Double.parseDouble(totalPrice
                .getText()
                .substring(totalPrice
                        .getText()
                        .indexOf('$') + 1));
    }
}
