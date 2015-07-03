package pages.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.BasePage;

public class LoginPage extends BasePage{

    private static final String MESSAGE_LOCATOR = "font";
    private static String HEADER_TAGNAME_LOCATOR = "h1";
	private static String USER_NAME_LINK_XPATH_LOCATOR = "//div[@id='edit']/fieldset/form/table/tbody/tr[1]/td[1]";
	private static String USER_NAME_INPUT_LINE_XPATH_LOCATOR = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[1]/td[2]/input";
	private static String PASSWORD_LINK_XPATH_LOCATOR = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[2]/td[1]";
	private static String PASSWORD_INPUT_LINE_XPATH_LOCATOR = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[2]/td[2]/input";
	private static String REMEMBER_ME_LINK_XPATH_LOCATOR = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[3]/td[2]";
	private static String REMEMBER_ME_CHECKBOX_XPATH_LOCATOR = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[3]/td[1]/input";
	private static String LOGIN_LINK_XPATH_LOCATOR = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[4]/td[1]/input";
	private static String LOGIN_BUTTON_XPTAH_LOCATOR = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[4]/td[1]/input";
	private static String RESET_LINK_XPATH_LOCATOR = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[4]/td[2]/input";
	private static String RESET_BUTTON_XPATH_LOCATOR = "//*[@id=\"edit\"]/fieldset/form/table/tbody/tr[4]/td[2]/input";
    

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public UserInfoPage login(String userName, String password) {
    	return super.login(userName, password);
    }

    public UserInfoPage logout() {
        return super.logout();
    }
    
    public String findMessageText(){
    	return browser.findElementByCssSelector(MESSAGE_LOCATOR).getText();
    }
    
    public String getHeaderItemText() {
        return browser.findElementByTagName(HEADER_TAGNAME_LOCATOR).getText();
    }
    
    public String getUserNameItemText(){
    	return browser.findElementByXpath(USER_NAME_LINK_XPATH_LOCATOR).getText();
    }
    
    public String getPasswordItemText(){
    	return browser.findElementByXpath(PASSWORD_LINK_XPATH_LOCATOR).getText();
    }
    
    public String getRememberMeItemText(){
    	return browser.findElementByXpath(REMEMBER_ME_LINK_XPATH_LOCATOR).getText();
    }
    
    public String getLoginItemText(){
    	return browser.findElementByXpath(LOGIN_LINK_XPATH_LOCATOR).getText();
    }
    
    public String getResetItemText(){
    	return browser.findElementByXpath(RESET_LINK_XPATH_LOCATOR).getText();
    }
    
    public Boolean isUserNameLine(){
        WebElement element = browser.findElementByXpath(USER_NAME_INPUT_LINE_XPATH_LOCATOR);
        return element!=null ? true:false;
    }
    
    public Boolean isPasswordLine(){
        WebElement element = browser.findElementByXpath(PASSWORD_INPUT_LINE_XPATH_LOCATOR);
        return element!=null ? true:false;
    }
    
    public Boolean isRememberMeCheckBox(){
        WebElement element = browser.findElementByXpath(REMEMBER_ME_CHECKBOX_XPATH_LOCATOR);
        return element!=null ? true:false;
    }
    
    public Boolean isLoginButton(){
        WebElement element = browser.findElementByXpath(LOGIN_BUTTON_XPTAH_LOCATOR);
        return element!=null ? true:false;
    }
    
    public Boolean isResetButton(){
        WebElement element = browser.findElementByXpath(RESET_BUTTON_XPATH_LOCATOR);
        return element!=null ? true:false;
    }    
}
