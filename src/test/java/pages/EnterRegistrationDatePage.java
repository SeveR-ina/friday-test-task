package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EnterRegistrationDatePage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(name = "monthYearFirstRegistered")
    private WebElement monthYearInput;

    public EnterRegistrationDatePage(WebDriver driver) {
        super(driver);
    }

    public void enter(String date) {
        monthYearInput.sendKeys(date);
    }

    public void submit() {
        submitButton.click();
    }
}
