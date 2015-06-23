package TestCustomer;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.Navigation;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by lumberjack85 on 6/21/15.
 */
public class InitDataClicker {
    private static WebDriver driver;
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";
    String LOGIN = "supervisor1";
    String PASS = "qwerty";
    private Navigation navigation;


    public  void initProduct(){
        System.setProperty("webdriver.chrome.driver", "/home/lumberjack85/IdeaProjects/Ch-031.AQC/OMS_test/chromedriver");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        navigation = new Navigation(driver);
        LoginPage loginPage = new LoginPage(driver);
        navigation.goToUrl(HOME_PAGE);
        UserInfoPage userInfoPage = navigation.login(LOGIN, PASS);
        ItemManagementPage itemManagementPage = userInfoPage.selectItemManagementTab();

        AddProductPage addProductPage = new ItemManagementPage(driver).goToAddProduct();
        addProductPage.setProductNameValue("product1");
        addProductPage.setProductDescriptionValue("product description");
        addProductPage.setProductPriceValue("100");
        addProductPage.clickOkButton();
        driver.quit();

    }

    @Test
    public void initTest() throws Exception{
        InitDataClicker init = new InitDataClicker();
        init.initProduct();

        Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/oms2", "root", "root");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        System.out.println("connected");

        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("Products");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("/data/partial.xml"));
        System.out.println("created");
    }


}
