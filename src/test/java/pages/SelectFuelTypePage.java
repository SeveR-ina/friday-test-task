package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectFuelTypePage extends BasePage {

    public SelectFuelTypePage(WebDriver driver) {
        super(driver);
    }

    public void selectFuel(String fuel) {
        driver.findElement(By.xpath("//label[contains(text(), '" + fuel + "')]")).click();
    }
}
