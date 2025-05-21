package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureUtils {
    @Attachment(value = "screenshot", type = "image/png")
    public static void takeScreenshot(WebDriver driver) {
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}