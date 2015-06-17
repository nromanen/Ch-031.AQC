package ordering;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import pages.ordering.CustomerAddProductsToOrderPage;
import pages.ordering.CustomerCreateOrderPage;
import pages.ordering.CustomerOrderingPage;
import tools.Browser;
import tools.DBUnitConfig;

public class TestSaveNewOrder extends DBUnitConfig {

	private static WebDriver driver;
	private static Browser browser;
	private static final String HOME_URL = "http://localhost:8080/OMS/";
	private static final int TIMEOUT = 2;

	private static final String USER_NAME_FOR_CUSTOMER = "customer1";
	private static final String PASSWORD_FOR_CUSTOMER = "qwerty";

	private static final String WRONG_USER_NAME_FOR_CUSTOMER = "lohghgbh";
	private static final String WRONG_PASSWORD_FOR_CUSTOMER = "qwerty4544";
		
	public TestSaveNewOrder(String name) {
		super(name);
	}

	@Before
	public  void setUp() throws Exception {
		
		IDataSet productData = getDataFromFile("products.xml");
		beforeData = new IDataSet[] {productData};
		super.setUp();
			  
		driver = new FirefoxDriver(); 
		browser = new Browser(driver);
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.get(HOME_URL);			
	}

   @After
   public void tearDown ()  {
    driver.quit(); 
   }
	
	@Test
	public void testCustomerLogin() throws Exception {
		
		LoginPage login = new LoginPage(driver); 
		UserInfoPage userInfoPage = login.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
		String elementOnInfoPage = userInfoPage.getBrowser().findElementByTagName("legend").getText();
		assertEquals(elementOnInfoPage, "User Info"); 		
	}
	
	@Test  // will not pass because error messages in SRS and OMS don't match
	public void testWrongLogin() {
		
		LoginPage login = new LoginPage(driver); 
		login.login(WRONG_USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
	    //assertEquals("Your login attempt was not successful, try again.\n\nReason: Bad credentials.", browser.findElementByCssSelector("font").getText());
		assertEquals("Such user does not exist in the system – please try again.", browser.findElementByCssSelector("font").getText());
	}
	
	@Test  // will not pass because error messages in SRS and OMS don't match
	public void testWrongPassword() {
		
		LoginPage login = new LoginPage(driver); 
		login.login(USER_NAME_FOR_CUSTOMER, WRONG_PASSWORD_FOR_CUSTOMER);
		assertEquals("Password is incorrect – please try again.", browser.findElementByCssSelector("font").getText());		
	}
	
	@Test
	 // will not pass because error messages in SRS and OMS don't match
	public void testEmptyLogin() {

		LoginPage login = new LoginPage(driver);
		login.login("", PASSWORD_FOR_CUSTOMER);
		assertEquals("Such user does not exist in the system – please try again.", browser.findElementByCssSelector("font").getText());
	}	

	@Test  // will not pass because error messages in SRS and OMS don't match
	public void testEmptyPassword() {
			
		LoginPage login = new LoginPage(driver); 
		login.login(USER_NAME_FOR_CUSTOMER, "");
		assertEquals("Password is incorrect – please try again.", browser.findElementByCssSelector("font").getText());		
	}

	@Test
	public void testSwitchToOrderingPage(){
					
		LoginPage login = new LoginPage(driver); 	
		UserInfoPage userInfo = login.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
		CustomerOrderingPage ordering = userInfo.switchToOrderingPage();
								
		List <String> expectedValues = new ArrayList<String>();
		expectedValues.add("Order Name");
		expectedValues.add("Total price");
		expectedValues.add("Max Discount");
		expectedValues.add("Delivery date");
		expectedValues.add("Status");
		expectedValues.add("Assignee");
		expectedValues.add("Edit");
		expectedValues.add("Delete");
				
		List <String> actualValues =  ordering.getHeadersFromTableWithOrders(); 
	    
	    assertEquals(expectedValues.size(), actualValues.size());
		assertEquals(expectedValues, actualValues);
	}
	
	@Test
	public void testSwitchToCreatingNewOrderPage(){
		
		LoginPage login = new LoginPage(driver); 	
		UserInfoPage userInfo = login.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);		
		CustomerOrderingPage ordering = userInfo.switchToOrderingPage();
		
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		String elementOnCreateNewOrderPage = createNewOrderPage.getBrowser().findElementByTagName("legend").getText(); 

		assertEquals(elementOnCreateNewOrderPage, "Item Selection"); 	
	}
   
	@Test
	public void testClickAddItemButton(){
		
		LoginPage login = new LoginPage(driver); 	
		UserInfoPage userInfo = login.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);		
		CustomerOrderingPage ordering = userInfo.switchToOrderingPage();
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("Item Name");
		expectedValues.add("Item Description");
		expectedValues.add("Add");
	
		List <String> actualValues =  addProductsPage.getHeadersFromTableWithProducts();
	   
	    assertEquals(expectedValues.size(), actualValues.size());
		assertEquals(expectedValues, actualValues);
	}

	@Test
	public void testSelectProduct() throws Exception{ 

		LoginPage login = new LoginPage(driver); 	
		UserInfoPage userInfo = login.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);		
		CustomerOrderingPage ordering = userInfo.switchToOrderingPage();
		
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		
		addProductsPage.selectProduct();
	
		assertEquals("table", browser.findElementByXpath("//form[@id = 'doneForm']/table/tbody/tr[1]/td[2]").getText());
		assertEquals("800.0", browser.findElementByXpath("//form[@id = 'doneForm']/table/tbody/tr[3]/td[2]").getText());
	}
	
}
