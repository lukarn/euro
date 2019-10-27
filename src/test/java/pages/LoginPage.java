package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {

    @Override
    public boolean isAt(){
        return (this.zalogujButton.isDisplayed() && this.zarejestrujButton.isDisplayed());
    }

    @Override
    public boolean clickAt(){
        return clickOK(this.locator);
    }

    @FindBy(xpath = "//*[contains(text(), 'Zaloguj') and contains(@onclick, 'zaloguj')]")
    private WebElement zalogujButton;

    @FindBy(xpath = "//*[contains(text(), 'Zarejestruj') and contains(@onclick, 'zarejestruj')]")
    private WebElement zarejestrujButton;

    // login locators
    @FindBy(id = "j_username")
    private WebElement loginInput;

    @FindBy(id = "j_password")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[contains(text(), 'Podane hasło lub email są nieprawidłowe')]")
    private WebElement loginErrorText;

    // sign up locators
    @FindBy(id = "firstname")
    private WebElement firstnameInput;

    @FindBy(id = "surname")
    private WebElement surnameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordNewInput;

    @FindBy(id = "password2")
    private WebElement password2NewInput;

    @FindBy(xpath = "//*[contains(@id, 'regAck') and contains(@class, 'checkbox')]")
    private WebElement ackCheckboxButton;


    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // login methods
    public void setLoginInput(String text)
    {
        loginInput.sendKeys(text);
    }

    public void setPasswordInput(String text)
    {
        passwordInput.sendKeys(text);
    }

    public void setZalogujButton()
    {
        clickElement(zalogujButton);
    }

    public boolean loginError()
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(loginErrorText));
            return loginErrorText.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    // sign up methods
    public void setFirstnameInput(String text)
    {
        firstnameInput.sendKeys(text);
    }

    public void setSurnameInput(String text)
    {
        surnameInput.sendKeys(text);
    }

    public void setEmailInput(String text)
    {
        emailInput.sendKeys(text);
    }

    public void setPasswordNewInput(String text)
    {
        passwordNewInput.sendKeys(text);
    }

    public void setPassword2NewInput(String text)
    {
        password2NewInput.sendKeys(text);
    }

    public void setAckCheckButton()
    {
        clickElement(ackCheckboxButton);
    }

    public void setZarejestrujButton()
    {
        clickElement(zarejestrujButton);
    }

}
