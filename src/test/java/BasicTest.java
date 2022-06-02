import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.SelectPreconditionPage;
import utils.ShadowDomUtils;
import utils.TimeOuts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

abstract class BasicTest {
    protected WebDriver driver;

    protected SelectPreconditionPage preconditionPage;
    private final static String TEST_PROPERTIES_PATH = "./src/test/resources/test.properties";
    private final static String VW_PROPERTIES_PATH = "./src/test/resources/vw.properties";
    private final static String BMW_PROPERTIES_PATH = "./src/test/resources/bmw.properties";
    private final static String OPEL_PROPERTIES_PATH = "./src/test/resources/opel.properties";
    protected Properties testProperties;
    protected Properties vwProperties;
    protected Properties opelProperties;
    protected Properties bmwProperties;

    protected void doPreparationsFor(String browser, boolean headless) {
        setCapabilities(browser);
        openBrowser(browser, headless);

        driver.get(testProperties.getProperty("salesFunnelURL"));
        manageDriver();
    }

    protected void acceptAllCookies() {
        WebElement parentShadowElement = driver.findElement(By.id("usercentrics-root"));
        Map<String, Object> params = new HashMap<>();
        params.put("parentElement", parentShadowElement);
        params.put("innerSelector", "button.sc-gsDKAQ.hWjjep");
        ShadowDomUtils.clickElementShadowDOM(((RemoteWebDriver) driver), params);
    }

    protected void setProperties() {
        testProperties = new Properties();
        vwProperties = new Properties();
        opelProperties = new Properties();
        bmwProperties = new Properties();
    }

    protected void loadPropertiesFromFile() {
        try {
            testProperties.load(new FileInputStream(TEST_PROPERTIES_PATH));
            vwProperties.load(new FileInputStream(VW_PROPERTIES_PATH));
            bmwProperties.load(new FileInputStream(BMW_PROPERTIES_PATH));
            opelProperties.load(new FileInputStream(OPEL_PROPERTIES_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCapabilities(String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser", browser);
    }

    public void quitDriver() {
        driver.quit();
    }

    private void openBrowser(String browser, boolean headless) {
        switch (browser) {
            case "Chrome":
                openChrome(headless);
                break;
            case "FireFox":
                openFireFox(headless);
                break;
            case "Edge":
                openEdge(headless);
                break;
            default:
                System.out.println("Wrong Browser's name");
                break;
        }
    }

    private void manageDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue()));
        driver.manage().deleteAllCookies();
    }

    private void openChrome(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
    }

    private void openEdge(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        driver = new EdgeDriver(options);
    }

    private void openFireFox(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        driver = new FirefoxDriver(options);
    }
}
