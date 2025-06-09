package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Label {

    WebDriver driver;
    String label;

    public Label(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public String getText() {
        return driver.findElement(
                By.cssSelector(
                        String.format("[data-test='%s']", label))).getText();
    }

    public WebElement getLocator() {
        return driver.findElement(
                By.cssSelector(
                        String.format("[data-test='%s']", label)));
    }
}
