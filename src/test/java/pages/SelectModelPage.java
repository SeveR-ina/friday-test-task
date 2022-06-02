package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectModelPage extends BasePage {

    public SelectModelPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select model: {0}")
    public void selectModel(String model) {
        driver.findElement
                        (By.xpath("//div[@class='SingleClickListField__optionGroup--1pULx']" +
                                "//span[text() = 'Alle Modelle']" +
                                "/../..//label[contains(text(), '" +
                                model +
                                "')]")).
                click();
    }
}
