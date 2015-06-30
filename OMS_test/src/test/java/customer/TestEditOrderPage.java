/**
 * author: Alexander Melnychuk
 * This is the test class for testing Edit Order's Save and Order functions.
 */

package customer;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.*;
import pages.BasePage;;
import pages.auth.UserInfoPage;
import pages.ordering.*;
import tools.BaseDBTest;
import tools.CheckTableValue;

public class TestEditOrderPage extends BaseDBTest {
    private OrderPage orderPage;
    private BasePage basePage;
    private static final String LOGIN = "customer1";
    private static final String PASS = "qwerty";

    public TestEditOrderPage() throws Exception{
        super("");
    }

    @Before
    public  void setUp() throws Exception {
        IDataSet productData;
        try {
            productData = getDataFromFile("data/partial.xml");
            beforeData = new IDataSet[] {productData};
        } catch(DataSetException e){
            e.printStackTrace();
        }

        try {
            super.setUp();
        } catch (Exception e) {

            e.printStackTrace();
        }

        basePage = new BasePage(driver);
        UserInfoPage userInfoPage = basePage.login(LOGIN, PASS);
        orderPage = userInfoPage.goToOrderingTab();
    }

    @Test
    public  void testEditOrderStatus() throws javax.script.ScriptException{
        EditOrderPage eo = orderPage.goTo1EditOrder();
        assertTrue("Can't edit order", eo.isAddItem() == true);
    }

    @Test
    public void testAddItem() throws Exception{
        EditOrderPage eo = orderPage.goTo1EditOrder();
        AddItemPage add = eo.addItemClick();
        add.selectFirstItem();
        String itemQuantity = "2";
        add.setItemQuantity(itemQuantity);
        add.clickDoneButton();
        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Item was added, quantity is wrong", itemQuantity, tableValue.findValue("list", "Quantity", 1));
    }

    @Test
    public void testSaveButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();
        String expectedOrderNumber = "100";
        editOrderPage.setOrderNumber(expectedOrderNumber);
        String expectedPreferableDate = "10/05/2015";
        editOrderPage.setPreferableDate(expectedPreferableDate);
        String expectedAssignee = "merch1";
        editOrderPage.setAssignee(expectedAssignee);
        editOrderPage.clickSave();

        assertEquals("Order number is wrong", expectedOrderNumber, editOrderPage.checkOrderNumber());
        assertEquals("Preferable date is wrong", expectedPreferableDate, editOrderPage.checkPreferableDate());
        assertEquals("Assignee value is wrong", expectedAssignee, editOrderPage.checkAssignee());
    }

    @Test
    public void testVisaOrderButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();
        editOrderPage.setOrderNumber("100");
        editOrderPage.setPreferableDate("10/05/2015");
        editOrderPage.setAssignee("merch1");
        editOrderPage.clickSave();
        editOrderPage.setCardType("Visa");
        editOrderPage.setCardNumber("4323355300847977");
        editOrderPage.setCVV2("111");
        editOrderPage.setExpiryDate("02", "2017");
        editOrderPage.clickOrder();

        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Order doesn't have ordered status", "Ordered", tableValue.findValue("list", "Status", 0));
    }

    @Test
    public void testMasterCardOrderButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();
        editOrderPage.setOrderNumber("101");
        editOrderPage.setPreferableDate("10/05/2015");
        editOrderPage.setAssignee("merch1");
        editOrderPage.clickSave();
        editOrderPage.setCardType("MasterCard");
        editOrderPage.setCardNumber("4323355300847977");
        editOrderPage.setCVV2("111");
        editOrderPage.setExpiryDate("02", "2017");
        editOrderPage.clickOrder();

        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Order doesn't have ordered status", "Ordered", tableValue.findValue("list", "Status", 0));
    }

    @Test
    public void testAmericanExpressOrderButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();
        editOrderPage.setOrderNumber("102");
        editOrderPage.setPreferableDate("10/05/2015");
        editOrderPage.setAssignee("merch1");
        editOrderPage.clickSave();
        editOrderPage.setCardType("American Express");
        editOrderPage.setCardNumber("4323355300847977");
        editOrderPage.setCVV2("111");
        editOrderPage.setExpiryDate("02", "2017");
        editOrderPage.clickOrder();

        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Order doesn't have ordered status", "Ordered", tableValue.findValue("list", "Status", 0));
    }

    @Test
    public void testMaestroOrderButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();

        editOrderPage.setOrderNumber("103");
        editOrderPage.setPreferableDate("10/05/2015");
        editOrderPage.setAssignee("merch1");
        editOrderPage.clickSave();
        editOrderPage.setCardType("Maestro");
        editOrderPage.setCardNumber("4323355300847977");
        editOrderPage.setCVV2("111");
        editOrderPage.setExpiryDate("02", "2017");
        editOrderPage.setMaestroDate("10/05/2015");
        editOrderPage.setMaestroIssue("2");
        editOrderPage.clickOrder();

        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Order doesn't have ordered status", "Ordered", tableValue.findValue("list", "Status", 0));
    }


    @After
    public  void tearDown() throws Exception{
        super.tearDown();
    }


}