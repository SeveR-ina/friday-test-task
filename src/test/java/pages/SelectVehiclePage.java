package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectVehiclePage extends BasePage {

    @FindBy(name = "list")
    private WebElement openListButton;

    public SelectVehiclePage(WebDriver driver) {
        super(driver);
    }

    public void openCarList() {
        openListButton.click();
    }

    public void selectBrand(String brand) {
        driver.findElement
                        (By.xpath("//div[@class='SingleClickListField__optionGroup--1pULx']" +
                                "//span[text() = 'Alle Hersteller']" +
                                "/../..//label[contains(text(), '" +
                                brand +
                                "')]")).
                click();
    }
}
