package pages.ordering;

import org.openqa.selenium.WebDriver;
import tools.Browser;

public class EditOrderPage {

    private WebDriver driver;
    private Browser browser;

    public EditOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public EditOrderPage(Browser browser) {
        this.browser = new Browser(driver);
    }




}
