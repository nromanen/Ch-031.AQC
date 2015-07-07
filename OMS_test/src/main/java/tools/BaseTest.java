package tools;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePage;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final String BASEURL = "http://localhost:8080/OMS/";
    private static final int TIMEOUT = 30;
    protected static WebDriver driver = new FirefoxDriver();
    protected BasePage basePage;
    protected static EntityManager em = Persistence.createEntityManagerFactory("persistence").createEntityManager();

    @Before
    public void setUp() throws Exception {
        // Selenium
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.get(BASEURL);
        basePage = new BasePage(driver);
        screenShootRule.setDriver(driver);

    }

    @Rule
    public ScreenShotUtils screenShootRule = new ScreenShotUtils() {
        @Override
        protected void finished(Description description) {
            super.finished(description);
            basePage.logout();
            //driver.quit();
        }
    };
}
