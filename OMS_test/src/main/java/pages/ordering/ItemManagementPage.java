package pages.ordering;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;
import tools.Browser;
import tools.TableRow;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describe Item Management Page functionality and provides a way to use it.
 * @author Olya.
 */
public class ItemManagementPage extends BasePage {

    private static String filterBySelectBoxIdLocator = "field";
    private static String productTableIdLocator = "table";
    private static String tableRowTagNameLocator = "tr";
    private static String tableHeaderTagNameLocator = "th";
    private static String showItemLinkXPathLocator = "//div[@id='list']/p/a";
    private static String createReportLinkTextLocator = "create report";
    private static String addProductLinkTextLocator = "Add Product";


    public ItemManagementPage(WebDriver driver) {
        super(driver);

    }

    /**
     * This method returns the values from drop down list in "Search by" section.
     *
     * @return list which contains the values from drop down list in "Search by" section.
     */
    public List<String> getFilterValues() {
        List<String> values = new ArrayList<String>();
        Select select = new Select(browser.findElementById(filterBySelectBoxIdLocator));
        for (WebElement option : select.getOptions()) {
            values.add(option.getText());
        }
        return values;
    }

    /**
     * This method returns the currently selected value from drop down list in "Search by" section.
     *
     * @return the currently selected value from drop down list in "Search by" section.
     */
    public String getFilterCurrentValue() {
        Select select = new Select(browser.findElementById(filterBySelectBoxIdLocator));
        return select.getFirstSelectedOption().getText();

    }

    /**
     * This method returns names of "Products" table columns.
     *
     * @return list which contains the names of table columns.
     */
    public List<String> getProductTableHeadersNames() {
        List<String> names = new ArrayList<String>();
        List<WebElement> headers = browser.findElementsByTagName(tableHeaderTagNameLocator);
        for (WebElement header : headers) {
            names.add(header.getText());
        }
        return names;
    }

    /**
     * This method returns the row count of "Products" table.
     *
     * @return the row count of "Products" table.
     */
    public Integer getProductTableElementSize() {
        List<WebElement> rows = browser.findElementsByTagName(tableRowTagNameLocator);
        return rows.size() - 1;
    }

    /**
     * This method returns the text value of the show item link.
     *
     * @return the text value of the show item link.
     */
    public String getShowItemText() {
        return browser.findElementByXpath(showItemLinkXPathLocator).getText();
    }

    /**
     * This method makes a click on the show item link.
     */
    public void clickShowItemLink() {
        browser.findElementByXpath(showItemLinkXPathLocator).click();
    }

    /**
     * This method find and returns the row with given product name from "Product" table.
     *
     * @param productName the value you need to find.
     * @return {@link tools.TableRow} when found and null if not found.
     */
    public TableRow findProductByNameInTable(String productName) {
        List<WebElement> rows = browser.findElementsByTagName(tableRowTagNameLocator);
        for (int i = 1; i< rows.size(); i++) {
            WebElement webElement = rows.get(i);
            TableRow tableRow = new TableRow(webElement);
            if (tableRow.getNthColumnValue(0).equals(productName)) {
                return tableRow;
            }
        }
        return null;
    }

    /**
     * This method clicks on the Add product link.
     *
     * @return {@link pages.ordering.AddProductPage}.
     */
    public AddProductPage goToAddProduct() {
        browser.findElementByLinkText(addProductLinkTextLocator).click();
        return PageFactory.initElements(browser.getDriver(), AddProductPage.class);
    }

    public AddProductPage clickEditLinkOnProduct(String productName) {
        findProductByNameInTable(productName).getNthColumnElement(3).findElement(By.tagName("a")).click();
        return PageFactory.initElements(browser.getDriver(), AddProductPage.class);
    }

    public void clickDeleteLinkOnProductAndAccept(String productName) {
        findProductByNameInTable(productName).getNthColumnElement(4).findElement(By.tagName("a")).click();
        browser.alertAccept();

    }

    public void clickDeleteLinkOnProductAndDismiss(String productName) {
        findProductByNameInTable(productName)
                .getNthColumnElement(4)
                .findElement(By.tagName("a"))
                .click();
        browser.alertDismiss();
    }
}
