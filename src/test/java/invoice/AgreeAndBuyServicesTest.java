package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class AgreeAndBuyServicesTest extends BasicTestCase {

    // 3. Договор на услуги, добавляем услуги, закупаем услуги,
    // отгружаем, проверяем документы отгрузочные
    // http://screencast.com/t/bbRa3eVyM75 (Вторая часть)

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);
    private AddDocumentNinethPage ninethPage = PageFactory.initElements(getWebDriver(), AddDocumentNinethPage.class);
    private AddDocumentFourthPage fourthPage = PageFactory.initElements(getWebDriver(), AddDocumentFourthPage.class);
    private AddDocumentFifthPage fifthPage = PageFactory.initElements(getWebDriver(), AddDocumentFifthPage.class);
    private AddDocumentSixthPage sixthPage = PageFactory.initElements(getWebDriver(), AddDocumentSixthPage.class);
    private AddDocumentSeventhPage seventhPage = PageFactory.initElements(getWebDriver(), AddDocumentSeventhPage.class);
    private AddDocumentEighthPage eighthPage = PageFactory.initElements(getWebDriver(), AddDocumentEighthPage.class);
    private ProductsPage productsPage = PageFactory.initElements(getWebDriver(), ProductsPage.class);

    private SellersPage sellersPage = PageFactory.initElements(getWebDriver(), SellersPage.class);

    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @Test
    public void agreeAndBuyService() throws NoSuchFieldException, InterruptedException {

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

        sellersPage.initPage();

        sellersPage.open();
        sellersPage.openCreatedSeller();
        sellersPage.changeData();
        sellersPage.setBalance();
        sellersPage.save();

        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());


        thirdPage = secondPage.toTheNextStep();

        thirdPage.initPage();

        productsPage.open();
        productsPage.addNewItem();
        productsPage.fillNewItem("Услуга");

        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());

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
        fifthPage.initPurchase();
        fifthPage.waitForLoad();
        sixthPage = fifthPage.formPurchase();

        sixthPage.uploadFile();
        sixthPage.setTypeOfAgreement("Услуги");
        sixthPage.agreement("Save");
        sixthPage.waitForLoad();
        sixthPage.agreement("Use");
        sixthPage.waitForLoad();

        seventhPage = sixthPage.toTheNextStep();
        seventhPage.waitForLoad();
        seventhPage.openPayWindow();
//        seventhPage.setValuesPayWindow();
//        seventhPage.uploadFile1();
//        seventhPage.confirmPayment();
//        seventhPage.waitForLoad();
//        seventhPage.initPage();
//        seventhPage.saveAndDelivery();
        seventhPage.fillDeliveryForm();
        seventhPage.uploadFile2();
        seventhPage.confirmPayment();
        seventhPage.initPage();
        UtilStore.reload(getWebDriver());

        dynamicPayments.open();
        dynamicPayments.openPayWindow();
        dynamicPayments.setValuesPayWindow();
        dynamicPayments.uploadFile();
        dynamicPayments.confirmPayment();
        dynamicPayments.waitForLoad();
        dynamicPayments.initPage();
        UtilStore.goBack(getWebDriver());

        seventhPage.initPage();
        UtilStore.reload(getWebDriver());
        seventhPage.openPayWindow();
        seventhPage.fillDeliveryForm();
        seventhPage.uploadFile2();
        seventhPage.confirmPayment();
        seventhPage.waitForLoad();
        seventhPage.initPage();       // eighthPage = seventhPage.confirmDeliveryForm();
        seventhPage.saveAndDelivery();
        seventhPage.confirmPayment();

        eighthPage.waitForLoad();
        eighthPage.setCalendar2FirstDay();
        eighthPage.uploadAct();
        eighthPage.setNumber2();
        eighthPage.setCalendar3FirstDay();
        eighthPage.setNumber3();
        eighthPage.uploadInvoice();

        ninethPage = eighthPage.acceptOrder();
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
