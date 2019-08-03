package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;

import utilities.CommonTestData;
import utilities.DriverManager;
import utilities.LoginData;


import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestSignIn
{
    public static WebDriver driver;
    private LoginData loginData;
    private CommonTestData commonTestData;

    private long timeoutIsAt = (DriverManager.timeoutDriverManager - 1);  // one second less than driver
    //each timeoutIsAt MUST BE LESS THEN DriverManager.timeoutDriverManager
    //AND must be > 0

    @DataProvider
    public Object[][] getData()
    {
        return new Object[][]{
                {1, "https://www.euro.com.pl/", "chrome"},
                {1, "https://www.euro.com.pl/", "firefox"},
        };
    }

    @BeforeSuite()
    public void BeforeSuite() throws IOException
    {
        Assert.assertTrue(timeoutIsAt > 0, "-------------timeoutIsAt should be grater than 0 - check variable formula at the beginning of test class.");

        //load JSON data to objects
        loginData = LoginData.get("JSONdata/loginData.json");
        System.out.println("Starting for user: " + loginData.getLoginUsername());
        System.out.println("==============================================");

        commonTestData = CommonTestData.get("JSONdata/commonTestData.json");
        //end of loading JSON data

    }

    @AfterMethod()
    public void AfterMethod()
    {
        driver.quit();
    }

    //copy to disable:
    //, enabled = false
    @Test(dataProvider="getData", enabled = false)
    public void launch(int p1, String p2, String p3) throws InterruptedException {
        // setup driver
        DriverManager driverManager = new DriverManager(driver);
        //usingBrowser=p3;
        driver = driverManager.getDriver(p3);

        //get to base page (from data provider)
        driver.get(p2);
        System.out.println("-------testing www no. " + p1 + " : " + p2 + " on " + p3);

        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.isAt(timeoutIsAt, TimeUnit.SECONDS), "----------BasePage not loaded!");
    }

    @Test(dataProvider="getData")
    public void zaloguj(int p1, String p2, String p3) throws InterruptedException {
        launch(p1, p2, p3);

        BasePage basePage = new BasePage(driver);
        basePage.setZalogujButton();
        basePage.setZarejestrujButton();

//
//
//        basePage.enterUsername(loginData.getLoginUsername());
//        basePage.enterPassword(loginData.getLoginPassword());
//        basePage.setLoginButton();
//
//        Zaloguj zaloguj = new Zaloguj(driver);
//        Assert.assertTrue(zaloguj.isAt(timeoutIsAt, TimeUnit.SECONDS), "----------Zaloguj page not loaded!");

        //Thread.sleep(5000);
    }






}
