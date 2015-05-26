package softserve.mvn_oms;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tools.Browser;

public class CustomerOrderingPage {

	private WebDriver driver;
	private Browser browser; 

	private static final String LINK_FOR_CREATING_NEW_ORDER = "Create new order"; 
	
	public CustomerOrderingPage(WebDriver driver) {
		this.driver = driver;
		browser = new Browser(driver);
	}

	public WebDriver getDriver() {
		return this.driver;
	}
	
	public Browser getBrowser(){
		return this.browser;
	}
	
	public CustomerCreateOrderPage switchToCreatingNewOrderPage() {
		
		Browser browser = new Browser(driver); 
		browser.findElementByLinkText(LINK_FOR_CREATING_NEW_ORDER).click();
		
		return new CustomerCreateOrderPage(driver);
	}
	
	public List<String> getHeadersFromTableWithOrders() {
		
		List<String> names = new ArrayList<String>();
		List<WebElement> headers = browser.findElementById("list").findElements(By.tagName("th"));
		for (WebElement header : headers) {
			names.add(header.getText());
		}
		return names;
	}
	
}
