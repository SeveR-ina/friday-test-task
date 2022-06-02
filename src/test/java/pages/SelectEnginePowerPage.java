package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectEnginePowerPage extends BasePage {

    public SelectEnginePowerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select engine power: {0}")
    public void selectEP(String ep) {
        driver.findElement(By.xpath("//label[contains(text(), '" + ep + "')]")).click();
    }
}
