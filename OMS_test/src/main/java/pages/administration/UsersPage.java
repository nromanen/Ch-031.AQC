package pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.administration.AddUserPage;
import tools.Browser;
import tools.SeleniumWrapper;


public class UsersPage {

  private WebDriver driver;
  private Browser browser;

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
    this.driver = driver;
    this.browser = new Browser(driver);
  }

  public AddUserPage gotoAddUser() {
    driver.findElement(By.linkText("Create New User")).click();

    return new AddUserPage(driver);
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

//  public ArrayList<String> getFirst() {
//    ArrayList<String> res = new ArrayList<String>();
//    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(0).getText());
//    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(1).getText());
//    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(2).getText());
//    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(3).getText());
//    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(4).getText());
//    return res;
//  }

  public void setFilter(String column, String match, String value) {
    browser.selectByVisibleText(browser.findElementById("field"), column);
    browser.selectByVisibleText(browser.findElementById("condition"), match);
    browser.findElementById("searchField").sendKeys(value);
    browser.findElementByName("search").click();
  }




}
