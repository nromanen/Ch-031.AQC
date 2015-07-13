package tools;

import com.cloudinary.Cloudinary;
import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Olya
 * 
 */
public class ScreenShotUtils extends TestWatcher {
	private WebDriver driver;
    private static Cloudinary cloudinary;
	private static final String SCREEN_SHOT_CATALOG = "logs/";
    private static final String CLOUD_NAME_VALUE = PropertiesProvider.getProperty("cloudinary.cloud.name");
    private static final String API_SECRET_VALUE = PropertiesProvider.getProperty("cloudinary.api.secret");
    private static final String API_KEY_VALUE = PropertiesProvider.getProperty("cloudinary.api.key");
    private static final String CLOUD_NAME = "cloud_name";
    private static final String API_SECRET = "api_secret";
    private static final String API_KEY = "api_key";

    static {
		cloudinary = new Cloudinary(new HashMap<String, Object>() {
			{
				put(CLOUD_NAME, CLOUD_NAME_VALUE);
				put(API_SECRET, API_SECRET_VALUE);
				put(API_KEY, API_KEY_VALUE);
			}
		});
	}
	private static Logger LOG = LoggerFactory.getLogger(ScreenShotUtils.class);

    public ScreenShotUtils(WebDriver driver) {
		this.driver = driver;
	}

	private void screenShot(String fileName) throws IOException {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(fileName));
	}

	@Override
	protected void failed(Throwable e, Description description) {
		// Browser browser = new Browser(driver);
		StringBuilder linkName = new StringBuilder(SCREEN_SHOT_CATALOG);
		linkName.append(description.getClassName()).append("_")
				.append(description.getMethodName());
		StringBuilder fileNameBuilder = new StringBuilder();
		fileNameBuilder.append(linkName).append(".png");
		try {
			screenShot(fileNameBuilder.toString());
			File file = new File(fileNameBuilder.toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("public_id", fileNameBuilder.toString());
			Map uploadResult = cloudinary.uploader().upload(file, map);
			LOG.info("SCREENSHOT: uploaded to " + uploadResult.get("url"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	protected void starting(Description description) {
		super.starting(description);
		LOG.info("------" + description.getMethodName() + "------");
	}

	@Override
	protected void finished(Description description) {
		super.finished(description);
		LOG.info("------" + description.getMethodName() + " finished! ------");
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
