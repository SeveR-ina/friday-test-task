package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectEnginePowerPage extends BasePage {

    public SelectEnginePowerPage(WebDriver driver) {
        super(driver);
    }

    public void selectEP(String ep) {
        driver.findElement(By.xpath("//label[contains(text(), '" + ep + "')]")).click();
    }
}
