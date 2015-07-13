package tools;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olya on 12.07.2015.
 */
public class PropertiesProvider {
    private static Properties prop;

    static {
        prop = new Properties();
        try {
            prop.load(Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("db.config.properties"));
            prop.load(Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }


}