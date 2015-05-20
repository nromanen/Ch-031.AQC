package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class UsersPage {

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

  private WebDriver driver;

  public UsersPage(WebDriver driver) {
    this.driver = driver;
  }

  public AddUserPage gotoAddUser() {
    driver.findElement(By.linkText("Create New User")).click();

    return new AddUserPage(driver);
  }

  public int getFoundUsers() {
    WebElement e = driver.findElement(By.id("usersFound"));
    int usersFound = Integer.parseInt(e.getText());
    return usersFound;
  }

  public int countUsers() {
    WebElement e = driver.findElement(By.id("pageCount"));
    int pageCount = Integer.parseInt(e.getText());

    driver.findElement(By.id("last")).click();

    // get count of rows
    int lastPageCount = driver.findElements(By.xpath("//table[@id='table']/tbody/tr")).size();

    return (pageCount - 1) * USERS_PER_PAGE + lastPageCount;
  }

  public ArrayList<String> getFirst() {
    ArrayList<String> res = new ArrayList<String>();
    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(0).getText());
    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(1).getText());
    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(2).getText());
    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(3).getText());
    res.add(driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(4).getText());
    return res;
  }

  public int filter() {
    /*String firstname = driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(0).getText();
    String lastname = driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(1).getText();
    String login = driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(2).getText();
    String role = driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(3).getText();
    String region = driver.findElements(By.xpath("//table[@id='table']/tbody/tr/td")).get(4).getText();

    System.out.println(firstname);
    System.out.println(lastname);
    System.out.println(login);
    System.out.println(role);
    System.out.println(region);*/

    //new Select(driver.findElement(By.id("field"))).selectByVisibleText("First Name");


    //driver.findElement(By.id("searchField")).clear();
    //driver.findElement(By.id("searchField")).sendKeys("someemail@example.com");
    return 0;
  }




}
