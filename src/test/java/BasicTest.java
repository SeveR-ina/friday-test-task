import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.TimeOuts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

abstract class BasicTest {
    protected WebDriver driver;
    private final static String TEST_PROPERTIES_PATH = "./src/test/resources/test.properties";
    private final static String VW_PROPERTIES_PATH = "./src/test/resources/vw.properties";
    private final static String BMW_PROPERTIES_PATH = "./src/test/resources/bmw.properties";
    private final static String OPEL_PROPERTIES_PATH = "./src/test/resources/opel.properties";
    protected Properties testProperties;
    protected Properties vwProperties;
    protected Properties opelProperties;
    protected Properties bmwProperties;

    protected void doPreparationsFor(String browser) {
        setCapabilities(browser);
        openBrowser(browser);

        driver.get(testProperties.getProperty("salesFunnelURL"));
        manageDriver();
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

    private void openBrowser(String browser) {
        switch (browser) {
            case "Chrome":
                openChrome();
                break;
            case "FireFox":
                openFireFox();
                break;
            case "Edge":
                openEdge();
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

    private void openChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private void openEdge() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }

    private void openFireFox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }
}
