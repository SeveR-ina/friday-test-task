package pages;

import io.qameta.allure.Step;
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

    @Step("Enter {0} step into registration date input")
    public void enter(String date) {
        monthYearInput.sendKeys(date);
    }

    @Step("Click on submit button")
    public void submit() {
        submitButton.click();
    }
}
