package pages.auth;

import org.openqa.selenium.WebDriver;
import tools.Browser;

public class LoginPage {
	
	private static final String USER_FIELD_NAME = "j_username"; 
	private static final String PASSWORD_FIELD_NAME= "j_password";
	private static final String LOGIN_BUTTON_NAME = "submit";
	private static final String CANCEL_BUTTON_NAME = "reset";
	private static final String LOGOUT_ID = "logout";
	
	private  WebDriver driver;
	private Browser browser;
		
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		browser = new Browser(driver);
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public Browser getBrowser(){ 
		return this.browser;
	}
	
	public UserInfoPage login(String userName, String password) {

		browser.findElementByName(USER_FIELD_NAME).clear();
		browser.findElementByName(USER_FIELD_NAME).sendKeys(userName);
		browser.findElementByName(PASSWORD_FIELD_NAME).clear();
		browser.findElementByName(PASSWORD_FIELD_NAME).sendKeys(password);
		browser.findElementByName(LOGIN_BUTTON_NAME).click();
		return new UserInfoPage(driver);
	}
	
	public UserInfoPage logout(){
        browser.findElementById(LOGOUT_ID).click();
        browser.alertAccept();
        return new UserInfoPage(driver);
    }

    public UserInfoPage loginEmpty(){
    	browser.findElementByName(LOGIN_BUTTON_NAME).click();
        return new UserInfoPage(driver);
    }
	
}
