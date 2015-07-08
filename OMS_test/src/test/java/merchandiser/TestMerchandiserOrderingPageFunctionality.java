package merchandiser;

import static org.junit.Assert.*;

import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.ordering.MerchandiserOrderingPage;
import tools.BaseDBTest;

public class TestMerchandiserOrderingPageFunctionality extends BaseDBTest {

	private static String MERCHANDISER_LOGIN = "merch1";
	private static String MERCHANDISER_PASSWORD = "qwerty";
	private static String TEST_ORDER_NAME = "OrderName11";
	private MerchandiserOrderingPage merchOrderingPage;
	static Logger LOG = LoggerFactory
			.getLogger(TestMerchandiserEditOrder.class);

	@Before
	public void setUp() throws Exception {
		beforeData = new IDataSet[] { getDataFromFile("data/testOrdersDataForMerchTemp.xml") };
		super.setUp();
        basePage = new BasePage(driver);
        UserInfoPage userInfoPage = basePage.login(MERCHANDISER_LOGIN, MERCHANDISER_PASSWORD);
        merchOrderingPage = userInfoPage.selectOrderingTabByMerchandiser();
	}

	@Test
	/**
	 * This text verify if we click on link "Show 10 items", ten orders will be shown on ordering page.
	 * Name of this link will change from "Show 10 items" to "Show 5 items".
	 * Click on "Show 5 items" will show 5 orders on ordering page
	 */
	public void testOrderingShowItems() {
		assertEquals("Show 10 items",
				merchOrderingPage.findShow10ItemsLinkByXpathAndGetText());
		merchOrderingPage.findShowItemsLinkByXPathAndClick();
		assertEquals("2",
				merchOrderingPage.findPageCountLinkByXPathLocatorAndGetText());
		merchOrderingPage.findShowItemsLinkByXPathAndClick();
		assertEquals("3",
				merchOrderingPage.findPageCountLinkByXPathLocatorAndGetText());

	}

	@Test
	public void testSearchOrderByOrderName() throws Exception {
		merchOrderingPage
				.findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
		merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
		assertEquals(TEST_ORDER_NAME,
				merchOrderingPage.getFoundedOrderNameText());
	}

	@After
	public void tearDown() {
		try {
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
