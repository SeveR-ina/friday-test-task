import org.testng.Assert;
import org.testng.annotations.*;
import pages.SelectPreconditionPage;
import pages.SelectRegisteredOwnerPage;

import static org.testng.Assert.assertNotNull;

/**
 * Test class for checking no matter which radio button is chosen on /selectPrecondition
 * and /selectRegisteredOwner page, user still successfully visit /selectVehicle page.
 */
@Ignore
@Test
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

    @Ignore //TODO: has to work differently
    @Test(dataProvider = "objectTestData",
            description = "Test for checking that after choosing some radio buttons," +
                    "user still can see /selectVehicle page.")
    public void checkSalesFunnelWithVWCars(TestData testData) {
        preconditionPage.clickOnRButton(testData.items[0]);
        preconditionPage.submit();

        SelectRegisteredOwnerPage registeredOwnerPage = new SelectRegisteredOwnerPage(driver);

        registeredOwnerPage.clickOnRButton(testData.items[1]);
        registeredOwnerPage.clickOnRButton(testData.items[2]);
        registeredOwnerPage.submit();

        Assert.assertEquals(driver.getCurrentUrl(),
                testProperties.getProperty("vehiclePageURL"),
                "Actual page url != select vehicle page url");
    }

    /**
     * Data provider method for providing variants of choosing radio buttons.
     */
    //TODO: re-write code so all combinations go through test
    @DataProvider(name = "objectTestData")
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
        public int[] items;

        public TestData(int... items) {
            this.items = items;
        }
    }
}
