package baseTest;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import tools.Browser;
import tools.DBUnitConfig;
import java.util.concurrent.TimeUnit;

public class TestConfigWithDBUnit extends DBUnitConfig{
	protected static final String BASEURL = "http://localhost:8083/OMS/";
    protected static final int TIMEOUT = 30;
    protected  Browser browser = new Browser(new FirefoxDriver());

    public TestConfigWithDBUnit(String name) {
    	super(name);
	}

    @Before
    public void initialize() throws Exception {
        browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS); 
        browser.getDriver().get(BASEURL);//move to Browser        
    }

    @After public  void tearDown() {
    	try {
			super.tearDown();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
       browser.getDriver().quit();    	
    }
    
}
