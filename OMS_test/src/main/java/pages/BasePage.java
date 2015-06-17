package pages;

import org.openqa.selenium.WebDriver;
import pages.auth.UserInfoPage;
import tools.Browser;


public class BasePage {

//    protected WebDriver driver;
    protected Browser browser = new Browser();

    private static final String BASEURL = "http://localhost:8083/OMS/";
    private static String loginInputNameLocator = "j_username";
    private static String passwordInputNameLocator = "j_password";
    private static String submitButtonNameLocator = "submit";

    public BasePage(WebDriver driver) {
        browser.setDriver(driver);
    }

    public Browser getBrowser() {
        return this.browser;
    }

    public UserInfoPage gotoRoot() {
        browser.getDriver().get(BASEURL);
        return new UserInfoPage();
    }

    public void goToUrl(String url) {
        browser.getDriver().get(url);
    }

    public UserInfoPage logout() {
        browser.findElementById("logout").click();
        browser.alertAccept();
        return new UserInfoPage();
    }

    /**
     * hint: it's not necessary to return UserInfoPage
     * You may use just any_page.login(name, pass)
     */
    public UserInfoPage login(String userName, String password) {
        browser.findElementByName(loginInputNameLocator).sendKeys(userName);
        browser.findElementByName(passwordInputNameLocator).sendKeys(password);
        browser.findElementByName(submitButtonNameLocator).click();
        return new UserInfoPage();
    }
   }
