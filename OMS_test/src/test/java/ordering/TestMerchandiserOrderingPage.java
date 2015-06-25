package ordering;

import static org.junit.Assert.*;
import pages.auth.LoginPage;
import pages.ordering.MerchandiserOrderingPage;
import baseTest.TestConfig;

import org.junit.Test;
import org.junit.BeforeClass;

public class TestMerchandiserOrderingPage extends TestConfig {
	private static String merchandiserLogin = "merch1";
	private static String merchandiserPassword = "qwerty";
	private static String headerLinkTextFromSRS = "Ordering Management System.";
	private static String underHeaderLinkTextFromSRS = "Simple. Slim. Genius.";
	private static String userInfoTabtextFromSRS = "User Info";
	private static String orderingTabTextFromSRS = "Ordering";
	private static String searchForOrderByLinkTextFromSRS = "Search for order by ";
	private static String orderNameLinkTextFromSRS = "Order Name";
	private static String totalPriceLinkTextFromSRS = "Total Price";
	private static String maxDiscountLinktextFromSRS = "MaxDiscount";
	private static String deliveryDateLinktextFromSRS = "Delivery Date";
	private static String statusLinktextFromSRS = "Status";
	private static String editLinkTextFromSRS = "Edit";
	private static String deleteLinkTextFromSRS = "Delete";
	
	static MerchandiserOrderingPage merchOrderingPage = new MerchandiserOrderingPage(browser.getDriver());
	@BeforeClass
	public static void initialize() throws Exception {
		TestConfig.initialize();
		new LoginPage(browser.getDriver()).login(merchandiserLogin, merchandiserPassword);
		merchOrderingPage.findOrderingTabAndClick();
		
	}
	@Test
	public void testHeader(){
		assertEquals(headerLinkTextFromSRS, merchOrderingPage.findHeaderLinkByTagNameLocatorAndGetText());
	}
	
	@Test
	public void testUnderHeaderLink(){
		assertEquals(underHeaderLinkTextFromSRS, merchOrderingPage.findUnderHeaderLinkByTagNameLocatorAndGetText());
	}
	
	@Test
	public void testUserInfoTabText(){
		assertEquals(userInfoTabtextFromSRS, merchOrderingPage.findUserInfoTabByXPathLocatorAndGetText());
	}
	
	@Test
	public void testOrderingTabText(){
		assertEquals(orderingTabTextFromSRS, merchOrderingPage.findOrderingTabByXPathLocatorAndGetText());
	}
	@Test
	public void testSearchForOrderByText(){
		assertEquals(searchForOrderByLinkTextFromSRS, merchOrderingPage.findSearhForOrderByLinkByXPathLocatorAndGetText());
	}
	
	@Test
	public void testOrderNameText(){
		assertEquals(orderNameLinkTextFromSRS, merchOrderingPage.findPageCountLinkByXPathLocatorAndGetText());
	}
	
	@Test
	public void testTotalPriceText(){
		assertEquals(totalPriceLinkTextFromSRS, merchOrderingPage.getTotalPriceItemText());
	}
	
	@Test
	public void testMaxDiscountText(){
		assertEquals(maxDiscountLinktextFromSRS, merchOrderingPage.getMaxDiscountItemText());
	}
	
	@Test
	public void testDeliveryDateText(){
		assertEquals(deliveryDateLinktextFromSRS, merchOrderingPage.getDeliveryDateItemText());
	}
	
	@Test
	public void testStatusText(){
		assertEquals(statusLinktextFromSRS, merchOrderingPage.getStatusItemText());
	}
	
	@Test
	public void testEditText(){
		assertEquals(editLinkTextFromSRS, merchOrderingPage.getEditItemText());
	}
	
	@Test
	public void testDeleteText(){
		assertEquals(deleteLinkTextFromSRS, merchOrderingPage.getDeleteItemText());
	}
}
