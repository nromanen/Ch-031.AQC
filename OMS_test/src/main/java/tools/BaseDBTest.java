package tools;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseDBTest extends DBUnitConfig {

    private static final String BASEURL = "http://localhost:8080/OMS/";
    private static final int TIMEOUT = 30;

    protected WebDriver driver;

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
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.get(BASEURL);
    }

    public BaseDBTest(String name) throws Exception {
        // DBUnit constructor
        //super("UserFilterTest");
        super(name);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}