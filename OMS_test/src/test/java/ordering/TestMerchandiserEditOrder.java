package ordering;

import junit.extensions.TestSetup;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import pages.auth.LoginPage;
import pages.ordering.MerchandiserOrderingPage;
import baseTest.TestConfigWithDBUnit;

public class TestMerchandiserEditOrder extends TestConfigWithDBUnit {

	private static String MERCHANDISER_LOGIN = "merch1";
	private static String MERCHANDISER_PASSWORD = "qwerty";
	private static String TEST_ORDER_NAME = "OrderName12";
	private static String TEST_DELIVERY_DATE = "30/06/2015";

	//protected IDataSet orderData;

	public TestMerchandiserEditOrder(String name) {
		super(name);
		
	}
	
	 @Before
	 public  void setUp() throws Exception  {
		 super.initializationDataBase("products.xml", "testOrdersDataForMerchTemp.xml");
		 new LoginPage(browser.getDriver()).login(MERCHANDISER_LOGIN, MERCHANDISER_PASSWORD);

	    }
	 MerchandiserOrderingPage merchOrderingPage = new MerchandiserOrderingPage(browser.getDriver());
	 
	 public static TestSetup suite( ) { 
		 TestSetup setup = new TestSetup(new TestSuite(TestMerchandiserEditOrder.class)) { 
			 protected void setUp( ) throws Exception { 
				 initialize();
				 }
			 protected void tearDown( ) throws Exception { 
				 quit();
				 } 
		 }; return setup; 
	}
			 
	
	 
	 @Test
	 public void testEditOrderChangeDeliveryDate(){
		 merchOrderingPage.findOrderingTabAndClick();
		 merchOrderingPage.findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
		 merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
		 System.out.println(merchOrderingPage.findDeliveryDate1OrderAndGetText());
		 merchOrderingPage.findAndClickFirstEditOrder();
		 merchOrderingPage.findDeliveryDateFieldInEditAndSendNewKey(TEST_DELIVERY_DATE);
		 merchOrderingPage.findSaveButtonAndClick();
		 assertEquals(TEST_DELIVERY_DATE, merchOrderingPage.findDeliveryDate1OrderAndGetText());
		 new LoginPage(browser.getDriver()).logout();

		 
	 }
	 
	 @Test
	 public void testEditOrderChangeDeliveryDate2(){
		 merchOrderingPage.findOrderingTabAndClick();
		 merchOrderingPage.findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
		 merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
		 System.out.println(merchOrderingPage.findDeliveryDate1OrderAndGetText());
		 merchOrderingPage.findAndClickFirstEditOrder();
		 merchOrderingPage.findDeliveryDateFieldInEditAndSendNewKey(TEST_DELIVERY_DATE);
		 merchOrderingPage.findSaveButtonAndClick();
		 
		 assertEquals(TEST_DELIVERY_DATE, merchOrderingPage.findDeliveryDate1OrderAndGetText());
		 new LoginPage(browser.getDriver()).logout();

		 
	 }
	 
	 @Test
	 public void testEditOrderChangeDeliveryDate3(){
		 merchOrderingPage.findOrderingTabAndClick();
		 merchOrderingPage.findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
		 merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
		 System.out.println(merchOrderingPage.findDeliveryDate1OrderAndGetText());
		 merchOrderingPage.findAndClickFirstEditOrder();
		 merchOrderingPage.findDeliveryDateFieldInEditAndSendNewKey(TEST_DELIVERY_DATE);
		 merchOrderingPage.findSaveButtonAndClick();
		 assertEquals(TEST_DELIVERY_DATE, merchOrderingPage.findDeliveryDate1OrderAndGetText());
		 new LoginPage(browser.getDriver()).logout();

		 
	 }
	 
	 @Test
	 public void testEditOrderChangeDeliveryDate4(){
		 merchOrderingPage.findOrderingTabAndClick();
		 merchOrderingPage.findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
		 merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
		 System.out.println(merchOrderingPage.findDeliveryDate1OrderAndGetText());
		 merchOrderingPage.findAndClickFirstEditOrder();
		 merchOrderingPage.findDeliveryDateFieldInEditAndSendNewKey(TEST_DELIVERY_DATE);
		 merchOrderingPage.findSaveButtonAndClick();
		 assertEquals(TEST_DELIVERY_DATE, merchOrderingPage.findDeliveryDate1OrderAndGetText());
		 new LoginPage(browser.getDriver()).logout();

		 
	 }
}
