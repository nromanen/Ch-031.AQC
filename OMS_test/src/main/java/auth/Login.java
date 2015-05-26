package softserve.mvn_oms;

import org.openqa.selenium.WebDriver;

import tools.Browser;

public class Login {
	
	private static final String	LOGIN_BUTTON_NAME = "submit";

	private static final String USER_FIELD_NAME = "j_username"; 
	private static final String PASSWORD_FIELD_NAME= "j_password";
	
	private  WebDriver driver;
	private Browser browser;
	
	public Login(WebDriver driver)
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
	
	public CustomerUserInfoPage loginWithCustomerAccount(String userName, String password) {

		browser.findElementByName(USER_FIELD_NAME).clear();
		browser.findElementByName(USER_FIELD_NAME).sendKeys(userName);
		browser.findElementByName(PASSWORD_FIELD_NAME).clear();
		browser.findElementByName(PASSWORD_FIELD_NAME).sendKeys(password);
		browser.findElementByName(LOGIN_BUTTON_NAME).click();

		return new CustomerUserInfoPage(driver);
	}
	

}
