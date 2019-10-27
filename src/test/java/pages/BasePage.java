package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Page {

    @Override
    public boolean isAt(){
        return this.zalogujButton.isDisplayed();
    }

    @Override
    public boolean clickAt(){
        return clickOK(this.locator);
    }

    @FindBy(xpath = "//*[contains(text(), 'Zaloguj się') and contains(@href, 'login')]")
    private WebElement zalogujButton;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setZalogujButton()
    {
        clickElement(zalogujButton);
    }

}
