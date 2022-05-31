package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectBodyTypePage extends BasePage {

    public SelectBodyTypePage(WebDriver driver) {
        super(driver);
    }

    public void selectShape(String shape) {
        driver.findElement(By.xpath("//label[contains(text(), '" + shape + "')]")).click();
    }
}
