package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button {

    WebDriver driver;
    String label;

    public Button(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void click() {
        driver.findElement(By.id(label)).click();
    }

    public WebElement getLocator() {
        return driver.findElement(By.id(label));
    }
}
