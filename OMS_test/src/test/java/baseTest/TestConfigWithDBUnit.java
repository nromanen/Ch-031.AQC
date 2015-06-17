package baseTest;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import tools.Browser;
import tools.DBUnitConfig;
import java.util.concurrent.TimeUnit;

public class TestConfigWithDBUnit extends DBUnitConfig{

    public TestConfigWithDBUnit(String name) {
		super(name);		
	}

	private static final String BASEURL = "http://localhost:8083/OMS/";
    private static final int TIMEOUT = 30;

    protected Browser browser = new Browser(new FirefoxDriver());

    @Before
    public void initialize() throws Exception {
        browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        browser.getDriver().get(BASEURL);//move to Browser        
    }

    @After
    public void tearDown() throws Exception {
        browser.getDriver().quit();
    }
    
}
