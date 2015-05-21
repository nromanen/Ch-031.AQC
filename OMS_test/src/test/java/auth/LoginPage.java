package auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tools.Browser;

public class LoginPage {

    private WebDriver driver;
    private Browser browser;
    String loginInputNameLocator = "j_username";
    String passwordInputNameLocator = "j_password";
    String submitButtonNameLocator = "submit";
    String cancelButtonNameLocator = "reset";


    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }



    public UserInfoPage loginAs(String userName, String password) {

        browser.findElementByName(loginInputNameLocator).sendKeys(userName);
        browser.findElementByName(passwordInputNameLocator).sendKeys(password);
        browser.findElementByName(submitButtonNameLocator).click();
        return PageFactory.initElements(driver, UserInfoPage.class);
    }

   }

