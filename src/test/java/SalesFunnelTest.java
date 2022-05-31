import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.SelectPreconditionPage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

@Test
public class SalesFunnelTest extends BeforeTest {

    private SelectPreconditionPage selectPreconditionPage;

    @Parameters({"browser"})
    public SalesFunnelTest(String browser) throws IOException {
        super(browser);
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void doPreparations(String browser) {
        openBrowsers(browser);
        selectPreconditionPage = new SelectPreconditionPage(driver);

        assertNotNull(selectPreconditionPage, "Page was not loaded");

        //selectPreconditionPage.acceptAllCookies();

        WebElement parentShadowElement = driver.findElement(By.id("usercentrics-root"));
        Map<String, Object> params = new HashMap<>();
        params.put("parentElement", parentShadowElement);
        params.put("innerSelector", "button.sc-gsDKAQ.hWjjep");
        WebElement innerDOMElement1 = ShadowDomUtils.findElementShadowDOM(((RemoteWebDriver) driver), params);
        ShadowDomUtils.clickElementShadowDOM(((RemoteWebDriver) driver), params);
    }

    @AfterMethod
    public void closeBrowser() {
        quitDriver();
    }

    @Test
    public void checkSalesFunnel() {
        selectPreconditionPage.submit();
    }

    public static class ShadowDomUtils {

        /**
         * Finds first shadow DOM element matching the CSS selector and returns it
         *
         * @param driver
         * @param params
         * @return WebElement
         */
        public static WebElement findElementShadowDOM(RemoteWebDriver driver, Map<String, Object> params) {
            WebElement shadowRoot = (WebElement) params.get("parentElement");
            String innerElSelector = params.get("innerSelector").toString();
            String getInnerEl = "return arguments[0].shadowRoot.querySelector('" + innerElSelector + "');";
            //Wait for page to completely load
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                }
            };
            try {
                Thread.sleep(1000);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30));
                wait.until(expectation);
            } catch (Throwable error) {
            }
            //Convert RemoteWebElement to WebElement to use it as a parent Shadow element
            WebElement element = ((WebElement) driver.executeScript(getInnerEl, shadowRoot));
            if (element instanceof RemoteWebElement) {
                try {
                    @SuppressWarnings("rawtypes")
                    Class[] parameterTypes = new Class[]{SearchContext.class,
                            String.class, String.class};
                    Method m = element.getClass().getDeclaredMethod(
                            "setFoundBy", parameterTypes);
                    m.setAccessible(true);
                    Object[] parameters = new Object[]{driver, "cssSelector", innerElSelector};
                    m.invoke(element, parameters);
                } catch (Exception fail) {
                    throw new RuntimeException(fail);
                }
            }
            return element;
        }

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
                Thread.sleep(1000);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30));
                wait.until(expectation);
            } catch (Throwable error) {
            }
            driver.executeScript(getInnerEl, shadowRoot);
        }

        /**
         * Returns the value of the given attribute of the first matching shadow DOM element of the specified CSS locator.
         *
         * @param driver
         * @param params
         * @return String
         */
        public String getAttributeShadowDOM(RemoteWebDriver driver, Map<String, Object> params) {
            WebElement shadowRoot = (WebElement) params.get("parentElement");
            String innerElSelector = params.get("innerSelector").toString();
            String property = params.get("attribute").toString();
            String getInnerEl = "return arguments[0].shadowRoot.querySelector('" + innerElSelector + "');";
            //Wait for page to completely load
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                }
            };
            try {
                Thread.sleep(1000);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30));
                wait.until(expectation);
            } catch (Throwable error) {
            }
            return ((WebElement) driver.executeScript(getInnerEl, shadowRoot)).getAttribute(property);
        }

        /**
         * Finds all the shadow DOM element matching the CSS selector and returns.
         *
         * @param driver
         * @param params
         * @return List<WebElement>
         */
        public List<WebElement> findElementsShadowDOM(RemoteWebDriver driver, Map<String, Object> params) {
            WebElement shadowRoot = (WebElement) params.get("parentElement");
            String innerElSelector = params.get("innerSelector").toString();
            String getInnerEl = "return arguments[0].shadowRoot.querySelectorAll('" + innerElSelector + "');";
            //Wait for page to completely load
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                }
            };
            try {
                Thread.sleep(1000);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30));
                wait.until(expectation);
            } catch (Throwable error) {
            }
            //Convert RemoteWebElement to WebElement to use it as a parent Shadow element
            List<WebElement> elementList = ((List<WebElement>) driver.executeScript(getInnerEl, shadowRoot));
            System.out.println(elementList);
            List<WebElement> updatedList = new ArrayList<WebElement>();
            for (WebElement webElement : elementList) {
                if (webElement instanceof RemoteWebElement) {
                    try {
                        @SuppressWarnings("rawtypes")
                        Class[] parameterTypes = new Class[]{SearchContext.class,
                                String.class, String.class};
                        Method m = webElement.getClass().getDeclaredMethod(
                                "setFoundBy", parameterTypes);
                        m.setAccessible(true);
                        Object[] parameters = new Object[]{driver, "cssSelector", innerElSelector};
                    } catch (Exception fail) {
                        throw new RuntimeException(fail);
                    }
                }
            }
            return elementList;
        }

        /**
         * Returns text of the shadow DOM element matching the CSS selector and returns.
         *
         * @param driver
         * @param params
         * @return String
         */
        public String getTextShadowDOM(RemoteWebDriver driver, Map<String, Object> params) {
            WebElement shadowRoot = (WebElement) params.get("parentElement");
            String innerElSelector = params.get("innerSelector").toString();
            String getInnerEl = "return arguments[0].shadowRoot.querySelector('" + innerElSelector + "').innerText;";
            //Wait for page to completely load
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                }
            };
            try {
                Thread.sleep(1000);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30));
                wait.until(expectation);
            } catch (Throwable error) {
            }
            return ((String) driver.executeScript(getInnerEl, shadowRoot));
        }

        /**
         * Performs send keys operation on the shadow DOM element matching the CSS selector and returns.
         *
         * @param driver
         * @param params
         * @return
         */
        public void sendKeysShadowDOM(RemoteWebDriver driver, Map<String, Object> params) {
            WebElement shadowRoot = (WebElement) params.get("parentElement");
            String innerElSelector = params.get("innerSelector").toString();
            String input = params.get("characterSequence").toString();
            String getInnerEl = "return arguments[0].shadowRoot.querySelector('" + innerElSelector + "');";
            //Wait for page to completely load
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                }
            };
            try {
                Thread.sleep(1000);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30));
                wait.until(expectation);
            } catch (Throwable error) {
            }
            WebElement element = ((WebElement) driver.executeScript(getInnerEl, shadowRoot));
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + input + "'", element);
        }
    }
}
