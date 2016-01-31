package invoice;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * Created by NEXUS on 31.01.2016.
 */
public class InvoiceTestSuite {
    private WebDriver driver;

    @BeforeTest

    public void setUp() {

        driver = new FirefoxDriver();
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
