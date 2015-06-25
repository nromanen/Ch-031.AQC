package baseTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;
import tools.Browser;
import java.util.concurrent.TimeUnit;

public class TestConfig {

    private static final String BASEURL = "http://localhost:8083/OMS/";
    private static final int TIMEOUT = 30;

    protected static Browser browser = new Browser(new FirefoxDriver());//changed to static 22.06.2015

    @BeforeClass
    public static void initialize() throws Exception {
        browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        browser.getDriver().get(BASEURL);//move to Browser        
    }

    @AfterClass 
    public static void tearDown() throws Exception {
        browser.getDriver().quit();
    }
    
}
