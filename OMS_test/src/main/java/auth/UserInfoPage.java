package auth;

import ordering.ItemManagementPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tools.Browser;

public class UserInfoPage {
    private WebDriver driver;
    private Browser browser;
    private static String itemManagementTabLinkTextLocator = "Item Management";


    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public ItemManagementPage selectItemManagementTab() {
        browser.findElementByLinkText(itemManagementTabLinkTextLocator).click();
        return PageFactory.initElements(driver, ItemManagementPage.class);
    }


}