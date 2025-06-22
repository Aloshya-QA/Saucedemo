package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;

@Log4j2
public class CheckoutStep {

    WebDriver driver;
    CheckoutPage checkoutPage;

    public CheckoutStep(WebDriver driver) {
        this.driver = driver;
        checkoutPage = new CheckoutPage(driver);
    }

    public void completeOrder(String firstName, String lastName, String postalCode) {
        log.info("Successful order with credential: '{}', '{}', '{}'", firstName, lastName, postalCode);
        checkoutPage.open()
                .enterCheckoutInfo(firstName, lastName, postalCode)
                .isOpened()
                .clickFinishButton()
                .isOpened();
    }
}
