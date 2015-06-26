package TestSupervisor;

import junit.extensions.TestSetup;
import junit.framework.TestSuite;


import org.junit.Before;
import org.junit.Test;

import baseTest.TestConfigWithDBUnit;
import pages.auth.LoginPage;
import pages.ordering.ItemManagementPage;



/**
 * This test case is designed for testing the Delete Product functionality.
 * @author Olya
 */
public class SupervisorTest extends TestConfigWithDBUnit {

    public SupervisorTest(String name) {
		super(name);

	}
    

    
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME_DELETE = "lemon";
    
    @Before
	 public  void setUp() throws Exception  {
		 super.initializationDataBase("products.xml", "testOrdersDataForMerchTemp.xml");
		 new LoginPage(browser.getDriver()).login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);

	    }
    ItemManagementPage itemManagementPage = new ItemManagementPage(browser.getDriver());
	
    public static TestSetup suite( ) { 
		 TestSetup setup = new TestSetup(new TestSuite(SupervisorTest.class)) { 
			 protected void setUp( ) throws Exception {
				 System.out.println("BLABLABLABLABLABLABLABLABLABL");
				 initialize();
				 }
			 protected void tearDown( ) throws Exception { 
				 quit();
				 } 
		 }; return setup; 
	}

    @Test
    public void testDeleteProduct() throws Exception {
  
        itemManagementPage.clickDeleteLinkOnProductAndDismiss(PRODUCT_NAME_DELETE);
        assertNotNull(itemManagementPage.findProductByNameInTable(PRODUCT_NAME_DELETE));
        itemManagementPage.clickDeleteLinkOnProductAndAccept(PRODUCT_NAME_DELETE);
        assertNull(itemManagementPage.findProductByNameInTable(PRODUCT_NAME_DELETE));
    }


}