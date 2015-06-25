package TestCustomer;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.OrderItem;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import pages.ordering.CustomerOrderingPage;
import tools.BaseDBTest;
import tools.Browser;
import tools.DBUnitConfig;
import tools.OrderItemService;

/**
 * This test case is designed for testing the Show 10 (or 5) items functionality.
 * It checks correct change of name of appropriate link and correct count of orders
 * in table after ckicking on this link
 * @author Olesia
 *
 */

public class TestShowOrdersInTable extends BaseDBTest {
	
	private static final String USER_NAME_FOR_CUSTOMER = "customer1";
	private static final String PASSWORD_FOR_CUSTOMER = "qwerty";
	static Logger log = LoggerFactory.getLogger(TestShowOrdersInTable.class);
	
	CustomerOrderingPage ordering;
	
	public TestShowOrdersInTable(String name) throws Exception {
		super(name);
	}
	
	@Before
	public void setUp() throws Exception {	
		 
		IDataSet orderData = getDataFromFile("data/initOrders.xml");
        beforeData = new IDataSet[] {orderData};
		
		super.setUp();
				
		LoginPage loginPage = new LoginPage(driver);
		UserInfoPage userInfo = loginPage.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
		ordering = userInfo.switchToOrderingPage();
	}
		
	@Test
	public void testChangeShowTenOrFiveItemsLink(){  
		
		log.info("------testChangeShowTenOrFiveItemsLink------");	
		ordering.clickShowTenItems();
		boolean result1 = ordering.findShowFiveItemsLink();
		assertTrue(result1);
		
		ordering.clickShowFiveItems();
		boolean result2 = ordering.findShowTenItemsLink();
		assertTrue(result2);
		log.info("----testChangeShowTenOrFiveItemsLink pass----");
	}
	
	@Test
	public void testClickShowFiveItemsLink(){ 
		
		log.info("------testClickShowFiveItemsLink------");
		ordering.clickShowTenItems();
		ordering.clickShowFiveItems();
		int result = ordering.getCountOfOrdersInTable();	
		assertEquals(5, result);	
		log.info("----testClickShowFiveItemsLink pass----");
	}
	
	@Test
	public void testClickShowTenItemsLink(){ 
		
		log.info("------testClickShowTenItemsLink------");
		ordering.clickShowTenItems();
		int result = ordering.getCountOfOrdersInTable();
		assertEquals(10, result);	
		log.info("----testClickShowTenItemsLink pass----");
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