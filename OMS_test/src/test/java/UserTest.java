import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import pages.UserinfoPage;
import pages.UsersPage;
import service.UserService;
import tools.DBUnitConfig;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.concurrent.TimeUnit;

public class UserTest extends DBUnitConfig {

    private WebDriver driver;

    private static final String BASEURL = "http://localhost:8080/OMS/";
    private static final int TIMEOUT = 30;
 
    //private UserService service = new UserService();
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();
 
    @Before
    public void setUp() throws Exception {
        // DBUnit
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(
                     Thread.currentThread().getContextClassLoader()
                     .getResourceAsStream("data.xml"));
 
        tester.setDataSet(beforeData);
        tester.onSetup();

        // Selenium
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.get(BASEURL);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
 
    public UserTest(String name) {
        super(name);
    }

    @Test
    public void test1() throws Exception {

        beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("data1.xml"));
        tester.setDataSet(beforeData);

        LoginPage lp = new LoginPage(driver);
        UserinfoPage uip = lp.loginSuccess();
        UsersPage up = uip.gotoUsers();

        Assert.assertEquals(up.getFoundUsers(), 3);
    }

}