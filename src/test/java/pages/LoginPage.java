package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Button;
import wrappers.Input;
import wrappers.Text;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final Input
            userNameField = new Input(driver, "user-name"),
            passwordField = new Input(driver, "password");

    private final Button
            loginButton = new Button(driver, "login-button");

    private final Text
            errorMessage = new Text(driver, "error");

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

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
