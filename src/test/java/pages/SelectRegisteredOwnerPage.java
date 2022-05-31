package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectRegisteredOwnerPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public SelectRegisteredOwnerPage(WebDriver driver) {
        super(driver);
    }

    public void submit() {
        submitButton.click();
    }

}
