import org.testng.Assert;
import org.testng.annotations.*;
import pages.SelectPreconditionPage;
import pages.SelectRegisteredOwnerPage;

import static org.testng.Assert.assertNotNull;

@Ignore
@Test
public class FunnelFlowBeforeCarsTest extends BasicTest {

    @BeforeTest
    public void doBeforeTest() {
        setProperties();
        loadPropertiesFromFile();
    }

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
    @Test(dataProvider = "objectTestData")
    public void checkSalesFunnelWithVWCars(TestData testData) {
        preconditionPage.clickOnRButton(testData.items[0]);
        preconditionPage.submit();

        SelectRegisteredOwnerPage registeredOwnerPage = new SelectRegisteredOwnerPage(driver);

        registeredOwnerPage.clickOnRButton(testData.items[1]);
        registeredOwnerPage.clickOnRButton(testData.items[2]);
        registeredOwnerPage.submit();

        Assert.assertEquals(driver.getCurrentUrl(),
                testProperties.getProperty("vehiclePageURL"), "Actual page url != select vehicle page url");
    }

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

    static class TestData {
        public int[] items;

        public TestData(int... items) {
            this.items = items;
        }
    }
}
