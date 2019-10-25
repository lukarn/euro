package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.DriverManager;

import java.util.concurrent.TimeUnit;

public class BasePage extends Page {

    @Override
    public boolean isAt(){
        return this.zalogujButton.isDisplayed();
    }

    @Override
    public boolean isAt2(){
        return this.zalogujButton.isDisplayed();
    }

    @Override
    public boolean clickAt(){
        return clickOK(this.locator);
    }

//    @FindAll({
//            @FindBy(xpath = "//*[contains(text(), 'zaloguj')]"),
//            @FindBy(xpath = "//*[contains(text(), 'Zaloguj się') and contains(@href, 'login')]"),
//            @FindBy(xpath = "//*[contains(text(), 'Zaloguj')]")
//    })
    @FindBy(xpath = "//*[contains(text(), 'Zaloguj się') and contains(@href, 'login')]")
    WebElement zalogujButton;

    @FindBy(xpath = "//*[contains(text(), 'Zarejestruj')]")
    WebElement zarejestrujButton;

    @FindBy(id = "firstname")
    WebElement imieInput;

    //@FindBy(xpath = "//*[@id=\"logowanie\"]/form/ul/li[3]/input")
    // @FindBy(xpath = "//*[contains(@href, 'f_sprawdz_grafik') and contains(text(), 'Edytuj')]")

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


//    public void enterUsername(String username)
//    {
//        usernameInput.sendKeys(username);
//    }
//
//    public void enterPassword(String password)
//    {
//        passwordInput.sendKeys(password);
//    }

    public void setZalogujButton()
    {
        clickUntilElementIsNotPresent(zalogujButton,zarejestrujButton);
    }

    public void setZarejestrujButton()
    {
        clickUntilElementIsNotPresent(zarejestrujButton,imieInput);
//        locator = zarejestrujButton;
//        Assert.assertTrue(clickAt(locator, (DriverManager.timeoutDriverManager - 1), TimeUnit.SECONDS), "----------can not click this element (not found or not clickable)");
    }

//    public String getWelcomeText()
//    {
//        return welcomeText.getText();
//    }
//
//    public String getLoginError()
//    {
//        return loginError.getText();
//    }

}
