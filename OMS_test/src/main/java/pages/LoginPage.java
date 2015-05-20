package pages;

import org.openqa.selenium.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LoginPage {

  private WebDriver driver;

  private final static String admin_name = "admin";
  private final static String admin_password = "pass";


  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  public UserinfoPage loginSuccess() {
    driver.findElement(By.name("j_username")).clear();
    driver.findElement(By.name("j_username")).sendKeys(admin_name);
    driver.findElement(By.name("j_password")).clear();
    driver.findElement(By.name("j_password")).sendKeys(admin_password);
    driver.findElement(By.name("submit")).click();

    return new UserinfoPage(driver);
  }

  public LoginPage loginFailed() {
    return new LoginPage(driver);
  }

}
