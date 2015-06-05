package pages.ordering;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tools.Browser;
import tools.TableRow;

import java.util.ArrayList;
import java.util.List;

public class ItemManagementPage {
    private WebDriver driver;
    private Browser browser;

    private static String filterBySelectBoxIdLocator = "field";
    private static String productTableIdLocator = "table";
    private static String tableRowTagNameLocator = "tr";
    private static String tableHeaderTagNameLocator = "th";
    private static String showItemLinkXPathLocator = "//div[@id='list']/p/a";
    private static String createReportLinkTextLocator = "create report";
    private static String addProductLinkTextLocator = "Add Product";


    public ItemManagementPage(WebDriver driver) {

        this.driver = driver;
        browser = new Browser(driver);
    }

    public List<String> getFilterValues() {
        List<String> values = new ArrayList<String>();
        Select select = new Select(browser.findElementById(filterBySelectBoxIdLocator));
        for (WebElement option : select.getOptions()) {
            values.add(option.getText());
        }
        return values;
    }

    public String getFilterCurrentValue() {
        Select select = new Select(browser.findElementById(filterBySelectBoxIdLocator));
        return select.getFirstSelectedOption().getText();

    }

    public List<String> getProductTableHeadersNames() {
        List<String> names = new ArrayList<String>();
        List<WebElement> headers = browser.findElementsByTagName(tableHeaderTagNameLocator);
        for (WebElement header : headers) {
            names.add(header.getText());
        }
        return names;
    }

    public Integer getProductTableElementSize() {
        List<WebElement> rows = browser.findElementsByTagName(tableRowTagNameLocator);
        return rows.size() - 1;
    }

    public String getShowItemText() {
        return browser.findElementByXpath(showItemLinkXPathLocator).getText();
    }

    public void clickShowItemLink() {
        browser.findElementByXpath(showItemLinkXPathLocator).click();
    }

    public TableRow findProductByNameInTable(String productName) {
        List<WebElement> rows = browser.findElementsByTagName(tableRowTagNameLocator);
        for (WebElement webElement : rows) {
            TableRow tableRow = new TableRow(webElement);
            if (tableRow.getNthColumnValue(1).equals(productName)) {
                return tableRow;
            }
        }
        return null;
    }

    public AddProductPage goToAddProduct() {
        browser.findElementByLinkText(addProductLinkTextLocator).click();
        return PageFactory.initElements(driver, AddProductPage.class);
    }
}


