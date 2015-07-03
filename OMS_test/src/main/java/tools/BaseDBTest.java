package tools;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

	protected static final String BASEURL = "http://localhost:8083/OMS/";
	protected static final int TIMEOUT = 30;
	protected static Browser browser = new Browser(new FirefoxDriver());
	
	@BeforeClass
	public static void initialize(){
		browser.getDriver().manage().timeouts()
				.implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		browser.getDriver().get(BASEURL);
	}
	
    @Before
    public void setUp(){
        screenShootRule.setDriver(browser.getDriver());
    }
    
    public void initializationDataBase(String... dataFile) {
		if (dataFile.length != 0) {
			IDataSet[] data = new IDataSet[dataFile.length];
			for (int i = 0; i < dataFile.length; i++) {
				try {
					data[i] = getDataFromFile(dataFile[i]);
				} catch (DataSetException e) {
					e.printStackTrace();
				}
			}
			beforeData = data;
			try {
				super.setUp();
			} catch (Exception e) {
				System.out.println("OOOOOO");
				e.printStackTrace();
			}

		} else {
			System.out.println("!@#$%^&*(");
		}
	}

    @Rule
    public ScreenShotUtils screenShootRule = new ScreenShotUtils() {
        @Override
        protected void finished(Description description) {
            super.finished(description);
        }
    };
    
    @AfterClass
    public static void quit(){
    	browser.getDriver().quit();
    }

}
