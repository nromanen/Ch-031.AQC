package pages.ordering;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tools.Browser;

public class CustomerAddProductsToOrderPage {

	private WebDriver driver;
	private Browser browser;
	
	
	public CustomerAddProductsToOrderPage(WebDriver driver) {
		this.driver = driver;
		browser = new Browser(driver);
	}

	public WebDriver getDriver() {
		return this.driver;
	}
	
	public Browser getBrowser(){
		return this.browser;
	}
	
    public List<String> getHeadersFromTableWithProducts() { 
		
		List<String> names = new ArrayList<String>();
		List<WebElement> headers = browser.findElementById("list").findElements(By.tagName("th"));
		for (WebElement header : headers) {
			names.add(header.getText());
		}
		return names;
	}
	
    public void selectProduct() { 
		
    	browser.findElementByCssSelector("#selectFrom1 > a").click();
	}
       

}
