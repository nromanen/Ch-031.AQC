package pages.ordering;

import org.openqa.selenium.WebDriver;
import tools.Browser;

public class EditOrderPage {

    private WebDriver driver;
    private Browser browser;

    public EditOrderPage(WebDriver driver) {
        this.driver = driver;
        browser = new Browser(driver);
    }


    public void addItemClick(){
        browser.findElementByCssSelector("input[value=\"Add Item\"]").click();
    }




}
