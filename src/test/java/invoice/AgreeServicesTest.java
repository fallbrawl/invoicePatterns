package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.AddDocumentFirstPage;
import com.invoice.pages.AddDocumentPages.AddDocumentNinethPage;
import com.invoice.pages.AddDocumentPages.AddDocumentSecondPage;
import com.invoice.pages.AddDocumentPages.AddDocumentThirdPage;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Created by paul on 26.02.16.
 */
public class AgreeServicesTest extends BasicTestCase {

//    http://screencast.com/t/zgMA3uFC0Bn
//    Договор на услуги, добавляем услуги,
//    устанавливаем галочку "Услугу указываем самостоятельно",
//    совершаем отгрузку (там сейчас ошибка 500, ребята правят),
//    проверяем отгрузочные документы (счет,акт, счет-фактура)

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);
    private AddDocumentNinethPage ninethPage = PageFactory.initElements(getWebDriver(), AddDocumentNinethPage.class);

    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @Test(enabled = false)

    public void agreeServicesTest() throws NoSuchFieldException, InterruptedException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        firstPage.open();
        firstPage.enterNames();

        secondPage = firstPage.toTheNextStep();
        secondPage.uploadFile();
        secondPage.setTypeOfAgreementService("Услуги");
        secondPage.agreement("Save");
        secondPage.waitForLoad();
        secondPage.agreement("Use");
        secondPage.waitForLoad();

        thirdPage = secondPage.toTheNextStep();
        thirdPage.waitForLoad();
        thirdPage.initPage();
        thirdPage.setNumberOfItems();

        thirdPage.addProduct();
        thirdPage.fillProductForm();
        thirdPage.waitForLoad();
        thirdPage.setOfferService();
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
        UtilStore.reload(driver);

//        thirdPage.saveAndLoad();
        thirdPage.saveAndInitiate();
        ninethPage.waitForLoad();
        ninethPage.waitForLoad();
        ninethPage.setFullLoad();
        ninethPage.waitForLoad();
        ninethPage.fullShipment();
        ninethPage.waitForLoad();
        ninethPage.checkDocs("Bill");
        ninethPage.initPage();
        ninethPage.checkDocs("Act");
        ninethPage.initPage();
        ninethPage.checkDocs("BillFacture");
    }
}
