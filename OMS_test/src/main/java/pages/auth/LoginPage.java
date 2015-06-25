package pages.auth;

import org.openqa.selenium.WebDriver;

import pages.BasePage;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public UserInfoPage login(String userName, String password) {
    	return super.login(userName, password);
    }

    public UserInfoPage logout() {
        return super.logout();
    }
}
