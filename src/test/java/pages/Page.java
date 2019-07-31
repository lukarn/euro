package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.DriverManager;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public abstract class Page {
    public abstract boolean isAt();

    public boolean isAt(long timeout, TimeUnit timeunit){
        try{
            await().atMost(timeout, timeunit).ignoreExceptions().until(() -> isAt());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public abstract boolean isAt2();

    public boolean isAt2(long timeout, TimeUnit timeunit){
        try{
            await().atMost(timeout, timeunit).ignoreExceptions().until(() -> isAt2());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public abstract boolean clickAt();

    public boolean clickAt(WebElement locator, long timeout, TimeUnit timeunit){
        try{
            await().atMost(timeout, timeunit).ignoreExceptions().until(() -> clickAt());
            //locator.click();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    ////////////////////
    WebElement locator;
    WebDriver driver;
    public boolean clickOK(WebElement locator){
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            locator.click();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public void clickUntilElementIsPresent(WebElement clickLocator, WebElement presentLocator)
    {
        boolean isPresent=true;
        for(int i=0; i<10; i++)
        {
            locator = clickLocator;
            Assert.assertTrue(clickAt(locator, (DriverManager.timeoutDriverManager - 1), TimeUnit.SECONDS), "----------1-Can not click element!!!");

            if(isPresent) {
                try {
                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                    isPresent = presentLocator.isDisplayed();
                    driver.manage().timeouts().implicitlyWait(DriverManager.timeoutDriverManager, TimeUnit.SECONDS);
                    System.out.println("===========================================================================click successful but redirect not-source element still present!>>>>>>>>>>>>debug-remove this line from code");
                } catch (NoSuchElementException e) {
                    isPresent = false;
                    driver.manage().timeouts().implicitlyWait(DriverManager.timeoutDriverManager, TimeUnit.SECONDS);
                    i=30;
                }
            }
            else{
                i=30;   //do max. 10-times (most cases once - if first click is successful than )
            }

        }
    }
    public void clickUntilElementIsNotPresent(WebElement clickLocator, WebElement presentLocator)
    {
        boolean isPresent=false;
        for(int i=0; i<10; i++)
        {
            locator = clickLocator;
            Assert.assertTrue(clickAt(locator, (DriverManager.timeoutDriverManager - 1), TimeUnit.SECONDS), "----------2-Can not click element!!!");

            if(!isPresent) {
                try {
                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                    isPresent = presentLocator.isDisplayed();
                    driver.manage().timeouts().implicitlyWait(DriverManager.timeoutDriverManager, TimeUnit.SECONDS);
                    i=30;
                } catch (NoSuchElementException e) {
                    isPresent = false;
                    System.out.println("===========================================================================click successful but redirect not-destination element not present!>>>>>>>>>>>>debug-remove this line from code");
                    driver.manage().timeouts().implicitlyWait(DriverManager.timeoutDriverManager, TimeUnit.SECONDS);
                }
            }
            else{
                i=30;   //do max. 10-times (most cases once - if first click is successful than )
            }

        }
    }


}
