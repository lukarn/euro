package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DriverManager {

    private WebDriver driver;

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

    private WebDriver getChromeDriver()
    {
        ChromeOptions options = new ChromeOptions();

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "\\screenShots");
        options.setExperimentalOption("prefs", chromePrefs);

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(driver != null)
        {
            driver.manage().window().maximize();
        }
        else
        {
            System.out.println("Driver = null - during init in DriverManager");
        }
        return driver;
    }

    private WebDriver getFirefoxDriver()
    {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile myProfile = new FirefoxProfile();
        myProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        myProfile.setPreference("pdfjs.disabled", true);
        myProfile.setPreference("browser.download.folderList", 2);
        myProfile.setPreference("browser.download.manager.showWhenStarting", false);
        myProfile.setPreference("browser.download.dir", System.getProperty("user.dir") + "\\screenShots");
        options.setProfile(myProfile);

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(driver != null)
        {
            driver.manage().window().maximize();
        }
        else
        {
            System.out.println("Driver = null - during init in DriverManager");
        }
        return driver;
    }




}
