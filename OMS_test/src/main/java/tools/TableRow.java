package tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Olya on 05.06.2015.
 */
public class TableRow {

    private WebElement rowWebElement;

    public TableRow(WebElement rowWebElement) {
        this.rowWebElement = rowWebElement;
    }

    public String getNthColumnValue(int columnIndex) {
        String nthSelector = "//td[" + columnIndex +"]";
        return rowWebElement.findElement(By.xpath(nthSelector)).getText();
    }
}
