package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.DriverManager;

public class Account extends Page {

    @Override
    public boolean isAt(){
        return this.daneIustawieniaButton.isDisplayed();
    }

    @Override
    public boolean clickAt(){
        return clickOK(this.locator);
    }

    @FindBy(xpath = "//*[@id='left-column']//*[contains(text(), 'Dane i ustawienia')]")
    private WebElement daneIustawieniaButton;

//    @FindBy(xpath = "//*[@id='top-menu']//*[contains(@href, 'konto')]")
//    private WebElement kontoButton;

    @FindBy(xpath = "//*[contains(@id, 'delete-zmien') and contains(text(), 'Usuń')]")
    private WebElement usunButton;

    @FindBy(xpath = "//*[@id='deleteBox']//*[contains(text(), 'Usuń') and contains(@onclick, 'remove')]")
    private WebElement usunConfirmButton;

    public Account(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void deleteAccount()
    {
        Assert.assertTrue(isAt(DriverManager.timeoutDriverManager - 1));  // one second less than driver), "You are not on your account main page");
        clickElement(daneIustawieniaButton);
        clickElement(usunButton);
        clickElement(usunConfirmButton);
    }



}
