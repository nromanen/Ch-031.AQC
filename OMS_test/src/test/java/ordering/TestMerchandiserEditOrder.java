package ordering;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Test;

import pages.auth.LoginPage;
import pages.ordering.MerchandiserOrderingPage;
import baseTest.TestConfigWithDBUnit;

public class TestMerchandiserEditOrder extends TestConfigWithDBUnit {

	private static String merchandiserLogin = "merch1";
	private static String merchandiserPassword = "qwerty";
	private static String testOrderName = "OrderName12";
	private static String testDeliveryDate = "30/06/2015";

	protected IDataSet orderData;

	public TestMerchandiserEditOrder(String name) {
		super(name);
		
	}
	
	 @Before
	 public  void setUp() throws Exception {
		 initialize();
		 try {
			 orderData = getDataFromFile("testOrdersDataForMerchTemp.xml");
		 } catch (DataSetException e) {
			 e.printStackTrace();
		 }
		 beforeData = new IDataSet[] { orderData };
		 super.setUp();
		 
		 new LoginPage(browser.getDriver()).login(merchandiserLogin, merchandiserPassword);
		 merchOrderingPage.findOrderingTabAndClick();
	    }
	 MerchandiserOrderingPage merchOrderingPage = new MerchandiserOrderingPage(browser.getDriver());
	 
	 @Test
	 public void testEditOrderChangeDeliveryDate(){
		 merchOrderingPage.findOrderingTabAndClick();
		 merchOrderingPage.findSearchForOrderByValueLinkAndSenKey(testOrderName);
		 merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
		 merchOrderingPage.findAndClickFirstEditOrder();
		 merchOrderingPage.findDeliveryDateFieldInEditAndSendNewKey(testDeliveryDate);
		 merchOrderingPage.findSaveButtonAndClick();
		 assertEquals(testDeliveryDate, merchOrderingPage.findDeliveryDate1OrderAndGetText());
		 
	 }
	 
}
