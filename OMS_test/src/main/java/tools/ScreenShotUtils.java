package tools;

import com.cloudinary.Cloudinary;
import org.apache.commons.lang.ObjectUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

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
    private static final String SCREEN_SHOT_CATALOG = "logs/";
    static Logger LOG = LoggerFactory.getLogger(ScreenShotUtils.class);
    Map<String, Object> cloudinaryParams = new HashMap<String, Object>(){{
        put("cloud_name", "ch-031");
        put("api_secret", "U3CRczVh1BN1OZpzircEL5fMNhM");
        put("api_key", "681724576471196");
    }};
    Cloudinary cloudinary = new Cloudinary(cloudinaryParams);



    public ScreenShotUtils() {
    }

    @Override
    protected void failed(Throwable e, Description description) {
        Browser browser = new Browser(driver);
        StringBuilder linkName = new StringBuilder(SCREEN_SHOT_CATALOG);
        linkName.append(description.getClassName()).append("_").append(description.getMethodName());
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(linkName).append(".png");
        try {
            browser.screenShot(fileNameBuilder.toString());
            File file = new File(fileNameBuilder.toString());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("public_id", fileNameBuilder.toString());
            Map uploadResult = cloudinary.uploader().upload(file,map);
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
        LOG.info("------" + description.getMethodName() + " pass! ------");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}

