package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(xpath = "//*[@id='label-filter-availability-0']")
    private WebElement itemAvailabilityCheck;

    @FindBy(xpath = "//*[@id='product-top']//*[@class='product-button']//*[contains(@title, 'koszyk') and contains(text(), 'koszyk')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[@id='product-top']//*[@class='price-normal selenium-price-normal']")
    private WebElement priceNormalText;

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

        waitForVisible(itemCountText);
        System.out.println("Item count: " + itemCountText.getText());
        if(itemCountText.getText().equalsIgnoreCase("(0)"))
        {
            Assert.fail("---------------No item found! Check and change filters.");
            return;
        }
        clickElement(priceMinInput);

        clickElement(itemAvailabilityCheck);
        clickElement(filterSortSelect);
        clickElement(sortFromMinSelect);
        clickElement(filterSortSelect);

        clickElement(firstOnListButton);
    }

    public boolean checkProduct(double min, double max)
    {
        waitForVisible(addToCartButton);
        try {
            if(addToCartButton.isDisplayed())
            {
                String str = priceNormalText.getText();
                str = str.replaceAll("\\D+","");
                Double d = Double.valueOf(str);
                return (d > min) && (d < max);
            }
            else {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }

    }



}
