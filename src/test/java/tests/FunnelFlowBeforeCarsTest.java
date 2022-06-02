package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SelectPreconditionPage;
import pages.SelectRegisteredOwnerPage;
import utils.listeners.TestListener;

import static org.testng.Assert.assertNotNull;

/**
 * Test class for checking no matter which radio button is chosen on /selectPrecondition
 * and /selectRegisteredOwner page, user still successfully visit /selectVehicle page.
 */
@Listeners({TestListener.class})
@Epic("Regression Tests")
@Feature("Sales Funnel Tests")
public class FunnelFlowBeforeCarsTest extends BasicTest {

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

    @Test(dataProvider = "objectTestData",
            description = "Test for checking that after choosing some radio buttons," +
                    "user still can see /selectVehicle page.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Check /selectvehicle page by choosing radio buttons on previous pages")
    public void checkSelectVehiclePageByChoosingRadioButtons(TestData testData) {
        preconditionPage.clickOnInsuranceStateRadioButton(testData.insuranceState);
        preconditionPage.submit();

        SelectRegisteredOwnerPage registeredOwnerPage = new SelectRegisteredOwnerPage(driver);

        registeredOwnerPage.clickOnCarRegStateRadioButton(testData.carRegState);
        registeredOwnerPage.clickOnCarPurchaseStateRadioButton(testData.carPurchaseState);
        registeredOwnerPage.submit();

        Assert.assertEquals(driver.getCurrentUrl(),
                testProperties.getProperty("vehiclePageURL"),
                "Actual page url != select vehicle page url");
    }

    /**
     * Data provider method for providing variants of choosing radio buttons.
     */
    @DataProvider
    public static Object[][] objectTestData() {
        return new Object[][]{
                {new TestData(0, 0, 0)},
                {new TestData(0, 0, 1)},
                {new TestData(0, 1, 0)},
                {new TestData(0, 1, 1)},
                {new TestData(1, 0, 0)},
                {new TestData(1, 1, 0)},
                {new TestData(1, 0, 1)},
                {new TestData(1, 1, 1)},
        };
    }

    /**
     * Static class for test data.
     */
    static class TestData {
        public int insuranceState;
        public int carRegState;
        public int carPurchaseState;

        public TestData(int insuranceState, int carRegState, int carPurchaseState) {
            this.insuranceState = insuranceState;
            this.carRegState = carRegState;
            this.carPurchaseState = carPurchaseState;
        }

        @Override
        public String toString() {
            return "TestData{" +
                    "insuranceState=" + (insuranceState + 1) + "th option" +
                    ", carRegState=" + (carRegState + 1) + "th option" +
                    ", carPurchaseState=" + (carPurchaseState + 1) + "th option" +
                    '}';
        }
    }
}
