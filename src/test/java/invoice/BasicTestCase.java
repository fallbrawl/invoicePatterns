package invoice;

import com.invoice.data.UserData;
import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by NEXUS on 01.02.2016.
 */
public class BasicTestCase {

    protected static WebDriver driver;

    public UserData admin = new UserData("pavel.a@attractgroup.com", "attract");

    protected WebDriver getWebDriver() {
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("imp_wait")), TimeUnit.SECONDS);
        }
        return driver;
    }

    @BeforeTest

    public void setUp() {

    }

    @AfterTest

    public void shutDown() {

        // driver.quit();
    }

}
