package supervisor;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.BaseDBTest;
import tools.BaseTest;
import tools.TableRow;
import static org.junit.Assert.assertEquals;

/**
 * This test case is designed for testing the Add Product functionality.
 *
 * @author Olya.
 */
public class AddProductTest extends BaseDBTest {

    private static ItemManagementPage ITEM_MANAGEMENT_PAGE;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME = "Test Product";
    private static final String PRODUCT_DESCRIPTION = "Function test";
    private static final String PRODUCT_PRICE = "65.0";
    
    @BeforeClass
    public static void initialize() {
		BaseDBTest.initialize();
		BasePage basePage = new BasePage(browser.getDriver());
		UserInfoPage userInfoPage = basePage.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        ITEM_MANAGEMENT_PAGE = userInfoPage.selectItemManagementTab();
	}

    @Test
    /**
     * This test verify that new product with name PRODUCT_NAME, description PRODUCT_DESCRIPTION
     * and price PRODUCT_PRICE is created and is visible in product table on Item Management page.
     */
    public void testAddProducts() throws Exception {
        
        AddProductPage addProductPage = ITEM_MANAGEMENT_PAGE.goToAddProduct();
        addProductPage.setProductNameValue(PRODUCT_NAME);
        assertEquals(PRODUCT_NAME, addProductPage.getProductNameValue());
        addProductPage.setProductDescriptionValue(PRODUCT_DESCRIPTION);
        assertEquals(PRODUCT_DESCRIPTION, addProductPage.getProductDescriptionValue());
        addProductPage.setProductPriceValue(PRODUCT_PRICE);
        assertEquals(PRODUCT_PRICE, addProductPage.getProductPriceValue());
        addProductPage.clickOkButton();
        TableRow row = ITEM_MANAGEMENT_PAGE.findProductByNameInTable(PRODUCT_NAME);
        assertEquals(PRODUCT_DESCRIPTION, row.getNthColumnValue(1));
        assertEquals(PRODUCT_PRICE, row.getNthColumnValue(2));
    }

}

