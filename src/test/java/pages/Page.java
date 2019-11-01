package pages;

import org.openqa.selenium.JavascriptExecutor;
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
    public boolean isAt(long timeout){
        try{
            await().atMost(timeout, TimeUnit.SECONDS).ignoreExceptions().until(this::isAt);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public abstract boolean clickAt();
    private boolean clickAt(long timeout){
        try{
            await().atMost(timeout, TimeUnit.SECONDS).ignoreExceptions().until(this::clickAt);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    WebElement locator;
    WebDriver driver;
    boolean clickOK(WebElement locator){
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            locator.click();
            return true;
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
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

    void clickElement(WebElement clickLocator)
    {
        System.out.println("Clicking locator: " + clickLocator + " on page with title: " + driver.getTitle());
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0);");
        locator = clickLocator;
        Assert.assertTrue(clickAt((DriverManager.timeoutDriverManager - 1)), "----------can not click this element (not found or not clickable)");
    }

    void waitForVisible(WebElement visibleLocator)
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(visibleLocator));
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(visibleLocator));
        }
    }


}
