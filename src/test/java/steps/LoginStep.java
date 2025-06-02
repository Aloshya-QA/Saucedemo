package steps;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginStep {

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
