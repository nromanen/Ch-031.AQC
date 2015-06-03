package pages.auth;

import pages.administration.UsersPage;
import pages.ordering.CustomerOrderingPage;
import pages.ordering.ItemManagementPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import tools.Browser;

public class UserInfoPage {
    private WebDriver driver;
    private Browser browser;
    private static final String ITEM_MANAGEMENT_TAB_LINK_TEXT_LOCATOR = "Item Management";
    private static final String USERS_LOCATOR = "Administration";
    private static final String LINK_FOR_ORDERING = "Ordering";  

    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
        browser = new Browser(driver);
    }
    
    public WebDriver getDriver() {
		return this.driver;
	}

	public Browser getBrowser() {
		return this.browser;
	}

    public ItemManagementPage selectItemManagementTab() {
        browser.findElementByLinkText(ITEM_MANAGEMENT_TAB_LINK_TEXT_LOCATOR).click();
        return PageFactory.initElements(driver, ItemManagementPage.class);
    }
    
    public CustomerOrderingPage switchToOrderingPage() {
		
		Browser browser = new Browser(driver); 
		browser.findElementByLinkText(LINK_FOR_ORDERING).click();
		return new CustomerOrderingPage(driver);
	}

    public UsersPage gotoUsers() {
        browser.findElementByLinkText(USERS_LOCATOR).click();
        return new UsersPage(driver);
    }


}