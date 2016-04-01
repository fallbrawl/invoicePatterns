package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Created by paul on 29.02.16.
 */
public class AgreeLicenseTest extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);
    private AddDocumentNinethPage ninethPage = PageFactory.initElements(getWebDriver(), AddDocumentNinethPage.class);
    private AddDocumentFourthPage fourthPage = PageFactory.initElements(getWebDriver(), AddDocumentFourthPage.class);
    private AddDocumentFifthPage fifthPage = PageFactory.initElements(getWebDriver(), AddDocumentFifthPage.class);
    private AddDocumentSixthPage sixthPage = PageFactory.initElements(getWebDriver(), AddDocumentSixthPage.class);
    private AddDocumentSeventhPage seventhPage = PageFactory.initElements(getWebDriver(), AddDocumentSeventhPage.class);
    private AddDocumentEighthPage eighthPage = PageFactory.initElements(getWebDriver(), AddDocumentEighthPage.class);

    private SellersPage sellersPage = PageFactory.initElements(getWebDriver(), SellersPage.class);

    private MainPage mainPage = PageFactory.initElements(getWebDriver(), MainPage.class);
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;


    @Test

    public void agreeLicenseTest() throws InterruptedException, NoSuchFieldException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        firstPage.open();
        firstPage.enterNames();

        secondPage = firstPage.toTheNextStep();
        secondPage.waitForLoad();
        secondPage.uploadFile();
        secondPage.setTypeOfAgreementService("Лицензионный");
        secondPage.setBestBefore();
        secondPage.setAgreementDelay("10");
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
        thirdPage.setNumberOfItems();
        thirdPage.addProduct();
        thirdPage.fillProductForm();
        thirdPage.waitForLoad();
        thirdPage.save();
        thirdPage.waitForLoad();

//        dynamicPayments.open();
//        dynamicPayments.openPayWindow();
//        dynamicPayments.setValuesPayWindow();
//        dynamicPayments.uploadFile();
//        dynamicPayments.confirmPayment();
//        dynamicPayments.waitForLoad(); //если убрать отсрочку, то понадобится
//        dynamicPayments.initPage();
//        UtilStore.goBack(getWebDriver());
//        dynamicPayments.waitForLoad();


        UtilStore.reload(getWebDriver());
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
        sixthPage.setTypeOfAgreement("Лицензионный");
        sixthPage.setAgreementDelay("10");
        sixthPage.setBestBefore();
        sixthPage.agreement("Save");
        sixthPage.waitForLoad();
        sixthPage.agreement("Use");
        sixthPage.waitForLoad();

        seventhPage = sixthPage.toTheNextStep();
        seventhPage.waitForLoad();
//        seventhPage.openPayWindow(); // TODO: тут надо переделать под договор с отсрочкой, и лишнее исчезнет
//        seventhPage.setValuesPayWindow();
//        seventhPage.uploadFile1();
//        seventhPage.confirmPayment();
//        seventhPage.waitForLoad();
//        seventhPage.initPage();
//        seventhPage.saveAndDelivery();
//        seventhPage.fillDeliveryForm();
//        seventhPage.uploadFile2();
//        seventhPage.confirmPayment();
//        seventhPage.initPage();
//        UtilStore.reload(getWebDriver());
//
//        dynamicPayments.open();
//        dynamicPayments.openPayWindow();
//        dynamicPayments.setValuesPayWindow();
//        dynamicPayments.uploadFile();
//        dynamicPayments.confirmPayment();
//        dynamicPayments.waitForLoad();
//        dynamicPayments.initPage();
//        UtilStore.goBack(getWebDriver());
//
//
//        UtilStore.reload(getWebDriver());
        seventhPage.initPage();
        //seventhPage.openPayWindow();
        seventhPage.saveAndDelivery();
        seventhPage.fillDeliveryForm();
        seventhPage.uploadFile2();
        seventhPage.confirmPayment();
        seventhPage.waitForLoad();
        seventhPage.initPage();
 //       seventhPage.saveAndDelivery();
//        seventhPage.openPayWindow();
//        seventhPage.fillDeliveryForm();
//        seventhPage.uploadFile2();
//        seventhPage.confirmPayment();
        seventhPage.waitForLoad();
        seventhPage.initPage();       // eighthPage = seventhPage.confirmDeliveryForm();
        //seventhPage.saveAndDelivery();
        seventhPage.confirmPayment();

        eighthPage = seventhPage.confirmDeliveryForm();
        eighthPage.waitForLoad();
        eighthPage.uploadAct();
        eighthPage.setCalendar3FirstDay();
        eighthPage.setNumber3();

        ninethPage = eighthPage.acceptOrder();
        ninethPage.waitForLoad();
        ninethPage.waitForLoad();
        ninethPage.setFullLoad();
        ninethPage.waitForLoad();
        ninethPage.fullShipmentForLicenseAgreement();
        ninethPage.waitForLoad();
        ninethPage.checkDocs("Bill");
        ninethPage.initPage();
        ninethPage.checkDocs("Act");
        ninethPage.initPage();

    }
}
