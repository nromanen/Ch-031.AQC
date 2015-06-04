package TestSupervisor;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.Browser;
import tools.ColoredString;
import tools.DBUnitConfig;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class AddProductTest extends DBUnitConfig {
    static WebDriver driver;
    Browser browser;
    String supervisorLogin = "User3";
    String supervisorPassword = "pass";
   // ItemManagementPage itemManagementPage;

    public AddProductTest(String name) {
        super(name);
    }
    static {driver = new FirefoxDriver();}
    @Before
    public void setUp() throws Exception {
        beforeData = new IDataSet[] {initialData, userData};
        super.setUp();
        browser = new Browser(driver);
        browser.goToUrl("http://localhost:8080/OMS/login.htm");
        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(supervisorLogin, supervisorPassword);
        ItemManagementPage itemManagementPage = userInfoPage.selectItemManagementTab();

    }
    @Test
    public void testAddProducts() throws Exception {
        AddProductPage addProductPage = new ItemManagementPage(driver).goToAddProduct();
        addProductPage.setProductNameValue("Test Product");
        addProductPage.setProductDescriptionValue("Function test");
        addProductPage.setProductPriceValue("65");
        addProductPage.clickOkButton();
        assertNotNull(new ItemManagementPage(driver));

        // Fetch database data after executing your code
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("products");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/main/resources/expectedDataProduct.xml"));
        ITable expectedTable = expectedDataSet.getTable("products");

        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
        expectedTable.getTableMetaData().getColumns());

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, filteredTable);
    }

    @After
    public void tearDown() throws Exception {
        browser.findElementById("logout").click();
        browser.alertAccept();
        driver.quit();
        //super.tearDown();
        DatabaseOperation.DELETE_ALL.execute(getConnection(), getConnection().createDataSet());

    }
    }
