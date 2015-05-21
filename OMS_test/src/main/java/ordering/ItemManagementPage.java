package ordering;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tools.Browser;

import java.util.ArrayList;
import java.util.List;

public class ItemManagementPage {
    private WebDriver driver;
    private Browser browser;

    String filterBySelectBoxIdLocator = "field";
    String productTableIdLocator = "table";
    String showItemLinkXPathLocator = "//div[@id='list']/p/a";
    String createReportLinkTextLocator = "create report";
    String addProductLinkTextLocator = "Add Product";


    public ItemManagementPage(WebDriver driver) {

        this.driver = driver;
        browser = new Browser(driver);
    }

//    public ItemManagementPage(Browser browser) {
//        this.browser = new Browser(driver);
//    }

    public List<String> getFilterByValues() {
        List<String> values = new ArrayList<String>();
        Select select = new Select(browser.findElementById(filterBySelectBoxIdLocator));
        for (WebElement option : select.getOptions()) {
            values.add(option.getText());
        }
        return values;
    }


    public List<String> getFilterByValuesWithSelectWOSelectClass() {
        List<String> values = new ArrayList<String>();
        List<WebElement> options = browser.findElementById(filterBySelectBoxIdLocator).findElements(By.tagName("option"));
        for (WebElement option : options) {
            values.add(option.getText());
        }
        return values;
    }

    public String getFilterByCurrentValue() {
        Select select = new Select(browser.findElementById(filterBySelectBoxIdLocator));
        return select.getFirstSelectedOption().getText();

    }

    public List<String> getProductTableHeadersNames() {
        List<String> names = new ArrayList<String>();
        List<WebElement> headers = browser.findElementById(productTableIdLocator).findElements(By.tagName("th"));
        for (WebElement header : headers) {
            names.add(header.getText());
        }
        return names;
    }

    public Integer getProductTableElementSize() {
        List<WebElement> rows = browser.findElementById(productTableIdLocator).findElements(By.tagName("tr"));
        return  rows.size() - 1;
    }

    public String getShowItemText() {
        return browser.findElementByXpath(showItemLinkXPathLocator).getText();
    }

    public void clickShowItemLink(){
        browser.findElementByXpath(showItemLinkXPathLocator).click();
    }

  /*  public ReportPage goToCreateReport(){
        browser.findElementByLinkText(createReportLinkTextLocator).click();
        return PageFactory.initElements(driver, ReportPage.class);
    }
*/
    public AddProductPage goToAddProduct() {
        browser.findElementByLinkText(addProductLinkTextLocator).click();
        return PageFactory.initElements(driver, AddProductPage.class);
    }
}


