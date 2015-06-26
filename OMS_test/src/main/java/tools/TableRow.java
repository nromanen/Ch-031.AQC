package tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represents a HTML table row. It provides an easy to use method for retrieving data from columns.
 * @author Olya.
 */
public class TableRow {

    private WebElement rowWebElement;

    public TableRow(WebElement rowWebElement) {
        this.rowWebElement = rowWebElement;
    }

    /**
     * This method returns the text value on the n-th columns in the row.
     * @param columnIndex - the index of the column which text data will be returned.
     * @return n-th column text date.
     */
    public String getNthColumnValue(int columnIndex) {
        return getNthColumnElement(columnIndex).getText();
    }

    public WebElement getNthColumnElement(int columnIndex) {
        return rowWebElement.findElements(By.tagName("td")).get(columnIndex);
    }
}