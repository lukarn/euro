package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Account;
import pages.BasePage;

import pages.LoginPage;
import pages.Shopping;
import utilities.CommonTestData;
import utilities.DriverManager;
import utilities.LoginData;


import java.io.IOException;


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
//                {1, "https://www.euro.com.pl/", "firefox"},
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
        if(driver != null)
        {
            driver.quit();
        }
        else
        {
            System.out.println("Something is wrong ---> driver = null in AfterMethod");
        }
    }

    //copy to disable:
    //, enabled = false
    @Test(dataProvider="getData", enabled = false)
    public void launch(int p1, String p2, String p3) {
        // setup driver
        DriverManager driverManager = new DriverManager(driver);
        //usingBrowser=p3;
        driver = driverManager.getDriver(p3);

        //get to base page (from data provider)
        driver.get(p2);
        System.out.println("-------testing www no. " + p1 + " : " + p2 + " on " + p3);

        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.isAt(timeoutIsAt), "----------BasePage not loaded!");
    }

    @Test(dataProvider="getData", priority=1, enabled = false)
    public void loginIncorrect(int p1, String p2, String p3) {
        launch(p1, p2, p3);

        BasePage basePage = new BasePage(driver);
        basePage.setZalogujButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setLoginInput(loginData.getLoginUsername());
        loginPage.setPasswordInput(loginData.getLoginPassword());
        loginPage.setZalogujButton();

        Assert.assertTrue(loginPage.loginError(), "----------Can not see login error info after incorrect login data");
    }

    @Test(dataProvider="getData", priority=2, enabled = false)
    public void signUp(int p1, String p2, String p3) {
        launch(p1, p2, p3);

        BasePage basePage = new BasePage(driver);
        basePage.setZalogujButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmailInput(loginData.getLoginUsername());
        loginPage.setPasswordNewInput(loginData.getLoginPassword());
        loginPage.setPassword2NewInput(loginData.getLoginPassword());
        loginPage.setFirstnameInput(commonTestData.getName().get(0));
        loginPage.setSurnameInput(commonTestData.getSurname().get(0));
        loginPage.setAckCheckButton();

        loginPage.setZarejestrujButton();

        Account account = new Account(driver);
        Assert.assertTrue(account.isAt(timeoutIsAt), "----------Sign up fail - you are not on desired page");
    }

    @Test(dataProvider="getData", priority=3, enabled = false)
    public void loginCorrect(int p1, String p2, String p3) {
        launch(p1, p2, p3);

        BasePage basePage = new BasePage(driver);
        basePage.setZalogujButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setLoginInput(loginData.getLoginUsername());
        loginPage.setPasswordInput(loginData.getLoginPassword());
        loginPage.setZalogujButton();

        Account account = new Account(driver);
        Assert.assertTrue(account.isAt(timeoutIsAt), "----------Log in fail - you are not on desired page");
    }

    //////////////////////////////////////////////////////////////////////////////////I am here:
    @Test(dataProvider="getData", priority=4)
    public void shopping(int p1, String p2, String p3) {
        loginCorrect(p1, p2, p3);

        Shopping shopping = new Shopping(driver);
        shopping.searchProduct("telewizor led samsung");
//        shopping.choseByPrice("2000","2001");
        shopping.choseByPrice("2000","2500");


        System.out.println(commonTestData.getCity());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider="getData", priority=9, enabled = false)
    public void deleteAccount(int p1, String p2, String p3) {
        loginCorrect(p1, p2, p3);

        Account account = new Account(driver);
        account.deleteAccount();

        BasePage basePage = new BasePage(driver);
        Assert.assertTrue(basePage.isAt(timeoutIsAt), "----------BasePage after delete account not loaded!");

        driver.quit();
        loginIncorrect(p1, p2, p3);
    }






}
