package auth;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tools.Browser;

public class Auth {
    private Browser browser;
    private WebDriver driver;
    private final String username = "j_username";
    private final String password = "j_password";


    public Auth(WebDriver driver) {
        this.driver = driver;
    }

    public ResultPage login(String login, String password){
        browser.findElementByName(username).sendKeys(login);
        browser.findElementByName(password).sendKeys(password);
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