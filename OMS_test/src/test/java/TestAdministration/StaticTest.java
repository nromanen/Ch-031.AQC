package TestAdministration;

import org.junit.Test;
import pages.administration.UsersPage;
import tools.BaseTest;
import static org.junit.Assert.assertEquals;

public class StaticTest extends BaseTest {
  private static final String ADMIN_NAME = "admin";
  private static final String ADMIN_PASS = "pass";


  @Test
  public void TestLogo() {
    // login and goto page we need
    UsersPage up = new UsersPage(driver);
    up.goHere();

    assertEquals("Ordering Management System.", up.getLogoText());
  }
}