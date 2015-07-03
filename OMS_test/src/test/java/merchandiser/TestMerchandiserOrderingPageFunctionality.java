package merchandiser;

import static org.junit.Assert.*;

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
	private static MerchandiserOrderingPage MERCH_ORDERING_PAGE;
	static Logger LOG = LoggerFactory
			.getLogger(TestMerchandiserEditOrder.class);
	
	@BeforeClass
	/**
	 * @BeforeClass needs to open browser, 
	 * go to base page and login as Merchandiser
	 */
	public static void initialize() {
		BaseDBTest.initialize();
		BasePage basePage = new BasePage(browser.getDriver());
		UserInfoPage userInfoPage = basePage.login(MERCHANDISER_LOGIN,
				MERCHANDISER_PASSWORD);
		MERCH_ORDERING_PAGE = userInfoPage.selectOrderingTabByMerchandiser();
	}

	@Before
	public void setUp() {
		super.initializationDataBase("data/testOrdersDataForMerchTemp.xml");
		super.setUp();
	}

	@Test
	/**
	 * This text verify if we click on link "Show 10 items", ten orders will be shown on ordering page.
	 * Name of this link will change from "Show 10 items" to "Show 5 items".
	 * Click on "Show 5 items" will show 5 orders on ordering page
	 */
	public void testOrderingShowItems() {
		assertEquals("Show 10 items",
				MERCH_ORDERING_PAGE.findShow10ItemsLinkByXpathAndGetText());
		MERCH_ORDERING_PAGE.findShowItemsLinkByXPathAndClick();
		assertEquals("2",
				MERCH_ORDERING_PAGE.findPageCountLinkByXPathLocatorAndGetText());
		MERCH_ORDERING_PAGE.findShowItemsLinkByXPathAndClick();
		assertEquals("3",
				MERCH_ORDERING_PAGE.findPageCountLinkByXPathLocatorAndGetText());

	}

	@Test
	public void testSearchOrderByOrderName() throws Exception {
		MERCH_ORDERING_PAGE
				.findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
		MERCH_ORDERING_PAGE.findApplyButtonByXPathLocatorAndClick();
		assertEquals(TEST_ORDER_NAME,
				MERCH_ORDERING_PAGE.getFoundedOrderNameText());
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
