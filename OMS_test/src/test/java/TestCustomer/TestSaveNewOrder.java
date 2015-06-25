package TestCustomer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.OrderItem;
import pages.BasePage;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import pages.ordering.CustomerAddProductsToOrderPage;
import pages.ordering.CustomerCreateOrderPage;
import pages.ordering.CustomerOrderingPage;
import tools.BaseDBTest;
import tools.BaseTest;
import tools.Browser;
import tools.DBUnitConfig;
import tools.OrderItemService;

/**
 * This test case is designed for testing (step by step) the Save Order functionality.
 * @author Olesia
 *
 */
public class TestSaveNewOrder extends BaseDBTest {
	
	private static Browser browser;

	private static final String USER_NAME_FOR_CUSTOMER = "customer1";
	private static final String PASSWORD_FOR_CUSTOMER = "qwerty";

	private static final String SELECTED_ASSIGNEE = "merch1";
	private static final String ENTERED_PREFERABLE_DELIVERY_DATE = "10/07/2015";
	String productName = "product1";
	String productDescription = "product description";
	String productPrice = "100.0";
	
	static Logger log = LoggerFactory.getLogger(TestSaveNewOrder.class);

	CustomerOrderingPage ordering;
	
	public Browser getBrowser(){
		return TestSaveNewOrder.browser;
	}
		
	public TestSaveNewOrder(String name) throws Exception {
		super(name);
	}

	@Before
	public void setUp() throws Exception {	

		IDataSet productData = getDataFromFile("data/initProduct.xml");  
        beforeData = new IDataSet[] {productData}; 
        
		super.setUp();	
		
		LoginPage loginPage = new LoginPage(driver);
		UserInfoPage userInfo = loginPage.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
		ordering = userInfo.switchToOrderingPage();
	}


	@Test
	public void testSwitchToOrderingPage(){
		
		log.info("------testSwitchToOrderingPage------");					
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
	    log.info("----testSwitchToOrderingPage pass----");	
	}
	
	@Test
	public void testSwitchToCreatingNewOrderPage(){
		
		log.info("------testSwitchToCreatingNewOrderPage------");		
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
		log.info("----testSwitchToCreatingNewOrderPage pass----");	
	}

	@Test
	public void testClickAddItemButton(){
		
		log.info("------testClickAddItemButton------");	
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("Item Name");
		expectedValues.add("Item Description");
		expectedValues.add("Add");	
		List <String> actualValues =  addProductsPage.getHeadersFromTableWithProducts();
	    assertEquals(expectedValues, actualValues);
	    log.info("----testClickAddItemButton pass----");
	}
	
  @Test   
	public void testSelectProduct() throws Exception{
	  
	    log.info("------testSelectProduct------");	
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectInitProduct();
		String actualName = addProductsPage.findNameOfSelectedProduct();
		assertEquals(productName, actualName  );
		String actualPrice = addProductsPage.findPriceOfSelectedProduct();		
		assertEquals(productPrice, actualPrice);
		log.info("----testSelectProduct pass----");
	}

	@Test
	public void testClickDoneButton() throws Exception{ 
		
		log.info("------testClickDoneButton------");				
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectInitProduct();
		CustomerCreateOrderPage result = addProductsPage.clickDoneButton();
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("1");
		expectedValues.add(productName);
		expectedValues.add(productDescription);
		expectedValues.add("Item");
		expectedValues.add(productPrice);
		expectedValues.add("1");
		expectedValues.add("100.0");
		expectedValues.add("Edit");
		expectedValues.add("Delete");	
		List <String> actualValues =  result.getItemFromTableInItemSelection("td");				
	   	assertEquals(expectedValues, actualValues);	   	
	    log.info("----testClickDoneButton pass----");	
	   	
	   	}
	
	@Test
	public void testSelectAssignee() throws Exception{ 
		
		log.info("------testSelectAssignee------");
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectInitProduct();
		addProductsPage.clickDoneButton();
		String result = createNewOrderPage.selectAssignee(SELECTED_ASSIGNEE);
		assertEquals("merch1",result);
		log.info("----testSelectAssignee pass----");
	}
	
	@Test
	public void testClickSaveButton() throws Exception{ 

		log.info("------TestClickSaveButton------");
    	CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectInitProduct();
		addProductsPage.clickDoneButton();
		createNewOrderPage.enterPreferableDeliveryDate(ENTERED_PREFERABLE_DELIVERY_DATE);
		createNewOrderPage.selectAssignee(SELECTED_ASSIGNEE);
		createNewOrderPage.clickSaveButton();
		createNewOrderPage.switchToOrderingPage();
	
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("OrderName1");
		expectedValues.add("100.0");
		expectedValues.add("0");
		expectedValues.add("");
		expectedValues.add("Created");  
		expectedValues.add("merch1"); 
		expectedValues.add("Edit");
		expectedValues.add("Delete");
		List <String> actualValues =  ordering.getValuesFromTableWithOrders("td");
	    assertEquals(expectedValues, actualValues);	
	    
	    log.info("----TestClickSaveButton pass----");	   
	}
	
	@After
	public void tearDown() throws Exception {
		 
		driver.quit();		
		try{

			List<OrderItem>  orderItems = OrderItemService.getAll(); 
					
		  		for(OrderItem orderItem : orderItems){ 
				OrderItemService.delete(orderItem);			    	
			}
		    }
			catch (Exception e ){	
				System.out.println(e.getMessage());
			}
		
		DatabaseOperation.DELETE.execute(getConnection(), getDataSet());
		
	}
	
}
