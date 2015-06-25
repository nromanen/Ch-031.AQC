package tools;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * It's necessary for DBUnit test to have beforeData
 * If you dont need one use emptyset
 */
public class BaseDBTest extends DBUnitConfig {

    private static final String BASEURL = "http://localhost:8080/OMS/";
    private static final int TIMEOUT = 30;

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        // DBUnit
        super.setUp();
        // Selenium
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.get(BASEURL);
    }

    public BaseDBTest(String name) throws Exception {
        // DBUnit constructor
        super(name);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
