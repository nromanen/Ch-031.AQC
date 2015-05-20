package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddUserPage {

  private WebDriver driver;

  private final static String LOGIN = "user";
  private final static String PASSWORD = "pass";
  private final static String FIRSTNAME = "Vasia";
  private final static String LASTNAME = "Pupkin";
  private final static String EMAIL = "fake@mail.com";

  public AddUserPage(WebDriver driver) {
    this.driver = driver;
  }

  public UsersPage AddUser() {
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("user1");
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys("name");
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys("lastname");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.id("confirmPassword")).clear();
    driver.findElement(By.id("confirmPassword")).sendKeys("pass");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("someemail@example.com");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

    return new UsersPage(driver);
  }

}
