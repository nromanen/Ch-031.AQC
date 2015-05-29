package ordering;

import org.openqa.selenium.WebDriver;
import tools.Browser;

public class AddItemPage {

    private WebDriver driver;
    private Browser browser;

    public AddItemPage(WebDriver driver) {
        this.driver = driver;
        this.browser = new Browser(driver);
    }

    public void clickDoneButton(){
        browser.findElementByCssSelector("input[value=\"Done\"]").click();
    }

    public void clickCancelButton(){
        browser.findElementByCssSelector("input[value=\"Cancel\"]").click();
    }

    public void clickResetButton(){
        browser.findElementByCssSelector("input[value=\"Reset\"]").click();
    }

    public void selectFirstItem(){
        browser.findElementByXpath("//form[@id='selectFrom1']//a").click();
    }

    public void setItemQuantity(){
        browser.findElementByName("quantity").clear();
        //browser.findElementByName("quantity").sendkeys(quantity);
    }



}