package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectPreconditionPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public SelectPreconditionPage(WebDriver driver) {
        super(driver);
    }

    public void submit() {
        waitForVisibilityOf(submitButton);
        submitButton.click();
    }
}
