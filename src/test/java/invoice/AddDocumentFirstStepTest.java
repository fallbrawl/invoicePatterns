package invoice;

import com.invoice.pages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by paul on 03.02.16.
 */
public class AddDocumentFirstStepTest extends BasicTestCase {
    //Creating objects

    private AddDocumentSecondPage secondPage;
    private MainPage mainPage;


    //Initializing objects via PageFactory
    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private ManagersPage managersPage = PageFactory.initElements(getWebDriver(), ManagersPage.class);

    @Test
    public void addDocument() throws Exception{

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        firstPage.open();
        firstPage.enterNames();
        secondPage = firstPage.toTheNextStep();

        secondPage.uploadFile();
        secondPage.extractNumber();
        secondPage.saveAgreement();
        secondPage.sendAgreement();

        managersPage.open();
        managersPage.checkAndSave();
        UtilStore.goBack(getWebDriver());



//        assertTrue(mainPage.isLoggedIn());

    }
}
