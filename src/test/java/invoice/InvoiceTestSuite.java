package invoice;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

/**
 * Created by NEXUS on 31.01.2016.
 */
public class InvoiceTestSuite {
    private WebDriver driver;

    @BeforeTest

    public void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        caps.setCapability("screen-resolution", "1280x720");
        //caps.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        //caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\Users\\USER\\Downloads\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");

        ArrayList<String> cliArgsCap = new ArrayList<String>();
        cliArgsCap.add("--webdriver-loglevel=NONE");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);

        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://invoicedev.php.attractgroup.com/public/login");

    }

    @AfterTest

    public void shutDown() {
        
        driver.quit();
    }

    @Test

    public void login() {
        LoginPage login = new LoginPage(driver);
        login.email("pavel.a@attractgroup.com");
        login.password("attract");
        MainPage main = login.enter();
        assertTrue(main.title().equals("Заказы на продажу"));

    }


}
