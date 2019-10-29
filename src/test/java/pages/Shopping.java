package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.DriverManager;

public class Shopping extends Page {

    @Override
    public boolean isAt(){
        return this.searchBoxInput.isDisplayed();
    }

    @Override
    public boolean clickAt(){
        return clickOK(this.locator);
    }

    @FindBy(xpath = "//*[@id='search-box']//*[@id='keyword']")
    private WebElement searchBoxInput;

    @FindBy(xpath = "//*[@id='filter-price-from']")
    private WebElement priceMinInput;

    @FindBy(xpath = "//*[@id='filter-price-to']")
    private WebElement priceMaxInput;

    @FindBy(xpath = "//*[@id='filter-price-button']")
    private WebElement filterPriceButton;

    @FindBy(xpath = "//*[@id='filter-sort']//*[@class='select3-selected-item']")
    private WebElement filterSortSelect;

    @FindBy(xpath = "//*[@id='filter-sort']//*[contains(@class, 'select') and contains(text(), 'Od najtańszego')]")
    private WebElement sortFromMinSelect;

    @FindBy(xpath = "//*[@id='filter-sort']//*[contains(@class, 'select') and contains(text(), 'Od najdroższego')]")
    private WebElement sortFromMaxSelect;

    @FindBy(xpath = "//*[contains(@id, 'products')]/div[2]/div/div[1]/div[1]/h2/a")
    private WebElement firstOnListButton;

    @FindBy(xpath = "//*[@id='products-header']//*[contains(@class, 'count')]")
    private WebElement itemCountText;

    //*[@id="products-header"]/div[2]



    public Shopping(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String searchProduct)
    {
        Assert.assertTrue(isAt(DriverManager.timeoutDriverManager - 1));  // one second less than driver), "Can not find search box on Page");
        clickElement(searchBoxInput);
        searchBoxInput.sendKeys(searchProduct);
        searchBoxInput.sendKeys("\n");
    }

    public void choseByPrice(String priceMin, String priceMax)
    {
        Assert.assertTrue(isAt(DriverManager.timeoutDriverManager - 1));  // one second less than driver), "Can not find search box on Page");

        clickElement(priceMinInput);
        priceMinInput.sendKeys(priceMin);
        clickElement(priceMaxInput);
        priceMaxInput.sendKeys(priceMax);
        clickElement(filterPriceButton);


        try {
//            WebDriverWait wait = new WebDriverWait(driver, 10);
//            wait.until(ExpectedConditions.visibilityOf(itemCountText));
            if(itemCountText.getText().equalsIgnoreCase("(0)"))
            {
                Assert.fail("---------------No item found! Check and change filters.");
                return;
            }
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
//            WebDriverWait wait = new WebDriverWait(driver, 10);
//            wait.until(ExpectedConditions.visibilityOf(itemCountText));
            if(itemCountText.getText().equalsIgnoreCase("(0)"))
            {
                Assert.fail("---------------No item found! Check and change filters.");
                return;
            }
        }



        clickElement(priceMinInput);

        clickElement(filterSortSelect);
        clickElement(sortFromMinSelect);

        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0);");

//        clickUntilElementIsPresent(firstOnListButton, firstOnListButton);
        clickElement(firstOnListButton);

    }



}
