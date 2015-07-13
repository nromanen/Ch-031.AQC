package tools;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.concurrent.TimeUnit;

public class BaseTest {

	private DBUnitConfig dbUnitConfig;
	private static final String BASEURL = PropertiesProvider.getProperty("base.url");
	private static final int TIMEOUT = Integer.parseInt(PropertiesProvider.getProperty("time.out"));
	protected static WebDriver driver;
	protected BasePage basePage;
	protected static EntityManager em = Persistence.createEntityManagerFactory(
			"persistence").createEntityManager();
    private static String webdriverName = PropertiesProvider.getProperty("webdriver.name");

	@BeforeClass
	public static void beforeClassSetUp() {
		driver = WebDriverFactory.getDriver(webdriverName);
	}

	@Before
	public void setUp() {
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.get(BASEURL);
		basePage = new BasePage(driver);

	}

	public void initDataBase(String... dataFile) {
		dbUnitConfig = new DBUnitConfig();
		dbUnitConfig.initDataBase(dataFile);
	}

	public DBUnitConfig getDbUnitConfig() {
		return dbUnitConfig;
	}

	public void setDbUnitConfig(DBUnitConfig dbUnitConfig) {
		this.dbUnitConfig = dbUnitConfig;
	}

	@Rule
	public ScreenShotUtils screenShootRule = new ScreenShotUtils(driver) {
		@Override
		protected void finished(Description description) {
			super.finished(description);
			basePage.logout();
		}
	};

	public void cleanDataBase() {
		getDbUnitConfig().tearDown();

	}

	@AfterClass
	public static void afterClassTearDown() {
		driver.quit();
	}
}
