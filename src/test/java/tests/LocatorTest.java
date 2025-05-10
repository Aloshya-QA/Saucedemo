package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class LocatorTest extends BaseTest {

    @Test
    public void checkLocator() {
        driver.findElement(By.id("user-name"));
        driver.findElement(By.name("password"));
        driver.findElement(By.className("submit-button"));
        driver.findElement(By.partialLinkText("Sauce Labs Bike"));
        driver.findElement(By.cssSelector("button[id=add-to-cart]"));
        driver.findElement(By.linkText("1"));

        driver.findElement(By.xpath("//div[@data-test='inventory-item-price']"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']"));
        driver.findElement(By.xpath("//a[contains(@data-test,'item')]"));
        driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs')]"));
        driver.findElement(By.xpath("//*[@id='item_0_title_link']//ancestor::a"));
        driver.findElement(By.xpath("//*[@id='item_0_title_link']//descendant::div"));
        driver.findElement(By.xpath("//*[@class='cart_quantity']//following::a"));
        driver.findElement(By.xpath("//*[contains(text(),'Sauce')]//parent::div"));
        driver.findElement(By.xpath(
                "//*[@class='inventory_item_name' and @data-test='inventory-item-name']"));

        driver.findElement(By.cssSelector(".inventory_item_name"));
        driver.findElement(By.cssSelector(".cart_item_label .inventory_item_name"));
        driver.findElement(By.cssSelector("#item_0_title_link"));
        driver.findElement(By.cssSelector("div.inventory_item_name"));
        driver.findElement(By.cssSelector("[id='item_0_title_link']"));
        driver.findElement(By.cssSelector("[id~='item_0_title_link']"));
        driver.findElement(By.cssSelector("[data-test|='item-0']"));
        driver.findElement(By.cssSelector("[data-test^='item-0']"));
        driver.findElement(By.cssSelector("[data-test$='-name']"));
        driver.findElement(By.cssSelector("[data-test*='-0-']"));
    }
}
