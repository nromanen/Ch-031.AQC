package supervisor;

import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.ordering.ItemManagementPage;
import tools.BaseDBTest;

/**
 * This test case is designed for testing the Delete Product functionality.
 * @author Olya
 */
public class DeleteProductTest extends BaseDBTest {
    private BasePage basePage;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME_DELETE = "lemon";


    public DeleteProductTest(String name) throws Exception {
        super(name);
    }

   @Before
   public void setUp() throws Exception {
       beforeData = new IDataSet[]{getDataFromFile("data/productData.xml")};
       super.setUp();
    }

    @Test
    public void testDeleteProduct() {
        basePage = new BasePage(driver);
        UserInfoPage userInfoPage = basePage.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();
        itemManagementPage.clickDeleteLinkOnProductAndDismiss(PRODUCT_NAME_DELETE);
        assertNotNull(itemManagementPage.findProductByNameInTable(PRODUCT_NAME_DELETE));
        itemManagementPage.clickDeleteLinkOnProductAndAccept(PRODUCT_NAME_DELETE);
        assertNull(itemManagementPage.findProductByNameInTable(PRODUCT_NAME_DELETE));
    }

    @After
    public void tearDown() throws Exception {
        basePage.logout();
        super.tearDown();
    }
}