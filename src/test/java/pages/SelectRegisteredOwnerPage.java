package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectRegisteredOwnerPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindAll({@FindBy(xpath = "//label[contains(@data-testid, 'shared')]")})
    private List<WebElement> radioButtonsCarRegState;

    @FindAll({@FindBy(xpath = "//label[contains(@data-testid, 'quoting.selectRegisteredOwner')]")})
    private List<WebElement> radioButtonsCarPurchaseState;

    public SelectRegisteredOwnerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on submit button")
    public void submit() {
        waitForVisibilityOf(submitButton);
        submitButton.click();
    }

    @Step("Click on car registration state radio button with {0} index")
    public void clickOnCarRegStateRadioButton(int index) {
        waitForVisibilityOf(radioButtonsCarRegState.get(0));
        radioButtonsCarRegState.get(index).click();
    }

    @Step("Click on car purchase state radio button with {0} index")
    public void clickOnCarPurchaseStateRadioButton(int index) {
        waitForVisibilityOf(radioButtonsCarPurchaseState.get(0));
        radioButtonsCarPurchaseState.get(index).click();
    }

}
