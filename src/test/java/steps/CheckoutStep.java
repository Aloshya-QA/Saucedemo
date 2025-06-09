package steps;

import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;

public class CheckoutStep {

    WebDriver driver;
    CheckoutPage checkoutPage;

    public CheckoutStep(WebDriver driver) {
        this.driver = driver;
        checkoutPage = new CheckoutPage(driver);
    }

    public void completeOrder(String firstName, String lastName, String postalCode) {
        checkoutPage.open()
                .enterCheckoutInfo(firstName, lastName, postalCode)
                .isOpened()
                .clickFinishButton()
                .isOpened();
    }
}
