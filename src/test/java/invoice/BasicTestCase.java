package invoice;

import com.invoice.data.UserData;
import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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

            DesiredCapabilities caps = DesiredCapabilities.firefox();
            caps.setJavascriptEnabled(true);

            //caps.setCapability("takesScreenshot", true);
            //caps.setCapability("screen-resolution", "1280x1024");
            //caps.setCapability("screen-resolution", "640x480");
            //caps.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
            //caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\Users\\USER\\Downloads\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");

//            ArrayList<String> cliArgsCap = new ArrayList<String>();
//            cliArgsCap.add("--webdriver-loglevel=NONE");
//            caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);

            //System.setProperty("webdriver.chrome.driver", "/home/paul/Downloads/chromedriver");

            //Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);

            driver = new FirefoxDriver(caps);
            driver.manage().window().setSize(new Dimension(1920, 1080));
            //driver = new PhantomJSDriver(caps);

            //driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("imp_wait")), TimeUnit.SECONDS);
        }
        return driver;
    }

    @BeforeMethod

    protected void testName() {
        System.out.println("Test name is: "  + this.getClass().getSimpleName());
    }

    public void setUp() {
//        driver.manage().window().maximize();
    }

    @AfterClass
    public void shutDown(){
//        driver.close();

    }

    @AfterTest

    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String osVersion = System.getProperty("os.name");
            System.out.println(osVersion);

            if (osVersion.contains("Linux")) {
                String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/Screenshot.jpg");
                System.out.println(pathLinux);
                FileUtils.copyFile(scrFile, new File(pathLinux));

            } else {
                String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\Screenshot.jpg");
                System.out.println(pathWindows);
                FileUtils.copyFile(scrFile, new File(pathWindows));

            }
        }
    }




}
