/**
 * author: Alexander Melnychuk
 * This is a tool for checking a value of any table cell.
 * To choose the right cell you must set id of a parent div, column's title and index of your cell
 * (from up to down)
 */

package tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class CheckTableValue {
    private WebDriver driver;
    public CheckTableValue(WebDriver driver) {
        this.driver = driver;
    }

    public String findValue(String id, String th,int tdId) throws Exception{
/*        String mas = (driver.findElement(By.xpath("//div[@id='"+id+"']//table//td["+td+"]//text()"))).toString();
        List<String> list = Arrays.asList(mas.split("\\s*,\\s*"));
        String[] value = new String[list.size()];
        list.toArray(value);
        return value[tdId];*/
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        String js ="function find(){"+
                "var table = document.getElementById(\""+id+"\").getElementsByTagName(\"table\")[0];"+
                "var searchText = \""+th+"\";"+
                "var found;"+

                "for (var i = 0; i < table.rows[0].cells.length; i++) {"+
                "   if (table.rows[0].cells[i].outerText == searchText) {"+

                "   found = table.rows[0].cells[i];"+
                "  console.log(found);"+
                " count = i;"+
                "console.log(count);"+
                "break;"+
                "}}"+

                "var td = [];"+
                "for (var j= 1; j<table.rows.length; j++) {"+
                "var el = table.rows[j].cells[count].outerText;"+
                "td.push(el);"+
                "}"+
                "return td;}"+
                "return find();";
        Object obj = (Object) executor.executeScript(js);
        ArrayList value = new ArrayList();
        value = (ArrayList) obj;
        return (String)value.get(tdId);
    }
}
