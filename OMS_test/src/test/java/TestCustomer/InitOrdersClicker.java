package TestCustomer;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.auth.UserInfoPage;
import pages.ordering.*;
import tools.DBUnitConfig;
import tools.Navigation;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *  Used for creating an initial product, orders and orderItems in xml
 */
public class InitOrdersClicker extends DBUnitConfig {
	private static WebDriver driver;
	private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";
	private static final String USER_NAME_FOR_SUPERVISOR = "supervisor1";
	private static final String USER_NAME_FOR_CUSTOMER = "customer1";
	private static final String PASSWORD = "qwerty";
	private static final String SELECTED_ASSIGNEE = "merch1";
	private static final String ENTERED_PREFERABLE_DELIVERY_DATE = "10/07/2015";
	private Navigation navigation;
	CustomerOrderingPage ordering;

	public InitOrdersClicker(String name) {
		super(name);
	}

	public void initProductAndOrders() throws Exception {
		
		driver = new FirefoxDriver();    		

		navigation = new Navigation(driver);
		navigation.goToUrl(HOME_PAGE);
		UserInfoPage userInfoPage = navigation.login(USER_NAME_FOR_SUPERVISOR, PASSWORD);
		userInfoPage.selectItemManagementTab();

		AddProductPage addProductPage = new ItemManagementPage(driver)
				.goToAddProduct();
		addProductPage.setProductNameValue("product1");
		addProductPage.setProductDescriptionValue("product description");
		addProductPage.setProductPriceValue("100");
		addProductPage.clickOkButton();
		navigation.logout();

		navigation = new Navigation(driver);
		navigation.goToUrl(HOME_PAGE);
		UserInfoPage customerInfoPage = navigation.login(USER_NAME_FOR_CUSTOMER, PASSWORD);
		ordering = customerInfoPage.switchToOrderingPage();

		int count = 11;

		CustomerAddProductsToOrderPage addProductsPage;
		CustomerCreateOrderPage createNewOrderPage;

		for (int i = 1; i <= count; i++) {
			createNewOrderPage = ordering.switchToCreatingNewOrderPage();
			addProductsPage = createNewOrderPage.clickAddItemButton();
			addProductsPage.selectInitProduct();
			addProductsPage.clickDoneButton();
			createNewOrderPage.enterPreferableDeliveryDate(ENTERED_PREFERABLE_DELIVERY_DATE);
			createNewOrderPage.selectAssignee(SELECTED_ASSIGNEE);
			createNewOrderPage.clickSaveButton();
			createNewOrderPage.switchToOrderingPage();
		}

		try {
			
			 Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
		        Connection jdbcConnection = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/oms2", "root", "root");			
			
			IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

	        QueryDataSet partialDataSet = new QueryDataSet(connection);
	        partialDataSet.addTable("Products");
	        partialDataSet.addTable("Orders");
	        partialDataSet.addTable("OrderItems");
	        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/main/resources/data/initOrders.xml"));	
	        
	        QueryDataSet partialDataSet2 = new QueryDataSet(connection);
	        partialDataSet2.addTable("Products");
	        FlatXmlDataSet.write(partialDataSet2, new FileOutputStream("src/main/resources/data/initProduct.xml"));	
		}
	        catch (Exception e) {
			System.out.println(e.getMessage());
		}

		finally {
			System.out.println("File with data was created!");
		}

		driver.quit();
	}

	public static void main(String[] args) throws Exception {

		InitOrdersClicker initOrdersClicker = new InitOrdersClicker("");
		initOrdersClicker.initProductAndOrders();
	}

}
