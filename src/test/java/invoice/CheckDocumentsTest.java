package invoice;

/**
 * Created by paul on 24.02.16.
 */

import org.testng.annotations.Test;
import com.invoice.pages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckDocumentsTest extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);
    private AddDocumentFourthPage fourthPage = PageFactory.initElements(getWebDriver(), AddDocumentFourthPage.class);
    private AddDocumentFifthPage fifthPage = PageFactory.initElements(getWebDriver(), AddDocumentFifthPage.class);


    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @Test

    public void checkDocumentTest() throws NoSuchFieldException, InterruptedException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        firstPage.open();
        firstPage.enterNames();

        secondPage = firstPage.toTheNextStep();
        secondPage.uploadFile();
        secondPage.agreement("Save");
        secondPage.waitForLoad();
        secondPage.agreement("Use");
        secondPage.waitForLoad();

        thirdPage = secondPage.toTheNextStep();
        thirdPage.initPage();
        thirdPage.enterExistingNameOfProduct();
        thirdPage.addProduct();
        thirdPage.save();
        thirdPage.waitForLoad();

        dynamicPayments.open();
        dynamicPayments.openPayWindow();
        dynamicPayments.setValuesPayWindow();
        dynamicPayments.uploadFile();
        dynamicPayments.confirmPayment();
        dynamicPayments.waitForLoad();
        dynamicPayments.initPage();
        UtilStore.goBack(getWebDriver());

        thirdPage.waitForLoad();
        thirdPage.save();
        thirdPage.waitForLoad();
        fourthPage = thirdPage.saveAndInitiate();

        fourthPage.waitForLoad();
        fourthPage.initPage();
        fifthPage = fourthPage.buyForAll();




    }
}
