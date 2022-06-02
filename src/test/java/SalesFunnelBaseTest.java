import pages.*;
import testData.Car;
import utils.TimeOuts;

import java.time.Duration;

public class SalesFunnelBaseTest extends BasicTest {

    protected SelectVehiclePage vehiclePage;

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
