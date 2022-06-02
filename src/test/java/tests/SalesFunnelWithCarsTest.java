package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.BeforeTest;
import pages.*;
import testData.Car;
import utils.listeners.TestListener;

import static org.testng.Assert.assertNotNull;

/**
 * Main test class with checking FRIDAY's sales funnel.
 * Picking 3 car brands and 3 models for each and every brand.
 */
@Listeners({TestListener.class})
@Epic("Regression Tests")
@Feature("Sales Funnel Tests")
public class SalesFunnelWithCarsTest extends SalesFunnelBaseTest {

    /**
     * Sets and loads test properties.
     */
    @BeforeTest
    public void doBeforeTest() {
        initializeProperties();
        loadPropertiesFromFile();
    }

    /**
     * Opens Browser, goes to start URL, accepts cookies, asserts that start page is not null.
     *
     * @param browser  can be chosen via parameter and value from testng xml.
     * @param headless can be chosen via parameter and value from testng xml.
     */
    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void doBeforeMethod(String browser, boolean headless) {
        doPreparationsFor(browser, headless);

        acceptAllCookies();

        preconditionPage = new SelectPreconditionPage(driver);
        assertNotNull(preconditionPage, "Select Precondition page is null");
    }

    @AfterMethod
    public void closeBrowser() {
        quitDriver();
    }

    @Test(dataProvider = "createVWCars",
            description = "Checking VW cars with 3 models until entering birthday")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Check Sales Funnel by selecting VW car")
    public void checkSalesFunnelWithVWCars(Car car) {
        openCarList();

        selectBrandAndModel(car);

        SelectBodyTypePage bodyTypePage = new SelectBodyTypePage(driver);
        bodyTypePage.selectShape(car.getShape());

        SelectFuelTypePage fuelTypePage = new SelectFuelTypePage(driver);
        fuelTypePage.selectFuel(car.getFuel());

        selectEP(car, "Passat");

        SelectEnginePage enginePage = new SelectEnginePage(driver);
        Assert.assertEquals(enginePage.getHsnTsnText(), car.getTsnHsn(),
                "Actual HSN/TSN != Expected one");
        enginePage.selectHsnTsn(car.getTsnHsn());

        enterRegDate(car);

        Assert.assertEquals(driver.getCurrentUrl(),
                testProperties.getProperty("birthDateURL"),
                "Actual page url != birth date url");
    }

    @Test(dataProvider = "createBMWCars",
            description = "Checking BMW cars with 3 models until entering birthday")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Check Sales Funnel by selecting BMW car")
    public void checkSalesFunnelWithBMWCars(Car car) {
        openCarList();

        selectBrandAndModel(car);

        if (car.getModel().equals("1er")) {
            SelectBodyTypePage bodyTypePage = new SelectBodyTypePage(driver);
            bodyTypePage.selectShape(car.getShape());
        }

        SelectFuelTypePage fuelTypePage = new SelectFuelTypePage(driver);
        fuelTypePage.selectFuel(car.getFuel());

        selectEP(car, "X1");

        SelectEnginePage enginePage = new SelectEnginePage(driver);
        Assert.assertEquals(enginePage.getHsnTsnText(), car.getTsnHsn(),
                "Actual HSN/TSN != Expected one");
        enginePage.selectHsnTsn(car.getTsnHsn());

        enterRegDate(car);

        Assert.assertEquals(driver.getCurrentUrl(),
                testProperties.getProperty("birthDateURL"),
                "Actual page url != birth date url");
    }

    @Test(dataProvider = "createOpelCars",
            description = "Checking Opel cars with 3 models until entering birthday")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Check Sales Funnel by selecting Opel car")
    public void checkSalesFunnelWithOpelCars(Car car) {

        openCarList();

        selectBrandAndModel(car);

        if (car.getModel().equals("Insignia")) {
            SelectBodyTypePage bodyTypePage = new SelectBodyTypePage(driver);
            bodyTypePage.selectShape(car.getShape());
        }

        if (!car.getModel().equals("Corsa")) {
            SelectFuelTypePage fuelTypePage = new SelectFuelTypePage(driver);
            fuelTypePage.selectFuel(car.getFuel());
        }

        if (car.getModel().equals("Meriva")) {
            SelectEnginePowerPage enginePowerPage = new SelectEnginePowerPage(driver);
            enginePowerPage.selectEP(car.getPs());
        }

        SelectEnginePage enginePage = new SelectEnginePage(driver);
        Assert.assertEquals(enginePage.getHsnTsnText(), car.getTsnHsn(),
                "Actual HSN/TSN != Expected one");
        enginePage.selectHsnTsn(car.getTsnHsn());

        enterRegDate(car);

        Assert.assertEquals(driver.getCurrentUrl(),
                testProperties.getProperty("birthDateURL"),
                "Actual page url != birth date url");
    }

    /**
     * Data provider method for providing different test data for Opel
     */
    @DataProvider
    public Object[][] createOpelCars() {

        Car corsa = Car.builder()
                .brand("OPEL")
                .model("Corsa")
                .tsnHsn(opelProperties.getProperty("corsaFinalResult"))
                .regDate(testProperties.getProperty("firstRegistration"))
                .build();

        Car insignia = Car.builder()
                .brand("OPEL")
                .model("Insignia")
                .shape(opelProperties.getProperty("insigniaShape"))
                .fuel(opelProperties.getProperty("insigniaFuel"))
                .tsnHsn(opelProperties.getProperty("insigniaFinalResult"))
                .regDate(testProperties.getProperty("firstRegistration"))
                .build();

        Car meriva = Car.builder()
                .brand("OPEL")
                .model("Meriva")
                .fuel(testProperties.getProperty("defaultFuel"))
                .ps(opelProperties.getProperty("merivaPs"))
                .tsnHsn(opelProperties.getProperty("merivaFinalResult"))
                .regDate(testProperties.getProperty("firstRegistration"))
                .build();

        return new Object[][]{{corsa}, {insignia}, {meriva}};
    }

    /**
     * Data provider method for providing different test data for BMW
     */
    @DataProvider
    public Object[][] createBMWCars() {

        Car x1 = Car.builder()
                .brand("BMW")
                .model("X1")
                .fuel(bmwProperties.getProperty("x1Fuel"))
                .tsnHsn(bmwProperties.getProperty("bmwFinalResult"))
                .regDate(testProperties.getProperty("firstRegistration"))
                .build();

        Car x3 = Car.builder()
                .brand("BMW")
                .model("X3")
                .fuel(testProperties.getProperty("defaultFuel"))
                .ps(bmwProperties.getProperty("x3Ps"))
                .tsnHsn(bmwProperties.getProperty("x3FinalResult"))
                .regDate(testProperties.getProperty("firstRegistration"))
                .build();

        Car oneEr = Car.builder()
                .brand("BMW")
                .model("1er")
                .shape(testProperties.getProperty("defaultShape"))
                .fuel(testProperties.getProperty("defaultFuel"))
                .ps(bmwProperties.getProperty("oneErPs"))
                .tsnHsn(bmwProperties.getProperty("oneErFinalResult"))
                .regDate(testProperties.getProperty("firstRegistration"))
                .build();

        return new Object[][]{{x1}, {x3}, {oneEr}};
    }

    /**
     * Data provider method for providing different test data for VW
     */
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
}
