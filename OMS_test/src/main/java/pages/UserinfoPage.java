package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tools.SeleniumWrapper;

public class UserinfoPage {

  private WebDriver driver;
  SeleniumWrapper sw;

  public UserinfoPage(WebDriver driver) {
    this.driver = driver;
    this.sw = new SeleniumWrapper(driver);
  }

  public UsersPage gotoUsers() {
    sw.findByLinkTextAndClick("Administration");

    return new UsersPage(driver);
  }

}
