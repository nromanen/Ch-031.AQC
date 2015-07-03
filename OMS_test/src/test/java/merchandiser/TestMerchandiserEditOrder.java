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

public class TestMerchandiserEditOrder extends BaseDBTest {

	private static String MERCHANDISER_LOGIN = "merch1";
	private static String MERCHANDISER_PASSWORD = "qwerty";
	private static String TEST_ORDER_NAME = "OrderName12";
	private static String TEST_DELIVERY_DATE = "30/06/2015";
	private static MerchandiserOrderingPage MERCH_ORDERING_PAGE;
	static Logger LOG = LoggerFactory
			.getLogger(TestMerchandiserEditOrder.class);

	@BeforeClass
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
	public void testEditOrderChangeDeliveryDate() {
		LOG.info("Start of test testEditOrderChangeDeliveryDate ");
		MERCH_ORDERING_PAGE
				.findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
		MERCH_ORDERING_PAGE.findApplyButtonByXPathLocatorAndClick();
		System.out.println(MERCH_ORDERING_PAGE
				.findDeliveryDate1OrderAndGetText());
		MERCH_ORDERING_PAGE.findAndClickFirstEditOrder();
		MERCH_ORDERING_PAGE
				.findDeliveryDateFieldInEditAndSendNewKey(TEST_DELIVERY_DATE);
		MERCH_ORDERING_PAGE.findSaveButtonAndClick();
		assertEquals(TEST_DELIVERY_DATE,
				MERCH_ORDERING_PAGE.findDeliveryDate1OrderAndGetText());
		LOG.info("End of test for testEditOrderChangeDeliveryDate");

	}

	@After
	public void tearDown() {
		try {
			LOG.info("tearDown starts");
			super.tearDown();
		} catch (Exception e) {
			LOG.info("tearDown failed");
			e.printStackTrace();
		}
	}
}
