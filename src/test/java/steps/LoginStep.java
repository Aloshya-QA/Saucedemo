package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

@Log4j2
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
        log.info("Authorization with credential: '{}', '{}'", userName, password);
        loginPage.open()
                .isOpened()
                .login(userName, password)
                .isOpened();
    }
}
