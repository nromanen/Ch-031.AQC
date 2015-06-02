package pages;

import org.openqa.selenium.WebDriver;
import pages.auth.UserInfoPage;
import tools.Browser;


public class BasePage {

    protected WebDriver driver;
    protected Browser browser;

    private static final String BASEURL = "http://localhost:8080/OMS/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        browser = new Browser(driver);
    }

    public UserInfoPage gotoRoot() {
        driver.get(BASEURL);
        return new UserInfoPage(driver);
    }
   }

