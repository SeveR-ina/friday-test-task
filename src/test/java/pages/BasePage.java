package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TimeOuts;

import java.time.Duration;


abstract class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForVisibilityOf(WebElement webElement) {
        new WebDriverWait(driver, Duration.ofMillis(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue())).until(ExpectedConditions.visibilityOf(webElement));
    }

    public void sendKeys(WebElement field, String text) {
        field.click();
        field.clear();
        field.sendKeys(text);
    }
}
