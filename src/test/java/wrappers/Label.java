package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class Label {

    WebDriver driver;
    String label;

    public Label(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public String getText() {
        log.info("Get text from {}", label);
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
