package pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.auth.UserInfoPage;



public class UsersPage extends BasePage{

  private final static int USERS_PER_PAGE = 5;
  private final static String[] COLUMNS = new String[]{
          "All Columns",
          "First Name",
          "Last Name",
          "Login",
          "Role",
          "Region"};
  private final static String[] MATCHES = new String[]{
          "equals",
          "not equals to",
          "starts with",
          "contains",
          "does not contain"};

  public UsersPage(WebDriver driver) {
    super(driver);
  }

  public int getFoundUsers() {
    WebElement e = browser.findElementById("usersFound");
    int usersFound = Integer.parseInt(e.getText());
    return usersFound;
  }

  public int countUsers() {
    WebElement e = browser.findElementById("pageCount");
    int pageCount = Integer.parseInt(e.getText());
    browser.findElementById("last").click();
    // get count of rows
    int lastPageCount = driver.findElements(By.xpath("//table[@id='table']/tbody/tr")).size();
    return (pageCount - 1) * USERS_PER_PAGE + lastPageCount;
  }

  public void setFilter(String column, String match, String value) {
    browser.selectByVisibleText(browser.findElementById("field"), column);
    browser.selectByVisibleText(browser.findElementById("condition"), match);
    browser.findElementById("searchField").sendKeys(value);
    browser.findElementByName("search").click();
  }

    public UsersPage goHere() {
        UserInfoPage user_info_page = gotoRoot();
        UsersPage users_page = user_info_page.gotoUsers();
        return users_page;
    }

    public String getLogoText() {
        return browser.findElementByXpath("//div[@id='logo']/h1").getText();
    }



}
