import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.BeforeTest;
import pages.*;
import testData.Car;
import utils.ShadowDomUtils;
import utils.TimeOuts;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

@Test
public class SalesFunnelTest extends BasicTest {

    SelectVehiclePage vehiclePage;

    SelectPreconditionPage preconditionPage;

    @BeforeTest
    public void doBeforeTest() {
        setProperties();
        loadPropertiesFromFile();
    }

    @Parameters("browser")
    @BeforeMethod
    public void doBeforeMethod(String browser) {
        doPreparationsFor(browser);

        acceptAllCookies();

        preconditionPage = new SelectPreconditionPage(driver);
        assertNotNull(preconditionPage, "Select Precondition page is null");
    }

    @AfterMethod
    public void closeBrowser() {
        quitDriver();
    }

    @Test(dataProvider = "createVWCars")
    public void checkSalesFunnelWithVWCars(Car car) {
        preconditionPage.submit();

        SelectRegisteredOwnerPage registeredOwnerPage = new SelectRegisteredOwnerPage(driver);
        registeredOwnerPage.submit();

        vehiclePage = new SelectVehiclePage(driver);
        vehiclePage.openCarList();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue()));
        vehiclePage.selectBrand(car.getBrand());

        SelectModelPage modelPage = new SelectModelPage(driver);
        modelPage.selectModel(car.getModel());

        SelectBodyTypePage bodyTypePage = new SelectBodyTypePage(driver);
        bodyTypePage.selectShape(car.getShape());

        SelectFuelTypePage fuelTypePage = new SelectFuelTypePage(driver);
        fuelTypePage.selectFuel(car.getFuel());

        if (!car.getModel().equals("Passat")) {
            SelectEnginePowerPage enginePowerPage = new SelectEnginePowerPage(driver);
            enginePowerPage.selectEP(car.getPs());
        }

        SelectEnginePage enginePage = new SelectEnginePage(driver);
        Assert.assertEquals(enginePage.getHsnTsnText(), car.getTsnHsn(), "Actual HSN/TSN != Expected one");
        enginePage.selectHsnTsn(car.getTsnHsn());

        EnterRegistrationDatePage registrationDatePage = new EnterRegistrationDatePage(driver);
        registrationDatePage.fillInInput(car.getRegDate());
        registrationDatePage.submit();

        Assert.assertEquals(driver.getCurrentUrl(), testProperties.getProperty("birthDateURL"), "Actual page url != birth date url");
    }

    @DataProvider
    public Object[][] createVWCars() {

        Car polo = Car.builder()
                .brand("VW")
                .model("Polo")
                .shape(vwProperties.getProperty("poloShape"))
                .fuel(testProperties.getProperty("defaultFuel"))
                .ps(vwProperties.getProperty("poloPs"))
                .tsnHsn(vwProperties.getProperty("poloFinalResult"))
                .regDate(testProperties.getProperty("firstRegistration"))
                .build();

        Car golf = Car.builder()
                .brand("VW")
                .model("Golf")
                .shape(vwProperties.getProperty("golfShape"))
                .fuel(vwProperties.getProperty("golfFuel"))
                .ps(vwProperties.getProperty("golfPs"))
                .tsnHsn(vwProperties.getProperty("golfFinalResult"))
                .regDate(testProperties.getProperty("firstRegistration"))
                .build();

        Car passat = Car.builder()
                .brand("VW")
                .model("Passat")
                .shape(vwProperties.getProperty("passatShape"))
                .fuel(vwProperties.getProperty("passatFuel"))
                .tsnHsn(vwProperties.getProperty("passatFinalResult"))
                .regDate(testProperties.getProperty("firstRegistration"))
                .build();

        return new Object[][]{{polo}, {golf}, {passat}};
    }

    private void acceptAllCookies() {
        WebElement parentShadowElement = driver.findElement(By.id("usercentrics-root"));
        Map<String, Object> params = new HashMap<>();
        params.put("parentElement", parentShadowElement);
        params.put("innerSelector", "button.sc-gsDKAQ.hWjjep");
        ShadowDomUtils.clickElementShadowDOM(((RemoteWebDriver) driver), params);
    }
}
