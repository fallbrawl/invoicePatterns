package invoice;

import com.invoice.pages.LoginPage;
import com.invoice.pages.MainPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by NEXUS on 01.02.2016.
 */
public class LoginTest extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private MainPage mainPage;

    @Test
    public void testLogin() throws Exception {

            loginPage.open();
            mainPage = loginPage.loginAs(admin);
            assertTrue(mainPage.isLoggedIn());

    }
}
