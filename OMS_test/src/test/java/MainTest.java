import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import pages.UserinfoPage;
import pages.UsersPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MainTest {
  private WebDriver driver;

  private static final String BASEURL = "http://localhost:8080/OMS/";
  private static final int TIMEOUT = 30;


  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    driver.get(BASEURL);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

  @Ignore
  @Test
  public void testLogin() throws Exception {
    LoginPage lp = new LoginPage(driver);
    UserinfoPage uip = lp.loginSuccess();
    String curl = driver.getCurrentUrl();

    assertEquals(curl, BASEURL + "userInfo.htm");
  }

  @Test
  public void testUserCount() throws Exception {
    LoginPage lp = new LoginPage(driver);
    UserinfoPage uip = lp.loginSuccess();

    UsersPage up = uip.gotoUsers();



    assertEquals(up.getFoundUsers(), up.countUsers());
  }



}
