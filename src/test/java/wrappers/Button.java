package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class Button {

    WebDriver driver;
    String label;

    public Button(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void click() {
        log.info("Click {} button", label);
        driver.findElement(By.id(label)).click();
    }

    public WebElement getLocator() {
        return driver.findElement(By.id(label));
    }
}
