package steps;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginStep {

    private static final Logger log = LoggerFactory.getLogger(LoginStep.class);
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    public void auth(String userName, String password) {
        loginPage.open()
                .isOpened()
                .login(userName, password)
                .isOpened();
    }
}
