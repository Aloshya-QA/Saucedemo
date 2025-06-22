package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Input {

    WebDriver driver;
    String label;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void fill(String text) {
        log.info("Filling {} into {}", text, label);
        driver.findElement(By.id(label)).sendKeys(text);
    }
}
