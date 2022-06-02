package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectBodyTypePage extends BasePage {

    public SelectBodyTypePage(WebDriver driver) {
        super(driver);
    }

    @Step("Select {0} shape of car model")
    public void selectShape(String shape) {
        driver.findElement(By.xpath("//label[contains(text(), '" + shape + "')]")).click();
    }
}
