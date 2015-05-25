package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tools.SeleniumWrapper;

public class LoginPage {

  private WebDriver driver;
  SeleniumWrapper sw;

  private final static String admin_name = "admin";
  private final static String admin_password = "pass";


  public LoginPage(WebDriver driver) {
    this.driver = driver;
    sw = new SeleniumWrapper(driver);
  }

  public UserinfoPage loginSuccess() {

    sw.findByNameAndSendKeys("j_username", admin_name);
    sw.findByNameAndSendKeys("j_password", admin_password);
    sw.findByNameAndClick("submit");

    return new UserinfoPage(driver);
  }

  public LoginPage loginFailed() {
    return new LoginPage(driver);
  }

}
