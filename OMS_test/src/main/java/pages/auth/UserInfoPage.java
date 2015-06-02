package pages.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import pages.administration.UsersPage;
import pages.ordering.ItemManagementPage;

public class UserInfoPage extends BasePage{

    private static final String itemManagementTabLinkTextLocator = "Item Management";
    private static final String USERS_LOCATOR = "Administration";


    public UserInfoPage(WebDriver driver) {
        super(driver);
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