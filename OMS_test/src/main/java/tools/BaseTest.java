package tools;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final String BASEURL = "http://localhost:8080/OMS/";
    private static final int TIMEOUT = 30;
    protected WebDriver driver;
    protected EntityManager em = Persistence.createEntityManagerFactory("persistence").createEntityManager();

    @Before
    public void setUp() throws Exception {
        // Selenium
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.get(BASEURL);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
