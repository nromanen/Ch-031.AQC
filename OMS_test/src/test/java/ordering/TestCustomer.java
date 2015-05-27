package softserve.mvn_oms;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import tools.Browser;

public class TestCustomer {
	
	private static WebDriver driver;
	private static Browser browser;
	private static final String HOME_URL = "http://localhost:8080/OMS/";
	private static final int TIMEOUT = 2;
			
		private static final String USER_NAME_FOR_CUSTOMER = "customer1"; 
		private static final String PASSWORD_FOR_CUSTOMER = "qwerty";
		
		private static final String WRONG_USER_NAME_FOR_CUSTOMER = "lohghgbh"; 
		private static final String WRONG_PASSWORD_FOR_CUSTOMER = "qwerty4544";

	@Before
	public  void setUp() throws Exception {
		driver = new FirefoxDriver(); 
		browser = new Browser(driver);
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.get(HOME_URL);
	}


		@After
	public void tearDown() throws Exception {
		driver.close();  
	}

	//@Ignore
	@Test
	public void testCustomerLogin() {
		
		Login login = new Login(driver); 
		CustomerUserInfoPage result = login.loginWithCustomerAccount(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
		String URL = result.getBrowser().getCurrentUrl(); 
		assertEquals(URL, HOME_URL + "userInfo.htm"); 
		String elementOnInfoPage = result.getBrowser().findElementByTagName("legend").getText();
		assertEquals(elementOnInfoPage, "User Info"); 	
	}
	
	//@Ignore
	@Test  // will not pass
	public void testWrongLogin() {
		
		Login login = new Login(driver); 
		login.loginWithCustomerAccount(WRONG_USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
	    //assertEquals("Your login attempt was not successful, try again.\n\nReason: Bad credentials.", browser.findElementByCssSelector("font").getText());
		assertEquals("Such user does not exist in the system – please try again.", browser.findElementByCssSelector("font").getText());
	}
	
	//@Ignore
	@Test  // will not pass
	public void testWrongPassword() {
		
		Login login = new Login(driver); 
		login.loginWithCustomerAccount(USER_NAME_FOR_CUSTOMER, WRONG_PASSWORD_FOR_CUSTOMER);
		assertEquals("Password is incorrect – please try again.", browser.findElementByCssSelector("font").getText());		
	}
	
	//@Ignore
	@Test // will not pass
	public void testEmptyLogin() {

		Login login = new Login(driver);
		login.loginWithCustomerAccount("", PASSWORD_FOR_CUSTOMER);
		assertEquals("Such user does not exist in the system – please try again.", browser.findElementByCssSelector("font").getText());
	}	
	
	//@Ignore
	@Test  // will not pass
	public void testEmptyPassword() {
			
		Login login = new Login(driver); 
		login.loginWithCustomerAccount(USER_NAME_FOR_CUSTOMER, "");
		assertEquals("Password is incorrect – please try again.", browser.findElementByCssSelector("font").getText());		
	}
	
	//@Ignore
	@Test
	public void testSwitchToOrderingPage(){
					
		Login login = new Login(driver); 	
		CustomerUserInfoPage userInfo = login.loginWithCustomerAccount(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
		CustomerOrderingPage ordering = userInfo.switchToOrderingPage();
		String URL = ordering.getBrowser().getCurrentUrl();
		assertEquals(URL, HOME_URL + "order.htm"); 
						
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
	    //System.out.println(actualValues.toString());
	    //System.out.println(expectedValues.toString());
	    assertEquals(expectedValues.size(), actualValues.size());
		assertEquals(expectedValues, actualValues);
	}
	
	//@Ignore
	@Test
	public void testSwitchToCreatingNewOrderPage(){
		
		Login login = new Login(driver); 	
		CustomerUserInfoPage userInfo = login.loginWithCustomerAccount(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);		
		CustomerOrderingPage ordering = userInfo.switchToOrderingPage();
		
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		String elementOnCreateNewOrderPage = createNewOrderPage.getBrowser().findElementByTagName("legend").getText(); // як краще ??
		//String elementOnCreateNewOrderPage = createNewOrderPage.getBrowser().findElementByXpath("//div[@id = 'content']/fieldset/legend").getText();
		assertEquals(elementOnCreateNewOrderPage, "Item Selection"); 	
	}
	
	//@Ignore
	@Test
	public void testClickAddItemButton(){
		
		Login login = new Login(driver); 	
		CustomerUserInfoPage userInfo = login.loginWithCustomerAccount(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);		
		CustomerOrderingPage ordering = userInfo.switchToOrderingPage();
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		
		String URL = addProductsPage.getBrowser().getCurrentUrl();
		assertEquals(URL, HOME_URL + "products.htm");
		
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("Item Name");
		expectedValues.add("Item Description");
		expectedValues.add("Add");
	
		List <String> actualValues =  addProductsPage.getHeadersFromTableWithProducts();
	    //System.out.println(actualValues.toString());
	    //System.out.println(expectedValues.toString());
	    assertEquals(expectedValues.size(), actualValues.size());
		assertEquals(expectedValues, actualValues);
	}
	

}
