package pages.ordering;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class MerchandiserOrderingPage extends BasePage {
	
	public MerchandiserOrderingPage(WebDriver driver) {
		super(driver);
		
	}
	
	private static String orderingTabXPathLocator = "//*[@id=\"nav\"]/li[1]/a";
	private static String showItemsXPathLocator = "//*[@id=\"searchFilter\"]/p/a";
	private static String pageCountLinkXPathLocator = "//*[@id=\"pageCount\"]";
	
	
	public void findOrderingTabAndClick(){
		browser.findElementByXpath(orderingTabXPathLocator).click();		
	}
	
	public String finnShow10ItemsLinkByXpathAndGetText(){
		return browser.findElementByXpath(showItemsXPathLocator).getText();
	}
	
	public void findShowItemsLinkByXPathAndClick(){
		browser.findElementByXpath(showItemsXPathLocator).click();
	}
	
	public String findPageCountLinkByXPathLocatorAndGetText(){
		return browser.findElementByXpath(pageCountLinkXPathLocator).getText();
	}
	
}
