package pages.auth;

import pages.administration.UsersPage;
import pages.ordering.ItemManagementPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import tools.Browser;

public class UserInfoPage {
    private WebDriver driver;
    private Browser browser;
    private static final String itemManagementTabLinkTextLocator = "Item Management";
    private static final String USERS_LOCATOR = "Administration";


    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
        browser = new Browser(driver);
    }

    public ItemManagementPage selectItemManagementTab() {
        browser.findElementByLinkText(itemManagementTabLinkTextLocator).click();
        return PageFactory.initElements(driver, ItemManagementPage.class);
    }

    public UsersPage gotoUsers() {
        browser.findElementByLinkText(USERS_LOCATOR).click();

        return new UsersPage(driver);
    }


}