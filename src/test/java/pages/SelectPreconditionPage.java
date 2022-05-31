package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SelectPreconditionPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//button[contains(text(),'Alle akzeptieren')]")
    private WebElement acceptAllCookiesButton;

    public SelectPreconditionPage(WebDriver driver) {
        super(driver);
    }

    public void submit() {
        submitButton.click();
    }

    /*public void acceptAllCookies() {
        Actions builder = new Actions(driver);
        for (int i = 0; i <= 6; i++)
            builder.sendKeys(Keys.TAB).perform();

        builder.sendKeys(Keys.ENTER).perform();
    }*/
}
