package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Button;
import wrappers.Input;
import wrappers.Label;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final Input
            userNameField = new Input(driver, "user-name"),
            passwordField = new Input(driver, "password");

    private final Button
            loginButton = new Button(driver, "login-button");

    private final Label
            errorMessage = new Label(driver, "error");

    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isOpened() {
        wait.until(ExpectedConditions.visibilityOf(loginButton.getLocator()));
        return this;
    }

    public ProductsPage login(String user, String password) {
        userNameField.fill(user);
        passwordField.fill(password);
        loginButton.click();
        return new ProductsPage(driver);
    }

    public boolean isLoginButtonVisible() {
        return loginButton.getLocator().isDisplayed();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
