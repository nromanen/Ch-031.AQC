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
import tools.*;


import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AddProductTest extends DBUnitConfig {
    private WebDriver driver;
    private Navigation navigation;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "User3";
    private static final String SUPERVISOR_PASSWORD = "pass";
    private static final String PRODUCT_NAME = "Test Product";
    private static final String PRODUCT_DESCRIPTION = "Function test";
    private static final String PRODUCT_PRICE = "65";
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";

    public AddProductTest(String name) {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        navigation = new Navigation(driver);
        LoginPage loginPage = new LoginPage(driver);
        navigation.goToUrl(HOME_PAGE);
        UserInfoPage userInfoPage = navigation.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        ItemManagementPage itemManagementPage = userInfoPage.selectItemManagementTab();
    }

    @Test
    public void testAddProducts() throws Exception {
        AddProductPage addProductPage = new ItemManagementPage(driver).goToAddProduct();
        addProductPage.setProductNameValue(PRODUCT_NAME);
        addProductPage.setProductDescriptionValue(PRODUCT_DESCRIPTION);
        addProductPage.setProductPriceValue(PRODUCT_PRICE);
        addProductPage.clickOkButton();
        ItemManagementPage itemManagementPage = new ItemManagementPage(driver);
        TableRow row = itemManagementPage.findProductByNameInTable(PRODUCT_NAME);
        assertEquals(PRODUCT_DESCRIPTION, row.getNthColumnValue(2));
        assertEquals(PRODUCT_PRICE, row.getNthColumnValue(3));
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
        navigation.logout();
        //DatabaseOperation.DELETE_ALL.execute(getConnection(), getConnection().createDataSet());
    }
}

