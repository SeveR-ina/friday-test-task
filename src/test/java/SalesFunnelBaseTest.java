import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.*;
import testData.Car;
import utils.ShadowDomUtils;
import utils.TimeOuts;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SalesFunnelBaseTest extends BasicTest {

    protected SelectVehiclePage vehiclePage;

    protected SelectPreconditionPage preconditionPage;

    protected void acceptAllCookies() {
        WebElement parentShadowElement = driver.findElement(By.id("usercentrics-root"));
        Map<String, Object> params = new HashMap<>();
        params.put("parentElement", parentShadowElement);
        params.put("innerSelector", "button.sc-gsDKAQ.hWjjep");
        ShadowDomUtils.clickElementShadowDOM(((RemoteWebDriver) driver), params);
    }

    protected void openCarList() {
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue()));

        preconditionPage.submit();

        SelectRegisteredOwnerPage registeredOwnerPage = new SelectRegisteredOwnerPage(driver);
        registeredOwnerPage.submit();

        vehiclePage = new SelectVehiclePage(driver);
        vehiclePage.openCarList();

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue()));
    }

    protected void selectBrandAndModel(Car car) {
        vehiclePage.selectBrand(car.getBrand());

        SelectModelPage modelPage = new SelectModelPage(driver);
        modelPage.selectModel(car.getModel());
    }

    protected void selectEP(Car car, String expectedModel) {
        if (!car.getModel().equals(expectedModel)) {
            SelectEnginePowerPage enginePowerPage = new SelectEnginePowerPage(driver);
            enginePowerPage.selectEP(car.getPs());
        }
    }

    protected void enterRegDate(Car car) {
        EnterRegistrationDatePage registrationDatePage = new EnterRegistrationDatePage(driver);
        registrationDatePage.fillInInput(car.getRegDate());
        registrationDatePage.submit();

    }
}
