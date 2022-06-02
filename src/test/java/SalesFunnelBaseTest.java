import pages.*;
import testData.Car;
import utils.TimeOuts;

import java.time.Duration;

/**
 * Parent class for SalesFunnelWithCarsTest with base methods.
 */
public class SalesFunnelBaseTest extends BasicTest {

    protected SelectVehiclePage vehiclePage;

    /**
     * Goes to /selectVehicle page and opens list of car brands.
     */
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

    /**
     * Selects exact car brand and model.
     */
    protected void selectBrandAndModel(Car car) {
        vehiclePage.selectBrand(car.getBrand());

        SelectModelPage modelPage = new SelectModelPage(driver);
        modelPage.selectModel(car.getModel());
    }

    /**
     * Selects exact engine power.
     */
    protected void selectEP(Car car, String expectedModel) {
        if (!car.getModel().equals(expectedModel)) {
            SelectEnginePowerPage enginePowerPage = new SelectEnginePowerPage(driver);
            enginePowerPage.selectEP(car.getPs());
        }
    }

    /**
     * Enters the registration date.
     */
    protected void enterRegDate(Car car) {
        EnterRegistrationDatePage registrationDatePage = new EnterRegistrationDatePage(driver);
        registrationDatePage.enter(car.getRegDate());
        registrationDatePage.submit();

    }
}
