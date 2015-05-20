package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserinfoPage {

  private WebDriver driver;

  public UserinfoPage(WebDriver driver) {
    this.driver = driver;
  }

  public UsersPage gotoUsers() {
    driver.findElement(By.linkText("Administration")).click();

    return new UsersPage(driver);
  }

}
