package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Button;
import wrappers.Input;
import wrappers.Label;

import java.util.Objects;

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
        return driver.findElement(TITLE).getText();
    }

    public CheckoutPage open() {
        driver.get(BASE_URL + "checkout-step-one.html");
        return this;
    }

    @Override
    public CheckoutPage isOpened() {
        if (Objects.requireNonNull(driver.getCurrentUrl()).contains("checkout-step-one.html")) {
            wait.until(ExpectedConditions.textToBe(TITLE, "Checkout: Your Information"));
        } else if (Objects.requireNonNull(driver.getCurrentUrl()).contains("checkout-step-two.html")) {
            wait.until(ExpectedConditions.textToBe(TITLE, "Checkout: Overview"));
        } else if (Objects.requireNonNull(driver.getCurrentUrl()).contains("checkout-complete.html")) {
            wait.until(ExpectedConditions.textToBe(TITLE, "Checkout: Complete!"));
        }

        return this;
    }

    public CheckoutPage enterCheckoutInfo(String firstName, String lastName, String postalCode) {
        firstNameField.fill(firstName);
        lastNameField.fill(lastName);
        postalCodeField.fill(postalCode);
        continueButton.click();
        return this;
    }

    public CartPage clickCancelButton() {
        cancelButton.click();
        return new CartPage(driver);
    }

    public ProductsPage clickCancelSecondButton() {
        cancelButton.click();
        return new ProductsPage(driver);
    }

    public CheckoutPage clickFinishButton() {
        finishButton.click();
        return this;
    }

    public ProductsPage clickBackHomeButton() {
        backHomeButton.click();
        return new ProductsPage(driver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public Double getProductsTotalPrice() {
        return Double.parseDouble(totalProductPrice
                .getText()
                .substring(totalProductPrice
                        .getText()
                        .indexOf('$') + 1));
    }

    public Double getTaxPrice() {
        return Double.parseDouble(taxPrice
                .getText()
                .substring(taxPrice
                        .getText()
                        .indexOf('$') + 1));
    }

    public Double getTotalPrice() {
        return Double.parseDouble(totalPrice
                .getText()
                .substring(totalPrice
                        .getText()
                        .indexOf('$') + 1));
    }
}
