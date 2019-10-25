package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DriverManager {

    WebDriver driver;

    public static long timeoutDriverManager = 10;

    public DriverManager(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebDriver getDriver(String usingBrowser)
    {
        if(usingBrowser.equalsIgnoreCase("chrome"))
        {
            //run chromedriver
            driver = getChromeDriver();
            System.out.println("start with chromedriver :)");
        }
        else if(usingBrowser.equalsIgnoreCase("firefox"))
        {
            //run firefox
            driver = getFirefoxDriver();
            System.out.println("start with firefoxdriver :)");
        }
        else {
            //other drivers to implement!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println("---------other driver to implement");
        }
        return driver;
    }

    public WebDriver getChromeDriver()
    {
        ChromeOptions options = new ChromeOptions();

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "\\screenShots");
        options.setExperimentalOption("prefs", chromePrefs);

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver getFirefoxDriver()
    {
        /*
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile myProfile = new FirefoxProfile();
        myProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        myProfile.setPreference("pdfjs.disabled", true);
        myProfile.setPreference("browser.download.folderList", 2);
        myProfile.setPreference("browser.download.manager.showWhenStarting", false);
        myProfile.setPreference("browser.download.dir", System.getProperty("user.dir") + "\\screenShots");
        options.setProfile(myProfile);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY);

         */

//        WebDriver driver = new FirefoxDriver(options);
//        driver.close();

        //firefoxdriver
        /*
        System.setProperty("webdriver.gecko.driver","geckodriver.exe");
        driver = new FirefoxDriver(options);
//        Dimension dimension1 = new Dimension(1400,650);
//        driver.manage().window().setSize(dimension1);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeoutDriverManager, TimeUnit.SECONDS);

         */

//        System.setProperty("webdriver.gecko.driver","C:\\Users\\Łukasz Arndt\\IdeaProjects\\GrafikTerminarz\\geckodriver.exe");
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());

        //System.setProperty("webdriver.gecko.driver","C:\\Users\\Łukasz Arndt\\IdeaProjects\\GrafikTerminarz\\geckodriver.exe");
//        FirefoxOptions options = new FirefoxOptions();
//        options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WIN10);
//        options.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        //options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

        //WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        //  "http://192.168.157.43:4445"
//        try{
//            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//        }
//        catch (MalformedURLException e)
//        {
//            System.out.println("----------------------------------------------------> driver error");
//            e.printStackTrace();
//        }

        DesiredCapabilities dc = DesiredCapabilities.firefox();
        dc.setCapability("marionette", true);
        dc.setBrowserName("firefox");
//
//
//        FirefoxOptions options = new FirefoxOptions();
//        options.setCapability("platform", "WIN10");
//        options.setCapability("browserName", "firefox");
        WebDriver driver = null;



        //  standalone selenium grid server .bat http://192.168.157.43:5555/wd/hub
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().window().maximize();
        return driver;
    }




}
