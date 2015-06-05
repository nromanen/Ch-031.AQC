package pages.ordering;

import org.openqa.selenium.WebDriver;
import tools.Browser;

public class OrderPage {
    private WebDriver driver;
    private Browser browser;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.browser = new Browser(driver);
    }

    public EditOrderPage goTo1EditOrder(){
        Browser browser = new Browser(driver);
        browser.findElementByXpath("//div[@id='list']/table//tr[2]//a[contains(text(),'Edit')]").click();
        return new EditOrderPage(driver);
    }

}
