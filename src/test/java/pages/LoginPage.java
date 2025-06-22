package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Button;
import wrappers.Input;
import wrappers.Label;

@Log4j2
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
        log.info("Opening LoginPage");
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton.getLocator()));
            log.info("LoginPage is opened");
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("LoginPage isn't opened");
        }

        return this;
    }

    public ProductsPage login(String user, String password) {
        log.info("Log in with credential: '{}', '{}'", user, password);
        userNameField.fill(user);
        passwordField.fill(password);
        loginButton.click();
        return new ProductsPage(driver);
    }

    public boolean isLoginButtonVisible() {
        return loginButton.getLocator().isDisplayed();
    }

    public String getErrorMessage() {
        log.info("Get error message");
        return errorMessage.getText();
    }
}
