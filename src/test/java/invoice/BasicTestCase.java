package invoice;

import com.invoice.data.UserData;
import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NEXUS on 01.02.2016.
 */
public class BasicTestCase {

    protected static WebDriver driver;

    public UserData admin = new UserData("pavel.a@attractgroup.com", "attract");
    public UserData manager_buyer = new UserData("pavel.a.manager.buyer@attractgroup.com", "attract");
    public UserData manager_seller = new UserData("pavel.a.manager_sales@attractgroup.com", "attract");
    public UserData accounter = new UserData("pavel.a.accounter@attractgroup.com", "attract");

    protected WebDriver getWebDriver() {
        if (driver == null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setJavascriptEnabled(true);

            //caps.setCapability("takesScreenshot", true);
            //caps.setCapability("screen-resolution", "1280x1024");
            //caps.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
            //caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\Users\\USER\\Downloads\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");

            ArrayList<String> cliArgsCap = new ArrayList<String>();
            cliArgsCap.add("--webdriver-loglevel=NONE");
            caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
            Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);

            driver = new PhantomJSDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("imp_wait")), TimeUnit.SECONDS);
        }
        return driver;
    }

    @BeforeTest

    public void setUp() {
//        driver.manage().window().maximize();
    }

    @AfterTest

    public void shutDown() {

        // driver.quit();
    }

}
