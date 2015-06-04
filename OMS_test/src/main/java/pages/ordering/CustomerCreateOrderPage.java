package pages.ordering;

import org.openqa.selenium.WebDriver;

import pages.ordering.CustomerAddProductsToOrderPage;
import tools.Browser;

public class CustomerCreateOrderPage {

	private WebDriver driver;
	private Browser browser;
		
	public CustomerCreateOrderPage(WebDriver driver) {
		this.driver = driver;
		browser = new Browser(driver); 
	}

	public WebDriver getDriver() {
		return this.driver;
	}
	
	public Browser getBrowser(){
		return this.browser;
	}
	
	public CustomerAddProductsToOrderPage clickAddItemButton() {
		
		browser.findElementByXpath("//input[@type='submit'][@value = 'Add Item']").click(); 
		
		return new CustomerAddProductsToOrderPage(driver);
	}
}
