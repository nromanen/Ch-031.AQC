package merchandiser;

import static org.junit.Assert.*;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
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
	private MerchandiserOrderingPage merchOrderingPage;
	static Logger LOG = LoggerFactory
			.getLogger(TestMerchandiserEditOrder.class);



	@Before
	public void setUp() throws Exception {
		// initTables();
		beforeData = new IDataSet[] { getDataFromFile("data/testOrdersDataForMerchTemp.xml") };
		super.setUp();
        basePage = new BasePage(driver);
        UserInfoPage userInfoPage = basePage.login(MERCHANDISER_LOGIN, MERCHANDISER_PASSWORD);
        merchOrderingPage = userInfoPage.selectOrderingTabByMerchandiser();
	}

	@Test
	public void testEditOrderChangeDeliveryDate() {
		LOG.info("Start of test testEditOrderChangeDeliveryDate ");
		merchOrderingPage
				.findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
		merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
		System.out.println(merchOrderingPage
				.findDeliveryDate1OrderAndGetText());
		merchOrderingPage.findAndClickFirstEditOrder();
		merchOrderingPage
				.findDeliveryDateFieldInEditAndSendNewKey(TEST_DELIVERY_DATE);
		merchOrderingPage.findSaveButtonAndClick();
		assertEquals(TEST_DELIVERY_DATE,
				merchOrderingPage.findDeliveryDate1OrderAndGetText());
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
