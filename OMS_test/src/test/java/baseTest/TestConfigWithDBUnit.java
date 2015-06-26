package baseTest;


import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;

import tools.Browser;
import tools.DBUnitConfig;

import java.util.concurrent.TimeUnit;

public class TestConfigWithDBUnit extends DBUnitConfig{
	protected static final String BASEURL = "http://localhost:8083/OMS/";
    protected static final int TIMEOUT = 30;
    protected static  Browser browser = new Browser(new FirefoxDriver());

    public TestConfigWithDBUnit(String name) {
    	super(name);
	}

    public static void initialize() throws Exception {
        browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS); 
        browser.getDriver().get(BASEURL);//move to Browser        
    }
    
    public static void quit(){
    	browser.getDriver().quit();  
    }
    
    public void initializationDataBase(String ...  dataFile){
    	if(dataFile.length != 0){
	    	IDataSet[] data = new IDataSet[dataFile.length];
	    	for (int i=0; i <dataFile.length; i++){
	    	try {
	    		data[i] = getDataFromFile(dataFile[i]);
	    		} 
	    	catch (DataSetException e) {
	    		e.printStackTrace();
				 }
	    	}
	    	beforeData = data;// new IDataSet[] { data };
	    	try {
	    		super.setUp();
	    	} 
	    	catch (Exception e) {
	    		e.printStackTrace();
			}
    			
    	}
    	else {System.out.println("!@#$%^&*(");}
    }
    
    @After 
    public  void tearDown() {
    	try {
			super.tearDown();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

    }
    
}
