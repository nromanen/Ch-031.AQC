package tools;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

/**
 * It's necessary for DBUnit test to have beforeData
 * If you dont need one use emptyset
 */
public class BaseDBTest extends DBUnitConfig {

    private static final String BASEURL = "http://localhost:8080/OMS/";
    private static final int TIMEOUT = 30;

    protected WebDriver driver;
    protected BasePage basePage;

    public WebDriver getDriver() {
        return  driver;
    }

    @Before
    public void setUp() throws Exception {
        // DBUnit
        super.setUp();

        // Selenium
      // driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
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
            driver.quit();
        }
    };

}
