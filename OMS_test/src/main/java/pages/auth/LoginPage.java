package pages.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.administration.UserInfoPage;
import pages.ordering.CustomerUserInfoPage;
import tools.Browser;

public class LoginPage {

    private WebDriver driver;
    private Browser browser;
    private static String loginInputNameLocator = "j_username";
    private static String passwordInputNameLocator = "j_password";
    private static String submitButtonNameLocator = "submit";
    private static String cancelButtonNameLocator = "reset";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        browser = new Browser(driver);
    }

    public UserInfoPage login(String userName, String password) {
        browser.findElementByName(loginInputNameLocator).sendKeys(userName);
        browser.findElementByName(passwordInputNameLocator).sendKeys(password);
        browser.findElementByName(submitButtonNameLocator).click();
        return new UserInfoPage(driver);
    }

    public UserInfoPage logout(){
        browser.findElementById("logout").click();
        browser.alertAccept();
        return new UserInfoPage(driver);
    }

    public UserInfoPage loginEmpty(){
        browser.findElementById("submit").click();
        return new UserInfoPage(driver);
    }

    public CustomerUserInfoPage loginWithCustomerAccount(String userName, String password) {

        browser.findElementByName(loginInputNameLocator).clear();
        browser.findElementByName(loginInputNameLocator).sendKeys(userName);
        browser.findElementByName(passwordInputNameLocator).clear();
        browser.findElementByName(passwordInputNameLocator).sendKeys(password);
        browser.findElementByName(submitButtonNameLocator).click();

        return new CustomerUserInfoPage(driver);
    }

   }

