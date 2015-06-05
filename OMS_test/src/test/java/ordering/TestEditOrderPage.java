/**
 * author: Alexander Melnychuk
 * This is the test class for testing Edit Order's Save and Order functions.
 */

package ordering;

import org.dbunit.dataset.IDataSet;
import org.junit.*;
import pages.auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.auth.UserInfoPage;
import pages.ordering.AddItemPage;
import pages.ordering.EditOrderPage;
import pages.ordering.OrderPage;
import tools.CheckTableValue;
import tools.DBUnitConfig;

public class TestEditOrderPage extends DBUnitConfig{
    private static WebDriver driver;
    private static String BASE_URL = "http://localhost:8080/OMS2";
    String LOGIN = "customer1";
    String PASS = "qwerty";

    public TestEditOrderPage(String name) {
        super(name);
    }


    @Before
    public  void setUp() throws Exception {
        IDataSet productData = getDataFromFile("dataset.xml");
        beforeData = new IDataSet[] {productData};
        super.setUp();

        System.setProperty("webdriver.chrome.driver", "/home/lumberjack85/Desktop/oms_git/Ch-031.AQC/OMS_test/chromedriver");
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @Test
    public  void testEditOrderStatus() throws javax.script.ScriptException{

        LoginPage lp = new LoginPage(driver);
        UserInfoPage ui = lp.login(LOGIN, PASS);
        OrderPage op = ui.goToOrderingTab();
        EditOrderPage eo = op.goTo1EditOrder();
        assertTrue("Can't edit order", eo.isAddItem() == true);
    }

    @Test
    public void testAddItem() throws Exception{
        LoginPage lp = new LoginPage(driver);
        UserInfoPage ui = lp.login(LOGIN, PASS);
        OrderPage op = ui.goToOrderingTab();
        EditOrderPage eo = op.goTo1EditOrder();
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
        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(LOGIN, PASS);
        OrderPage orderPage = userInfoPage.goToOrderingTab();
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
        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(LOGIN, PASS);
        OrderPage orderPage = userInfoPage.goToOrderingTab();
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
        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(LOGIN, PASS);
        OrderPage orderPage = userInfoPage.goToOrderingTab();
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
        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(LOGIN, PASS);
        OrderPage orderPage = userInfoPage.goToOrderingTab();
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
        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(LOGIN, PASS);
        OrderPage orderPage = userInfoPage.goToOrderingTab();
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
        driver.quit();
    }

}
