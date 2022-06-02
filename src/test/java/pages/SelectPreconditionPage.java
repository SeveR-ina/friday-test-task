package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectPreconditionPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindAll({@FindBy(className = "css-16lh4m5")})
    private List<WebElement> radioButtons;

    public SelectPreconditionPage(WebDriver driver) {
        super(driver);
    }

    public void submit() {
        waitForVisibilityOf(submitButton);
        submitButton.click();
    }

    public void clickOnRButton(int index) {
        waitForVisibilityOf(radioButtons.get(0));
        radioButtons.get(index).click();
    }
}
