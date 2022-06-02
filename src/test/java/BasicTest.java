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

/**
 * Basic class for all tests: creates driver instances,
 * works with properties and capabilities, manages drivers.
 */
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

    /**
     * Sets capabilities for browsers.
     * Creates instances of drivers.
     * Opens browser with start URL, maximizes the window, and deletes all cookies.
     */
    protected void doPreparationsFor(String browser, boolean headless) {
        createAndSetCapabilities(browser);
        uploadDriverAndInitializeBaseDriver(browser, headless);

        driver.get(testProperties.getProperty("salesFunnelURL"));
        manageDriver();
    }

    /**
     * Accepts all cookies for further testing.
     */
    protected void acceptAllCookies() {
        WebElement parentShadowElement = driver.findElement(By.id("usercentrics-root"));
        Map<String, Object> params = new HashMap<>();
        params.put("parentElement", parentShadowElement);
        params.put("innerSelector", "button.sc-gsDKAQ.hWjjep");
        ShadowDomUtils.clickElementShadowDOM(((RemoteWebDriver) driver), params);
    }

    /**
     * Creates and initializes properties.
     */
    protected void initializeProperties() {
        testProperties = new Properties();
        vwProperties = new Properties();
        opelProperties = new Properties();
        bmwProperties = new Properties();
    }

    /**
     * Loads/Reads all properties.
     */
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

    /**
     * Creates and sets capabilities for browser.
     *
     * @param browser can be chosen via parameter and value from testng xml.
     */
    private void createAndSetCapabilities(String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser", browser);
    }

    /**
     * Quits this driver and closes its window.
     */
    protected void quitDriver() {
        driver.quit();
    }

    /**
     * Uploads driver and, if necessary, adds options to it.
     * Initializes default driver.
     *
     * @param browser  can be chosen via parameter and value from testng xml.
     * @param headless can be chosen via parameter and value from testng xml.
     */
    private void uploadDriverAndInitializeBaseDriver(String browser, boolean headless) {
        switch (browser) {
            case "Chrome":
                uploadChromeDriverAndInitBaseDriver(headless);
                break;
            case "FireFox":
                uploadFireFoxDriverAndInitBaseDriver(headless);
                break;
            case "Edge":
                uploadEdgeDriverAndInitBaseDriver(headless);
                break;
            default:
                System.out.println("Wrong Browser's name");
                break;
        }
    }

    /**
     * Maximizes the window, waits, and deletes cookies.
     */
    private void manageDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue()));
        driver.manage().deleteAllCookies();
    }

    /**
     * Uploads Chrome driver.
     * If necessary, creates Chrome options.
     * Initializes default driver.
     */
    private void uploadChromeDriverAndInitBaseDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
    }

    /**
     * Uploads Edge driver.
     * If necessary, creates Edge options.
     * Initializes default driver.
     */
    private void uploadEdgeDriverAndInitBaseDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        driver = new EdgeDriver(options);
    }

    /**
     * Uploads Firefox driver.
     * If necessary, creates Firefox options.
     * Initializes default driver.
     */
    private void uploadFireFoxDriverAndInitBaseDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        driver = new FirefoxDriver(options);
    }
}
