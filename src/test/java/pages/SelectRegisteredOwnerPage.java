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

    @FindAll({@FindBy(className = "css-16lh4m5")})
    private List<WebElement> radioButtons;

    public SelectRegisteredOwnerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on submit button")
    public void submit() {
        waitForVisibilityOf(submitButton);
        submitButton.click();
    }

    @Step("Click on radio button with {0} index")
    public void clickOnRButton(int index) {
        waitForVisibilityOf(radioButtons.get(0));
        radioButtons.get(index).click();
    }

}
