package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectEnginePage extends BasePage {
    @FindBy(xpath = "//div[@class='SingleClickListField__infoBlock--IEVkY']//p")
    private WebElement hsnTsnElement;

    public SelectEnginePage(WebDriver driver) {
        super(driver);
    }

    public void selectHsnTsn(String hsnTsn) {
        driver.findElement(By.xpath("//p[contains(text(), '" + hsnTsn + "')]")).click();
    }

    public String getHsnTsnText() {
        waitForVisibilityOf(hsnTsnElement);
        return hsnTsnElement.getText();
    }
}
