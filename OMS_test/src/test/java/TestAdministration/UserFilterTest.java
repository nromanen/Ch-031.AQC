package TestAdministration;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.administration.UsersPage;
import pages.auth.UserInfoPage;
import pages.auth.LoginPage;
import tools.DBUnitConfig;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class UserFilterTest extends DBUnitConfig {
    private String column;
    private String match;
    private String value;
    private int expected;

    private static final String BASEURL = "http://localhost:8080/OMS/";
    private static final int TIMEOUT = 30;

    private static final String ADMIN_NAME = "admin";
    private static final String ADMIN_PASS = "pass";

    WebDriver driver;

    @Before
    public void initialize() throws Exception {
        // DBUnit
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();

        // Selenium
        System.setProperty("webdriver.chrome.driver", "/home/lumberjack85/Desktop/oms_git/Ch-031.AQC/OMS_test/chromedriver");
        driver = new ChromeDriver();

    }

    public UserFilterTest(String column, String match, String value, int expected) {
        // DBUnit constructor
        //super("TestAdministration.UserFilterTest");
        super("UserFilterTest");

        this.column = column;
        this.match = match;
        this.value = value;
        this.expected = expected;
    }


    @Parameterized.Parameters
    public static Collection Filters() {
        return Arrays.asList(new Object[][] {
                { "All Columns", "equals", "", 5 },
                
                { "All Columns", "equals", "Alice", 1 },
                { "All Columns", "not equals to", "Alice", 4 },
                { "All Columns", "starts with", "Ev", 2 },
                { "All Columns", "contains", "A", 3 },
                { "All Columns", "does not contain", "A", 2 },

                { "First Name", "equals", "Alice", 1 },
                { "First Name", "not equals to", "Alice", 4 },
                { "First Name", "starts with", "A", 2 },
                { "First Name", "contains", "A", 2 },
                { "First Name", "does not contain", "A", 3 },

                { "Last Name", "equals", "E.", 2 },
                { "Last Name", "not equals to", "E.", 3 },
                { "Last Name", "starts with", "A", 2 },
                { "Last Name", "contains", "A", 2 },
                { "Last Name", "does not contain", "A", 3 },

                { "Login", "equals", "admin", 1 },
                { "Login", "not equals to", "admin", 4 },
                { "Login", "starts with", "User", 4 },
                { "Login", "contains", "s", 4 },
                { "Login", "does not contain", "s", 1 },

                { "Role", "equals", "Administrator", 2 },
                { "Role", "not equals to", "Administrator", 3 },
                { "Role", "starts with", "A", 2 },
                { "Role", "contains", "or", 4 },
                { "Role", "does not contain", "or", 1 },

                { "Region", "equals", "North", 2 },
                { "Region", "not equals to", "North", 3 },
                { "Region", "starts with", "N", 2 },
                { "Region", "contains", "o", 4 },
                { "Region", "does not contain", "o", 1 },

        });
    }


    @Test
    public void testUserFilter() {
        // login and goto page we need
        LoginPage lp = new LoginPage(driver);
        UserInfoPage uip = lp.login(ADMIN_NAME,ADMIN_PASS);
        UsersPage up = uip.gotoUsers();

        up.setFilter(this.column, this.match, this.value);

        int actual = up.getFoundUsers();
        assertEquals(expected, actual);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}