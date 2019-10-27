package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class temp extends Page {

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

    @FindBy(id = "firstname")
    private WebElement imieInput;

    //@FindBy(xpath = "//*[@id=\"logowanie\"]/form/ul/li[3]/input")
    // @FindBy(xpath = "//*[contains(@href, 'f_sprawdz_grafik') and contains(text(), 'Edytuj')]")

    public temp(WebDriver driver)
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

//    public void setZalogujButton()
//    {
//        clickUntilElementIsNotPresent(zalogujButton,zarejestrujButton);
//    }

//    public void setZarejestrujButton()
//    {
//        clickUntilElementIsNotPresent(zarejestrujButton,imieInput);
////        locator = zarejestrujButton;
////        Assert.assertTrue(clickAt(locator, (DriverManager.timeoutDriverManager - 1), TimeUnit.SECONDS), "----------can not click this element (not found or not clickable)");
//    }

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
