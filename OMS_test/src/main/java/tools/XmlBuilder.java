package tools;

import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

/**
 * @author Olya
 */
public class XmlBuilder extends BaseDBTest {

    public XmlBuilder(String name) {
        super();
    }

    public static void main(String[] args) throws Exception {
        XmlBuilder xmlBuilder = new XmlBuilder("");
        xmlBuilder.saveToFile();
    }

    public void saveToFile () throws Exception {
        String productName = "Product";
        String productDescription = "Description";
        String supervisorLogin = "supervisor1";
        String supervisorPassword = "qwerty";

        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:8080/OMS/";
        BasePage basePage = new BasePage(driver);
        basePage.goToUrl(baseUrl);
        UserInfoPage userInfoPage = basePage.login(supervisorLogin, supervisorPassword);
        ItemManagementPage itemManagementPage = userInfoPage.selectItemManagementTab();

        for (int i = 1; i <= 10; i++) {
            AddProductPage addProductPage = itemManagementPage.goToAddProduct();
            addProductPage.setProductNameValue(productName + i);
            addProductPage.setProductDescriptionValue(productDescription + i);
            addProductPage.setProductPriceValue(String.format(Locale.ENGLISH, "%.2f", Math.random() * 1000));
            addProductPage.clickOkButton();
        }
        IDataSet databaseDataSet = getConnection().createDataSet();
        FilteredDataSet fds = new FilteredDataSet(new String[]{"products"}, databaseDataSet);
        File file = new File("src/main/resources/data/Products_init.xml");
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        FlatXmlDataSet.write(fds, fos);
        fos.flush();
        fos.close();
    }
}
