package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import io.appium.java_client.windows.WindowsDriver;


public class TestDesktop
{
    private static WindowsDriver CalculatorSession = null;
    private static WebElement CalculatorResult = null;

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
                //{1, "https://www.euro.com.pl/", "firefox"},
        };
    }

    @BeforeSuite()
    public void BeforeSuite() throws IOException
    {

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
        CalculatorSession.quit();
    }

    //copy to disable:
    //, enabled = false
    @Test(dataProvider="getData")
    public void launch(int p1, String p2, String p3) throws InterruptedException {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
            //  Microsoft.WindowsCalculator_10.1906.53.0_x64__8wekyb3d8bbwe

            //  capabilities.setCapability("app", "C:\\Program Files (x86)\\Badboy\\badboy.exe");

            CalculatorSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            CalculatorSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            CalculatorResult = CalculatorSession.findElementByAccessibilityId("CalculatorResults");
            Assert.assertNotNull(CalculatorResult);

        }catch(Exception e){
            e.printStackTrace();
        } finally {
        }

//        CalculatorSession.findElementByName("Clear").click();
//        Assert.assertEquals("0", _GetCalculatorResultText());

        //CalculatorSession.findElementById("num2Button").click();

        CalculatorSession.findElementByName("Jeden").click();
        CalculatorSession.findElementByName("Plus").click();
        CalculatorSession.findElementByName("Dwa").click();
        CalculatorSession.findElementByName("Równa się").click();
        Assert.assertEquals("3", _GetCalculatorResultText());




        System.out.println("-------testing www no. " + p1 + " : " + p2 + " on " + p3);

//        BasePage basePage = new BasePage(driver);
//        Assert.assertTrue(basePage.isAt(timeoutIsAt, TimeUnit.SECONDS), "----------BasePage not loaded!");

        Thread.sleep(5000);
    }

    protected String _GetCalculatorResultText()
    {
        // trim extra text and whitespace off of the display value
        return CalculatorResult.getText().replace("Wyświetlana wartość to ", "").trim();
    }







}
