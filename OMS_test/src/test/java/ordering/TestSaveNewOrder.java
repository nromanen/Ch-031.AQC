package ordering;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
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

/**
 * 
 * @author Olesia
 *
 */
public class TestSaveNewOrder extends DBUnitConfig {

	private static WebDriver driver;
	private static Browser browser;
	private static final String HOME_URL = "http://localhost:8080/OMS/";
	private static final int TIMEOUT = 2;
	
	private static final String USER_NAME_FOR_CUSTOMER = "customer1";
	private static final String PASSWORD_FOR_CUSTOMER = "qwerty";

	private static final String SELECTED_ASSIGNEE = "merch1";
	private static final String ENTERED_PREFERABLE_DELIVERY_DATE = "07/06/2015";

	CustomerOrderingPage ordering;
		
	public TestSaveNewOrder(String name) throws Exception {
		super(name);
	}

	@Before
	public void setUp() throws Exception {

		IDataSet productData = getDataFromFile("products.xml");
		beforeData = new IDataSet[] { productData };
		
		DatabaseOperation.REFRESH.execute(getConnection(), getDataSet());

		driver = new FirefoxDriver();
		browser = new Browser(driver);
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.get(HOME_URL);

		LoginPage loginPage = new LoginPage(driver);
		UserInfoPage userInfo = loginPage.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
		ordering = userInfo.switchToOrderingPage();
	}


	@Test
	public void testSwitchToOrderingPage(){
					
		List <String> expectedValues = new ArrayList<String>();
		expectedValues.add("Order Name");
		expectedValues.add("Total price");
		expectedValues.add("Max Discount");
		expectedValues.add("Delivery date");
		expectedValues.add("Status");
		expectedValues.add("Assignee");
		expectedValues.add("Edit");
		expectedValues.add("Delete");				
		List <String> actualValues =  ordering.getValuesFromTableWithOrders("th");
	    assertEquals(expectedValues, actualValues);
	}
	
	@Test
	public void testSwitchToCreatingNewOrderPage(){
		
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("Item Number");
		expectedValues.add("Item Name");
		expectedValues.add("Item Description");
		expectedValues.add("Dimension");
		expectedValues.add("Price");
		expectedValues.add("Quantity");
		expectedValues.add("Price per Line");
		expectedValues.add("Edit");
		expectedValues.add("Delete");
		List <String> actualValues =  createNewOrderPage.getItemFromTableInItemSelection("th");
		assertEquals(expectedValues, actualValues);
	}

	@Test
	public void testClickAddItemButton(){
		
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("Item Name");
		expectedValues.add("Item Description");
		expectedValues.add("Add");	
		List <String> actualValues =  addProductsPage.getHeadersFromTableWithProducts();
	    assertEquals(expectedValues, actualValues);
	}
	
	@Test
	public void testSelectProduct() throws Exception{

		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectProduct();
		assertEquals("table", browser.findElementByXpath("//form[@id = 'doneForm']/table/tbody/tr[1]/td[2]").getText());
		assertEquals("800.0", browser.findElementByXpath("//form[@id = 'doneForm']/table/tbody/tr[3]/td[2]").getText());
	}

	@Test
	public void testClickDoneButton() throws Exception{ 
				
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectProduct();
		CustomerCreateOrderPage result = addProductsPage.clickDoneButton();
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("1");
		expectedValues.add("table");
		expectedValues.add("brown");
		expectedValues.add("Item");
		expectedValues.add("800.0");
		expectedValues.add("1");
		expectedValues.add("800.0");
		expectedValues.add("Edit");
		expectedValues.add("Delete");	
		List <String> actualValues =  result.getItemFromTableInItemSelection("td");				
	   	assertEquals(expectedValues, actualValues);
	}
	
	@Test
	public void testSelectAssignee() throws Exception{ 
		
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectProduct();
		addProductsPage.clickDoneButton();
		String result = createNewOrderPage.selectAssignee(SELECTED_ASSIGNEE);
		assertEquals("merch1",result);
	}
	
	@Test
	public void testClickSaveButton() throws Exception{ 
			
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectProduct();
		addProductsPage.clickDoneButton();
		createNewOrderPage.enterPreferableDeliveryDate(ENTERED_PREFERABLE_DELIVERY_DATE);
		createNewOrderPage.selectAssignee(SELECTED_ASSIGNEE);
		createNewOrderPage.clickSaveButton();
		createNewOrderPage.switchToOrderingPage();
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("OrderName1");
		expectedValues.add("800.0");
		expectedValues.add("0");
		expectedValues.add("");
		expectedValues.add("Created");
		expectedValues.add("merch1");
		expectedValues.add("Edit");
		expectedValues.add("Delete");
		List <String> actualValues =  ordering.getValuesFromTableWithOrders("td");
	    assertEquals(expectedValues, actualValues);				
	}
	
	@After
	public void tearDown() throws Exception {

		driver.quit(); 
	}
	
	@AfterClass
	public void tearDownAfterClass() throws Exception  {
		super.tearDown();
		DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
	}
}


