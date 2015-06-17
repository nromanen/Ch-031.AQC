package pages.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import tools.Browser;

public class LoginPage extends BasePage {
	
	private static final String USER_FIELD_NAME = "j_username"; 
	private static final String PASSWORD_FIELD_NAME= "j_password";
	private static final String LOGIN_BUTTON_NAME = "submit";
	private static final String CANCEL_BUTTON_NAME = "reset";
	private static final String LOGOUT_ID = "logout";
	
	private static String headerItemLinkTagNameLocator = "h1";
	private static String userNameItemLinkXPathLocator = "//div[@id='edit']/fieldset/form/table/tbody/tr[1]/td[1]";
	private static String userNameInputLineXPathLocator = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[1]/td[2]/input";
	private static String passwordItemLinkXpathLocator = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[2]/td[1]";
	private static String passwordInputLineXPathLocator = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[2]/td[2]/input";
	private static String rememberMeItemLinkXPathLocator = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[3]/td[2]";
	private static String rememberMeCheckBoxXPathLocator = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[3]/td[1]/input";
	private static String loginItemLinkXPathLocator = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[4]/td[1]/input";
	private static String loginButtonXPathLocator = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[4]/td[1]/input";
	private static String resetItemLinkXpathLocator = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[4]/td[2]/input";
	private static String resetButtonXPathLocator = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[4]/td[2]/input";
	

	private Browser browser;
		
	public LoginPage(WebDriver driver){	
		super(driver);
		browser = new Browser(driver);
	}
	
	
	
	public UserInfoPage login(String userName, String password) {

		browser.findElementByName(USER_FIELD_NAME).clear();
		browser.findElementByName(USER_FIELD_NAME).sendKeys(userName);
		browser.findElementByName(PASSWORD_FIELD_NAME).clear();
		browser.findElementByName(PASSWORD_FIELD_NAME).sendKeys(password);
		browser.findElementByName(LOGIN_BUTTON_NAME).click();
		return new UserInfoPage();
	}
	
	public UserInfoPage logout(){
        browser.findElementById(LOGOUT_ID).click();
        browser.alertAccept();
        return new UserInfoPage();
    }

    public UserInfoPage loginEmpty(){
    	browser.findElementByName(LOGIN_BUTTON_NAME).click();
        return new UserInfoPage();
    }
    
    
    public String getHeaderItemText() {
        return browser.findElementByTagName(headerItemLinkTagNameLocator).getText();
    }
    
    public String getUserNameItemText(){
    	return browser.findElementByXpath(userNameItemLinkXPathLocator).getText();
    }
    
    public String getPasswordItemText(){
    	return browser.findElementByXpath(passwordItemLinkXpathLocator).getText();
    }
    
    public String getRememberMeItemText(){
    	return browser.findElementByXpath(rememberMeItemLinkXPathLocator).getText();
    }
    
    public String getLoginItemText(){
    	return browser.findElementByXpath(loginItemLinkXPathLocator).getText();
    }
    
    public String getResetItemText(){
    	return browser.findElementByXpath(resetItemLinkXpathLocator).getText();
    }
    
    public Boolean isUserNameLine(){
        WebElement element = browser.findElementByXpath(userNameInputLineXPathLocator);
        return element!=null ? true:false;
    }
    
    public Boolean isPasswordLine(){
        WebElement element = browser.findElementByXpath(passwordInputLineXPathLocator);
        return element!=null ? true:false;
    }
    
    public Boolean isRememberMeCheckBox(){
        WebElement element = browser.findElementByXpath(rememberMeCheckBoxXPathLocator);
        return element!=null ? true:false;
    }
    
    public Boolean isLoginButton(){
        WebElement element = browser.findElementByXpath(loginButtonXPathLocator);
        return element!=null ? true:false;
    }
    
    public Boolean isResetButton(){
        WebElement element = browser.findElementByXpath(resetButtonXPathLocator);
        return element!=null ? true:false;
    }
    
	
}
