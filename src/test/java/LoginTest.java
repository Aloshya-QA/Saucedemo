import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {
    @Test
    public void checkShoppingCartItemValues() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.xpath(
                "//input[@data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        driver.findElement(By.xpath(
                "//button[@data-test='add-to-cart-sauce-labs-bike-light']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        String itemName = driver.findElement(By.xpath(
                "//div[@data-test='inventory-item-name']")).getText();
        String itemPrice = driver.findElement(By.xpath(
                "//div[@data-test='inventory-item-price']")).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(itemName, "Sauce Labs Bike Light");
        softAssert.assertEquals(itemPrice, "$9.99");
        softAssert.assertAll();
    }
}
