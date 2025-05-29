package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Button;
import wrappers.Input;
import wrappers.Text;

public class CheckoutPage extends BasePage implements NavigationModule {

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

    private final Text
            errorMessage = new Text(driver, "error"),
            totalPrice = new Text(driver, "error"),
            totalProductPrice = new Text(driver, "error"),
            taxPrice = new Text(driver, "error");

    public String getTitle() {
        return title.getText();
    }

    public CheckoutPage open() {
        driver.get(BASE_URL + "checkout-step-one.html");
        return this;
    }

    @Override
    public CheckoutPage isOpened() {
        wait.until(ExpectedConditions.attributeContains(title.getLocator(), "span", ""));
        return this;
    }

    public void enterCheckoutInfo(String firstName, String lastName, String postalCode) {
        firstNameField.fill(firstName);
        lastNameField.fill(lastName);
        postalCodeField.fill(postalCode);
        continueButton.click();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickFinishButton() {
        finishButton.click();
    }

    public void clickBackHomeButton() {
        backHomeButton.click();
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
