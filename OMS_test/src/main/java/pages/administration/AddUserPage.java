package pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddUserPage extends AdministrationPage {

	private final static String LOGIN = "user";
	private final static String PASSWORD = "pass";
	private final static String FIRSTNAME = "Vasia";
	private final static String LASTNAME = "Pupkin";
	private final static String EMAIL = "fake@mail.com";

	private static String CREATE_NEW_USER_LINK_TAGNAME_LOCATOR = "h3";
	private static String LOGIN_FIELD_ID_LOCATOR = "login";
	private static String LOGIN_LINK_XPATH_LOCATOR = "//*[@id=\"userModel\"]/table/tbody/tr[1]/td[1]";
	private static String FIRSTNAME_FIELD_ID_LOCATOR = "firstName";
	private static String FIRSTNAME_LINK_XPATH_LOCATOR = "//*[@id=\"userModel\"]/table/tbody/tr[2]/td[1]";
	private static String LASTNAME_FIELD_ID_LOCATOR = "lastName";
	private static String LASTNAME_LINK_XPATH_LOCATOR = "//*[@id=\"userModel\"]/table/tbody/tr[3]/td[1]";
	private static String PASSWORD_FIELD_ID_LOCATOR = "password";
	private static String PASSWORD_LINK_XPATH_LOCATOR = "//*[@id=\"userModel\"]/table/tbody/tr[4]/td[1]";
	private static String CONFIRM_PASSWORD_ID_LOCATOR = "confirmPassword";
	private static String CONFIRM_LINK_XPATH_LOCATOR = "//*[@id=\"userModel\"]/table/tbody/tr[5]/td[1]";
	private static String EMAIL_FIELD_ID_LOCATOR = "email";
	private static String EMAIL_LINK_XPATH_LOCATOR = "//*[@id=\"userModel\"]/table/tbody/tr[6]/td[1]";
	private static String REGION_LINK_XPATH_LOCATOR = "//*[@id=\"userModel\"]/table/tbody/tr[7]/td[1]";
	private static String REGION_LIST_ID_LOCATOR = "regionID";
	private static String NORTH_REGION_XPATH_LOCATOR = "//*[@id=\"regionID\"]/option[1]";
	private static String EAST_REGION_XPATH_LOCATOR = "//*[@id=\"regionID\"]/option[2]";
	private static String SOUTH_REGION_XPATH_LOCATOR = "//*[@id=\"regionID\"]/option[3]";
	private static String WEST_REGION_XPATH_LOCATOR = "//*[@id=\"regionID\"]/option[4]";
	private static String CENTER_REGION_XPATH_LOCATOR = "//*[@id=\"regionID\"]/option[5]";
	private static String ROLE_LINK_XPATH_LOCATOR = "//*[@id=\"userModel\"]/table/tbody/tr[8]/td[1]";
	private static String ROLE_LIST_ID_LOCATOR = "roleID";
	private static String ADMINISTRTOR_ROLE_XPATH_LOCATOR = "//*[@id=\"roleID\"]/option[1]";
	private static String MERCHANDISER_ROLE_XPATH_LOCATOR = "//*[@id=\"roleID\"]/option[2]";
	private static String SUPERVISOR_ROLE_XPATH_LOCATOR = "//*[@id=\"roleID\"]/option[3]";
	private static String CUSTOMER_ROLE_XPATH_LOCATOR = "//*[@id=\"roleID\"]/option[4]";
	private static String SUBMIT_BUTTON_XPATH_LOCATOR = "//*[@id=\"userModel\"]/input[4]";
	private static String CANCEL_BUTTON_XPATH_LOCATOR = "//*[@id=\"userModel\"]/input[5]";

	public AddUserPage(WebDriver driver) {
		super(driver);
	}

	/** Diagon method */
	public UsersPage AddUser() {
		browser.findElementById(LOGIN_FIELD_ID_LOCATOR).clear();
		browser.findElementById(LOGIN_FIELD_ID_LOCATOR).sendKeys("user1");
		browser.findElementById(FIRSTNAME_FIELD_ID_LOCATOR).clear();
		browser.findElementById(FIRSTNAME_FIELD_ID_LOCATOR).sendKeys("name");
		browser.findElementById(LASTNAME_FIELD_ID_LOCATOR).clear();
		browser.findElementById(LASTNAME_FIELD_ID_LOCATOR).sendKeys("lastname");
		browser.findElementById(PASSWORD_FIELD_ID_LOCATOR).clear();
		browser.findElementById(PASSWORD_FIELD_ID_LOCATOR).sendKeys("pass");
		browser.findElementById(CONFIRM_PASSWORD_ID_LOCATOR).clear();
		browser.findElementById(CONFIRM_PASSWORD_ID_LOCATOR).sendKeys("pass");
		browser.findElementById(EMAIL_FIELD_ID_LOCATOR).clear();
		browser.findElementById(EMAIL_FIELD_ID_LOCATOR).sendKeys(
				"someemail@example.com");
		browser.findElementByCssSelector(SUBMIT_BUTTON_XPATH_LOCATOR).click();

		return new UsersPage(browser.getDriver());
	}

	/**
	 * method to get text of Create new user link 
	 */
	public String getCreateNewUserLinkText() {
		return browser.findElementByTagName(
				CREATE_NEW_USER_LINK_TAGNAME_LOCATOR).getText();
	}

	/**
	 * methods to get login name link, to find login field and to send key to login field
	 */
	public String getLoginNameLinkText() {
		return browser.findElementByXpath(LOGIN_LINK_XPATH_LOCATOR).getText();
	}
	
	public void findLoginField() {
		browser.findElementById(LOGIN_FIELD_ID_LOCATOR);
	}

	public void findLoginFieldClearAndSendKey(String testKey) {
		browser.findElementById(LOGIN_FIELD_ID_LOCATOR).clear();
		browser.findElementById(LOGIN_FIELD_ID_LOCATOR).sendKeys(testKey);
	}

	/**
	 * methods to get first name link text, to find first name's field and to send key to it
	 */
	public String getFirstNameLinkText() {
		return browser.findElementByXpath(FIRSTNAME_LINK_XPATH_LOCATOR)
				.getText();
	}

	public void findFirstNameField() {
		browser.findElementById(FIRSTNAME_FIELD_ID_LOCATOR);
	}

	public void findFirstNameFieldClearAndSendKey(String testKey) {
		browser.findElementById(FIRSTNAME_FIELD_ID_LOCATOR).clear();
		browser.findElementById(FIRSTNAME_FIELD_ID_LOCATOR).sendKeys(testKey);
	}

	/**
	 * methods to get text of last name link, find last name's field and send some key to it
	 */
	public String getLastNameLinkText() {
		return browser.findElementByXpath(LASTNAME_LINK_XPATH_LOCATOR)
				.getText();
	}

	public void findLastNameField() {
		browser.findElementById(LASTNAME_FIELD_ID_LOCATOR);
	}

	public void findLastNameFieldClearAndSendKey(String testKey) {
		browser.findElementById(LASTNAME_FIELD_ID_LOCATOR).clear();
		browser.findElementById(LASTNAME_FIELD_ID_LOCATOR).sendKeys(testKey);
	}

	/**
	 * methods to get text of password link, find pasword's field and to send some key to it
	 */
	public WebElement passwordLink(){
		return browser.findElementByXpath(PASSWORD_LINK_XPATH_LOCATOR);
	}
	
	public String getPasswordLinkText() {
		return browser.findElementByXpath(PASSWORD_LINK_XPATH_LOCATOR)
				.getText();
	}

	public void findPasswordField() {
		browser.findElementById(PASSWORD_FIELD_ID_LOCATOR);
	}

	public void findPasswordFieldClearAndSendKey(String testKey) {
		browser.findElementById(PASSWORD_FIELD_ID_LOCATOR).clear();
		browser.findElementById(PASSWORD_FIELD_ID_LOCATOR).sendKeys(testKey);
	}

	/**
	 * methods to get text of confirm password link, to find confirm password's field and to send some key to it
	 */
	public String getConfirmPasswordLinkText() {
		return browser.findElementByXpath(CONFIRM_LINK_XPATH_LOCATOR).getText();
	}

	public void findConfirmPasswordField() {
		browser.findElementById(CONFIRM_PASSWORD_ID_LOCATOR);
	}

	public void findConfirmPasswordClearAndSendKey(String testKey) {
		browser.findElementById(CONFIRM_PASSWORD_ID_LOCATOR).clear();
		browser.findElementById(CONFIRM_PASSWORD_ID_LOCATOR).sendKeys(testKey);
	}


	/**
	 * methods to get text of email address link, to find email address' field and to send some key to it
	 */
	public String getEmailAddressLinkText() {
		return browser.findElementByXpath(EMAIL_LINK_XPATH_LOCATOR).getText();
	}

	public void findEmailAdressField() {
		browser.findElementById(EMAIL_FIELD_ID_LOCATOR);
	}

	public void findEmailAdressClearAndSendKey(String testKey) {
		browser.findElementById(EMAIL_FIELD_ID_LOCATOR).clear();
		browser.findElementById(EMAIL_FIELD_ID_LOCATOR).sendKeys(testKey);
	}
	
	/**
	 * methods to get text of region link, to find region's list
	 */
	public String getRegionLinkText() {
		return browser.findElementByXpath(REGION_LINK_XPATH_LOCATOR).getText();
	}

	public void findRegionList() {
		browser.findElementById(REGION_LIST_ID_LOCATOR);
	}
	
	public void findRegionListAndClick(){
		browser.findElementById(REGION_LIST_ID_LOCATOR).click();
	}
	
	public String getNorthRegionText(){
		return browser.findElementByXpath(NORTH_REGION_XPATH_LOCATOR).getText();
	}
	
	public void findNorthRegionAndClick(){
		browser.findElementByXpath(NORTH_REGION_XPATH_LOCATOR).click();
	}
	
	public String getEastRegionText(){
		return browser.findElementByXpath(EAST_REGION_XPATH_LOCATOR).getText();
	}
	
	public void findEastRegionAndClick(){
		browser.findElementByXpath(EAST_REGION_XPATH_LOCATOR).click();
	}
	
	public String getSouthRegionText(){
		return browser.findElementByXpath(SOUTH_REGION_XPATH_LOCATOR).getText();
	}
	
	public void findSouthRegionAndClick(){
		browser.findElementByXpath(SOUTH_REGION_XPATH_LOCATOR).click();
	}
	
	public String getWestRegionText(){
		return browser.findElementByXpath(WEST_REGION_XPATH_LOCATOR).getText();
	}
	
	public void findWestRegionAndClick(){
		browser.findElementByXpath(WEST_REGION_XPATH_LOCATOR).click();
	}
	
	public String getCenterRegionText(){
		return browser.findElementByXpath(CENTER_REGION_XPATH_LOCATOR).getText();
	}
	
	public void findCenterRegionAndClick(){
		browser.findElementByXpath(CENTER_REGION_XPATH_LOCATOR).click();
	}
	
	/**
	 * methods to get text of role link, to find role's list
	 */
	public String getRoleLinkText() {
		return browser.findElementByXpath(ROLE_LINK_XPATH_LOCATOR).getText();
	}

	public void findRoleList() {
		browser.findElementById(ROLE_LIST_ID_LOCATOR);
	}
	
	public void findRoleListAndClick(){
		browser.findElementById(ROLE_LIST_ID_LOCATOR);
	}
	
	public String getAdministrationRoleText(){
		return browser.findElementByXpath(ADMINISTRTOR_ROLE_XPATH_LOCATOR).getText();
	}
	
	public void findAdministrationRoleAndClick(){
		browser.findElementByXpath(ADMINISTRTOR_ROLE_XPATH_LOCATOR).click();
	}
	
	public String getMerchandiserRoleText(){
		return browser.findElementByXpath(MERCHANDISER_ROLE_XPATH_LOCATOR).getText();
	}
	
	public void findMerchandiserRoleAndClick(){
		browser.findElementByXpath(MERCHANDISER_ROLE_XPATH_LOCATOR).click();
	}
	
	public String getSupervisorRoleText(){
		return browser.findElementByXpath(SUPERVISOR_ROLE_XPATH_LOCATOR).getText();
	}
	
	public void findSupervisorRoleAndClick(){
		browser.findElementByXpath(MERCHANDISER_ROLE_XPATH_LOCATOR).click();
	}
	
	public String getCustomerRoleText(){
		return browser.findElementByXpath(CUSTOMER_ROLE_XPATH_LOCATOR).getText();
	}

	public void findCustomerRoleAndClick(){
		browser.findElementByXpath(CUSTOMER_ROLE_XPATH_LOCATOR).click();
	}
	
	/**
	 * methods to get text of submit button, to find submit button and to click it
	 */
	public String getSubmitButtonText() {
		return browser.findElementByXpath(SUBMIT_BUTTON_XPATH_LOCATOR)
				.getText();
	}

	public void findSubmitButton() {
		browser.findElementByXpath(SUBMIT_BUTTON_XPATH_LOCATOR);
	}

	public void findSubmitButtonAndClick() {
		browser.findElementByXpath(SUBMIT_BUTTON_XPATH_LOCATOR).click();
	}
	
	/**
	 * methods to get text of cancel button, to find cancel button and to click it
	 */
	public String getCancelButtonText() {
		return browser.findElementByXpath(CANCEL_BUTTON_XPATH_LOCATOR)
				.getText();
	}

	public void findCancelButton() {
		browser.findElementByXpath(CANCEL_BUTTON_XPATH_LOCATOR);
	}	

	public void findCancelButtonAndClick() {
		browser.findElementByXpath(CANCEL_BUTTON_XPATH_LOCATOR).click();
	}
	
}
