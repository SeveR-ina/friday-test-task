package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TimeOuts;

import java.time.Duration;

/**
 * Parent page for all pages includes initing elements on the page and waiting for elements.
 */
abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for visibility of the page element for default time.
     */
    public void waitForVisibilityOf(WebElement webElement) {
        new WebDriverWait(driver, Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.
                getTimeOutValue())).until(ExpectedConditions.visibilityOf(webElement));
    }
}
