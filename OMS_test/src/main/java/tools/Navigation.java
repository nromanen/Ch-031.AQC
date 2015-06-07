package tools;

import org.openqa.selenium.WebDriver;
import pages.auth.UserInfoPage;

public class Navigation {
    private WebDriver driver;
    private Browser browser;
    private static String loginInputNameLocator = "j_username";
    private static String passwordInputNameLocator = "j_password";
    private static String submitButtonNameLocator = "submit";

    public Navigation(WebDriver driver) {
        this.driver = driver;
        browser = new Browser(driver);
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public UserInfoPage logout() {
        browser.findElementById("logout").click();
        browser.alertAccept();
        return new UserInfoPage(driver);
    }

    public UserInfoPage login(String userName, String password) {
        browser.findElementByName(loginInputNameLocator).sendKeys(userName);
        browser.findElementByName(passwordInputNameLocator).sendKeys(password);
        browser.findElementByName(submitButtonNameLocator).click();
        return new UserInfoPage(driver);
    }
}
