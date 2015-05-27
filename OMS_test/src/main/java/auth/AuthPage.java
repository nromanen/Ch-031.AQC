package auth;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tools.Browser;

public class AuthPage {
    private Browser browser;
    private WebDriver driver;
    private final String username = "j_username";
    private final String logpassword = "j_password";


    public AuthPage(WebDriver driver) {
        this.driver = driver;
        browser = new Browser(driver);
    }

    public ResultPage login(String login, String password){
        browser.findElementByName(username).sendKeys(login);
        browser.findElementByName(logpassword).sendKeys(password);
        browser.findElementByName("submit").click();
        return new ResultPage(driver);
    }

    public ResultPage logout(){
        browser.findElementById("logout").click();
        browser.alertAccept();
        return new ResultPage(driver);
    }

    public ResultPage loginEmpty(){
        browser.findElementById("submit").click();
        return new ResultPage(driver);
    }
}