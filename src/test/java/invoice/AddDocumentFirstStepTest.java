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
    private AddDocumentThirdPage thirdPage;


    //Initializing objects via PageFactory
    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private ManagersPage managersPage = PageFactory.initElements(getWebDriver(), ManagersPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);

    @Test

    public void addDocument() throws Exception{

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        firstPage.open();
        firstPage.enterNames();
        secondPage = firstPage.toTheNextStep();

        secondPage.uploadFile();
        secondPage.extractNumber();
        secondPage.agreement("Save");

        secondPage.waitForLoad();
        secondPage.agreement("Send");

        managersPage.open();
        managersPage.checkAndSave();
        UtilStore.goBack(getWebDriver());

        secondPage.waitForLoad();
        secondPage.agreement("Use");
        secondPage.waitForLoad();
        thirdPage = secondPage.toTheNextStep();

        secondPage.waitForLoad();
        thirdPage.enterNameOfProduct();
        thirdPage.addProduct();
        thirdPage.waitForLoad();
        thirdPage.waitForLoad();
    //  thirdPage.saveProduct();

        dynamicPayments.open();
        dynamicPayments.getValues();



//        assertTrue(mainPage.isLoggedIn());

    }
}
