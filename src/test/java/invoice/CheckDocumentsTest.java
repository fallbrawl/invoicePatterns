package invoice;

/**
 * Created by paul on 24.02.16.
 */

import org.testng.annotations.Test;
import com.invoice.pages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;

public class CheckDocumentsTest extends BasicTestCase {

    //1. Товарный договор, добавляем товары, проходим цепочку,
    // совершаем отгрузку - проверяем нет ли ошибок при отгрытии отгрузочных документов
    // (счет, накладная, счет-фактура)


    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);
    private AddDocumentFourthPage fourthPage = PageFactory.initElements(getWebDriver(), AddDocumentFourthPage.class);
    private AddDocumentFifthPage fifthPage = PageFactory.initElements(getWebDriver(), AddDocumentFifthPage.class);
    private AddDocumentSixthPage sixthPage = PageFactory.initElements(getWebDriver(), AddDocumentSixthPage.class);
    private AddDocumentSeventhPage seventhPage = PageFactory.initElements(getWebDriver(), AddDocumentSeventhPage.class);
    private AddDocumentEighthPage eighthPage = PageFactory.initElements(getWebDriver(), AddDocumentEighthPage.class);
    private AddDocumentNinethPage ninethPage = PageFactory.initElements(getWebDriver(), AddDocumentNinethPage.class);

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
        fifthPage.enterProviderName();

        sixthPage = fifthPage.formPurchase();
        sixthPage.uploadFile();
        sixthPage.agreement("Save");
        sixthPage.waitForLoad();
        sixthPage.agreement("Use");
        sixthPage.waitForLoad();

        seventhPage = sixthPage.toTheNextStep();
        seventhPage.waitForLoad();
        seventhPage.openPayWindow();
        seventhPage.setValuesPayWindow();
        seventhPage.uploadFile1();
        seventhPage.confirmPayment();
        seventhPage.waitForLoad();
        seventhPage.initPage();
        seventhPage.saveAndDelivery();
        seventhPage.fillDeliveryForm();
        seventhPage.uploadFile2();

        eighthPage = seventhPage.confirmDeliveryForm();
        eighthPage.waitForLoad();
        eighthPage.setCalendar1();
        eighthPage.uploadNaklad();
        eighthPage.setNumber2();
        eighthPage.setCalendar2();
        eighthPage.setNumber1();
        eighthPage.uploadInvoice();

        ninethPage = eighthPage.acceptOrder();
        //ninethPage.open();
        ninethPage.waitForLoad();
        ninethPage.waitForLoad();
        ninethPage.fullShipment(); //тут что то запорол
        ninethPage.fillFullShipmentForm();
        ninethPage.waitForLoad();
        ninethPage.checkDocs("Bill");
        ninethPage.initPage();
        ninethPage.checkDocs("Invoice");
        ninethPage.initPage();
        ninethPage.checkDocs("BillFacture");

    //    UtilStore.goBack();
       // eighthPage = seventhPage.acceptDelivery();
    }
}
