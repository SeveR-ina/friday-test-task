package utils;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Dushyantan Satike
 */
public class ShadowDomUtils {

    /**
     * Performs click operation on the first matching shadow DOM element of the specified CSS locator
     *
     * @param driver
     * @param params
     * @return
     */
    public static void clickElementShadowDOM(RemoteWebDriver driver, Map<String, Object> params) {
        WebElement shadowRoot = (WebElement) params.get("parentElement");
        String innerElSelector = params.get("innerSelector").toString();
        String getInnerEl = "return arguments[0].shadowRoot.querySelector('" + innerElSelector + "').click();";
        //Wait for page to completely load
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(90));
            wait.until(expectation);
        } catch (Throwable error) {
        }
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue()));
        driver.executeScript(getInnerEl, shadowRoot);
    }
}