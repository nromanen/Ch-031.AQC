package pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.BasePage;

public class AdministrationPage extends BasePage {

	public AdministrationPage(WebDriver driver) {
		super(driver);
	}

	private static String HEADER_LINK_TAGNAME = "h1";
	private static String UNDER_HEADER_LINK_TAGNAME = "h2";
	private static String ADMINISTRATION_TAB_XPATH_LOCATOR = "//*[@id=\"nav\"]/li[1]/a";
	private static String USERINFO_TAB_XPATH_LOCATOR = "//*[@id=\"nav\"]/li[2]/a";
	private static String CREATE_NEW_USER_LINK_XPATH_LOCATOR = "//*[@id=\"list\"]/a";
	private static String FOUND_USERS_LINK_XPATH_LOCATOR = "//*[@id=\"list\"]/h4[1]/text()";
	private static String NUMBER_OF_FOUND_USERS_ID_LOCATOR = "usersFound";
	private static String FIELD_FILTER_LINK_XPATH_LOCATOR = "//*[@id=\"searchForm\"]/label";
	private static String COLUMN_FILTER_ID_LOCATOR = "field";
	private static String CONDITION_FILTER_ID_LOCATOR = "condition";
	private static String SEARCH_FIELD_ID_LOCATOR = "searchField";
	private static String SEARCH_BUTTON_XPATH_LOCATOR = "//*[@id=\"searchForm\"]/input[2]";
	private static String SHOW_ITEMS_XPATH_LOCATOR = "//*[@id=\"list\"]/p/a";
	private static String FIRST_NAME_COLUMN_LINK_XPATH_LOCATOR = "//*[@id=\"table\"]/thead/tr/th[1]/a";
	private static String FIRST_ELEMENT_INCOLUMN_FIRST_NAME_XPATH_LOCATOR = "//*[@id=\"table\"]/tbody/tr[1]/td[1]";
	private static String LAST_NAME_COLUMN_XPATH_LOCATOR = "//*[@id=\"table\"]/thead/tr/th[2]/a";
	private static String LOGIN_COLUMN_LINK_XPATH_LOCATOR = "//*[@id=\"table\"]/thead/tr/th[3]/a";
	private static String ROLE_COLUMN_LINK_XPATH_LOCATOR = "//*[@id=\"table\"]/thead/tr/th[4]/a";
	private static String REGION_COLUMN_LINK_XPATH_LOCATOR = "//*[@id=\"table\"]/thead/tr/th[5]/a";
	private static String EDIT_COLUMN_LINK_XPATH_LOCATOR = "//*[@id=\"table\"]/thead/tr/th[6]";
	private static String DELETE_COLUMN_LINK_XPATH_LOCATOR = "//*[@id=\"table\"]/thead/tr/th[7]";
	private static String FIRST_ELEMENT_IN_COLUMN_DELETE_XPATH_LOCATOR = "//*[@id=\"table\"]/tbody/tr[1]/td[7]/a";
	private static String PAGE_COUNT_LINK_XPATH_LOCATOR = "//*[@id=\"list\"]/h4[2]";
	

	public AddUserPage selectCreateNewUser() {
		browser.findElementByXpath(CREATE_NEW_USER_LINK_XPATH_LOCATOR).click();
		return new AddUserPage(browser.getDriver());
	}

	/**
	 * Methods to find header's text
	 */
	public String getHeaderLinkText() {
		return browser.findElementByTagName(HEADER_LINK_TAGNAME).getText();
	}

	public String getUnderHeaderLinkText() {
		return browser.findElementByTagName(UNDER_HEADER_LINK_TAGNAME)
				.getText();
	}

	/**
	 * methods to find/click/getText of tabs "User Info" and "Administration"
	 */
	public String getUserInfoTabText() {
		return browser.findElementByXpath(USERINFO_TAB_XPATH_LOCATOR).getText();
	}

	public void findUserInfoTab() {
		browser.findElementByXpath(USERINFO_TAB_XPATH_LOCATOR);
	}

	public void findUserInfoTabAndClick() {
		browser.findElementByXpath(USERINFO_TAB_XPATH_LOCATOR).click();
	}

	public String getAdministrationTabText() {
		return browser.findElementByXpath(USERINFO_TAB_XPATH_LOCATOR).getText();
	}

	public void findAdministrationTab() {
		browser.findElementByXpath(ADMINISTRATION_TAB_XPATH_LOCATOR);
	}

	public void findAdministrationTAbAndClick() {
		browser.findElementByXpath(ADMINISTRATION_TAB_XPATH_LOCATOR).click();
	}

	/**
	 * methods to find,click and getText of Create new user link
	 */
	public String getCreateNewUserLinkText() {
		return browser.findElementByXpath(CREATE_NEW_USER_LINK_XPATH_LOCATOR)
				.getText();
	}

	public void findCreateNewUserLink() {
		browser.findElementByXpath(CREATE_NEW_USER_LINK_XPATH_LOCATOR);
	}

	public void findCreateNewUserLinkAndClick() {
		browser.findElementByXpath(CREATE_NEW_USER_LINK_XPATH_LOCATOR).click();
	}

	/**
	 * methods to getText from foundUsers link and to get number of found users
	 */
	public String getFoundUsersLinkText() {
		return browser.findElementByXpath(FOUND_USERS_LINK_XPATH_LOCATOR)
				.getText();
	}

	public String getNumberOfFoundUsers() {
		return browser.findElementById(NUMBER_OF_FOUND_USERS_ID_LOCATOR)
				.getText();
	}

	/**
	 * methods to test "search by" field
	 */
	public String getFieldFilterLinkText() {
		return browser.findElementByXpath(FIELD_FILTER_LINK_XPATH_LOCATOR)
				.getText();
	}

	public void getColumnFilterText() {
		browser.findElementById(COLUMN_FILTER_ID_LOCATOR).getText();
	}

	public void findColumnFilter() {
		browser.findElementById(COLUMN_FILTER_ID_LOCATOR);
	}

	public void findColumnFilterAndClick() {
		browser.findElementById(COLUMN_FILTER_ID_LOCATOR).click();
	}

	public void getConditionFiltertext() {
		browser.findElementById(CONDITION_FILTER_ID_LOCATOR).getText();
	}

	public void findConditionFilter() {
		browser.findElementById(CONDITION_FILTER_ID_LOCATOR);
	}

	public void findConditionFilterAndClick() {
		browser.findElementById(CONDITION_FILTER_ID_LOCATOR).click();
	}

	public void findSearchField() {
		browser.findElementById(SEARCH_FIELD_ID_LOCATOR);
	}

	public void findSearchFieldAndSendKey(String testKey) {
		browser.findElementById(SEARCH_FIELD_ID_LOCATOR).clear();
		browser.findElementById(SEARCH_FIELD_ID_LOCATOR).sendKeys(testKey);
	}

	public void getSearchButtonText() {
		browser.findElementByXpath(SEARCH_BUTTON_XPATH_LOCATOR).getText();
	}

	public void findSearchButton() {
		browser.findElementByXpath(SEARCH_BUTTON_XPATH_LOCATOR);
	}

	public void findSearchButtonAndClick() {
		browser.findElementByXpath(SEARCH_BUTTON_XPATH_LOCATOR).click();
	}

	/**
	 * methods to test "Show .. items" link
	 */
	public String getShowItemsLinkText() {
		return browser.findElementByXpath(SHOW_ITEMS_XPATH_LOCATOR).getText();
	}

	public void findShowItemsLink() {
		browser.findElementByXpath(SHOW_ITEMS_XPATH_LOCATOR);
	}

	public void findShowItemsLinkAndClick() {
		browser.findElementByXpath(SHOW_ITEMS_XPATH_LOCATOR).click();
	}

	/**
	 * methods to test names of the columns
	 */
	// First name Column
	public String getFirstNameColumnText() {
		return browser.findElementByXpath(FIRST_NAME_COLUMN_LINK_XPATH_LOCATOR)
				.getText();
	}

	public void findFirstNameColumn() {
		browser.findElementByXpath(FIRST_NAME_COLUMN_LINK_XPATH_LOCATOR);
	}

	public void findFirstNameCOlumnAndClick() {
		browser.findElementByXpath(FIRST_NAME_COLUMN_LINK_XPATH_LOCATOR)
				.click();
	}
	
	public String getFirstElementInColumnFirstName(){
		return browser.findElementByXpath(FIRST_ELEMENT_INCOLUMN_FIRST_NAME_XPATH_LOCATOR).getText();
	}
	
	public void findFirstElementInColumnFirstName(){
		browser.findElementByXpath(FIRST_ELEMENT_INCOLUMN_FIRST_NAME_XPATH_LOCATOR);
	}
	
	public Boolean isFirstElementIncolumnFirstName(){
		WebElement element = browser.findElementByXpath(FIRST_ELEMENT_INCOLUMN_FIRST_NAME_XPATH_LOCATOR);
		return element != null ? true : false;
	}
	// Last name Column
	public String getLastNameColumnText() {
		return browser.findElementByXpath(LAST_NAME_COLUMN_XPATH_LOCATOR)
				.getText();
	}

	public void findLastNameColumn() {
		browser.findElementByXpath(LAST_NAME_COLUMN_XPATH_LOCATOR);
	}

	public void findLastNameColumnAndClick() {
		browser.findElementByXpath(LAST_NAME_COLUMN_XPATH_LOCATOR).click();
	}
	// Login column
	public String getLoginColumnText() {
		return browser.findElementByXpath(LOGIN_COLUMN_LINK_XPATH_LOCATOR)
				.getText();
	}

	public void findLoginColumn() {
		browser.findElementByXpath(LOGIN_COLUMN_LINK_XPATH_LOCATOR);
	}

	public void findLoginColumnAndClick() {
		browser.findElementByXpath(LOGIN_COLUMN_LINK_XPATH_LOCATOR).click();
	}
	// Role Column
	public String getRoleColumnText() {
		return browser.findElementByXpath(ROLE_COLUMN_LINK_XPATH_LOCATOR)
				.getText();
	}

	public void findRoleColimn() {
		browser.findElementByXpath(ROLE_COLUMN_LINK_XPATH_LOCATOR);
	}

	public void finRoleColumnAndClick() {
		browser.findElementByXpath(ROLE_COLUMN_LINK_XPATH_LOCATOR).click();
	}
	// Region
	public String getRegionColumnText() {
		return browser.findElementByXpath(REGION_COLUMN_LINK_XPATH_LOCATOR)
				.getText();
	}

	public void findRegionColumn() {
		browser.findElementByXpath(REGION_COLUMN_LINK_XPATH_LOCATOR);
	}

	public void findRegionColumnAndClick() {
		browser.findElementByXpath(REGION_COLUMN_LINK_XPATH_LOCATOR).click();
	}
	// Edit
	public String getEditColumnText() {
		return browser.findElementByXpath(EDIT_COLUMN_LINK_XPATH_LOCATOR)
				.getText();
	}
	// Delete
	public String getDeleteColumnText() {
		return browser.findElementByXpath(DELETE_COLUMN_LINK_XPATH_LOCATOR)
				.getText();
	}
	
	public void findFirstElementInColumnDeleteAndClick(){
		browser.findElementByXpath(FIRST_ELEMENT_IN_COLUMN_DELETE_XPATH_LOCATOR).click();
		browser.alertAccept();
	}
	
	/**
	 * methods to test page count link
	 */
	public String getPageCountText() {
		return browser.findElementByXpath(PAGE_COUNT_LINK_XPATH_LOCATOR)
				.getText();
	}
}
