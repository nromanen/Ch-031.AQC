package ordering;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Test;
import baseTest.TestConfigWithDBUnit;
import pages.auth.LoginPage;
import pages.ordering.MerchandiserOrderingPage;

public class TestMerchandiserOrderingPageFunctionality extends TestConfigWithDBUnit{
	
	private static String merchandiserLogin = "merch1";
	private static String merchandiserPassword = "qwerty";
	private static String testOrderName = "OrderName11"; 

	protected IDataSet orderData;
	
	public TestMerchandiserOrderingPageFunctionality(String name) {
		super(name);
		
	}	
	
	 MerchandiserOrderingPage merchOrderingPage = new MerchandiserOrderingPage(browser.getDriver());
	 
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
	 
	 @Test 
	 public void testOrderingShowItems(){
		merchOrderingPage.findOrderingTabAndClick();
		assertEquals("Show 10 items", merchOrderingPage.findShow10ItemsLinkByXpathAndGetText());
		merchOrderingPage.findShowItemsLinkByXPathAndClick();
		assertEquals("2", merchOrderingPage.findPageCountLinkByXPathLocatorAndGetText());
		merchOrderingPage.findShowItemsLinkByXPathAndClick();
		assertEquals("3", merchOrderingPage.findPageCountLinkByXPathLocatorAndGetText());
		new LoginPage(browser.getDriver()).logout();
		
	}
	 
	 @Test
	 public void testSearchOrderByOrderName() throws Exception{
		merchOrderingPage.findSearchForOrderByValueLinkAndSenKey(testOrderName);
		merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
		assertEquals(testOrderName, merchOrderingPage.getFoundedOrderNameText());
		new LoginPage(browser.getDriver()).logout();		 
	 }

}
