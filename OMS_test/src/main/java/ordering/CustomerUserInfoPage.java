package softserve.mvn_oms;

import org.openqa.selenium.WebDriver;

import tools.Browser;

public class CustomerUserInfoPage {
	
	private  WebDriver driver;
	private Browser browser;
	private static final String LINK_FOR_ORDERING = "Ordering";  
	
	public CustomerUserInfoPage(WebDriver driver) {
		this.driver = driver;
		browser = new Browser(driver);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public Browser getBrowser() {
		return this.browser;
	}
		
	public CustomerOrderingPage switchToOrderingPage() {
		
		Browser browser = new Browser(driver); 
		browser.findElementByLinkText(LINK_FOR_ORDERING).click();
		return new CustomerOrderingPage(driver);
	}


}
