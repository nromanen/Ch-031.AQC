package ordering;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Test;
import baseTest.TestConfigWithDBUnit;
import pages.auth.LoginPage;
import pages.ordering.MerchandiserOrderingPage;

public class TestMerchandiserEditOrder extends TestConfigWithDBUnit{
	
	private String merchandiserLogin = "merch1";
	private String merchandiserPassword = "qwerty";

	protected IDataSet orderData;
	
	public TestMerchandiserEditOrder(String name) {
		super(name);
		try {
			orderData = getDataFromFile("ordersData.xml");
			

		} catch (DataSetException e) {
			e.printStackTrace();
		}
	}	
	
	 @Before
	    public  void setUp() throws Exception {		
		 super.initialize();
			 beforeData = new IDataSet[] { orderData };
			 super.setUp();		     
	    }
	 
	 @Test
	 	public void testOrderingShowItems(){
		 	MerchandiserOrderingPage merchOrderingPage = new MerchandiserOrderingPage(browser.getDriver());
		 	new LoginPage(browser.getDriver()).login(merchandiserLogin, merchandiserPassword);
		 	merchOrderingPage.findOrderingTabAndClick();
		 	assertEquals("Show 10 items", merchOrderingPage.finnShow10ItemsLinkByXpathAndGetText());
		 	merchOrderingPage.findShowItemsLinkByXPathAndClick();
		 	assertEquals("2", merchOrderingPage.findPageCountLinkByXPathLocatorAndGetText());
		 	merchOrderingPage.findShowItemsLinkByXPathAndClick();
		 	assertEquals("3", merchOrderingPage.findPageCountLinkByXPathLocatorAndGetText());
		}	 

}
