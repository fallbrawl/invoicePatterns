package invoice;

/**
 * Created by paul on 18.04.16.
 */

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;


public class OverpayTest extends BasicTestCase {

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
    public void overpayTest() throws InterruptedException, NoSuchFieldException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        firstPage.open();
        firstPage.enterNames();

        secondPage = firstPage.toTheNextStep();

        secondPage.uploadFile();

        secondPage.setTypeOfAgreementService("Товарный");

        secondPage.setAgreementDelay("10");
        secondPage.setBestBefore();

        secondPage.agreement("Save");
        secondPage.waitForLoad();
        secondPage.agreement("Use");
        secondPage.waitForLoad();

        thirdPage = secondPage.toTheNextStep();

        thirdPage.initPage();

        productsPage.open();
        productsPage.addNewItem();
        productsPage.fillNewItem("Товар");

        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());

        thirdPage = secondPage.toTheNextStep();
        thirdPage.initPage();

        thirdPage.enterExistingNameOfProduct();
        thirdPage.addProduct();
        thirdPage.save();
        thirdPage.waitForLoad();

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
        sixthPage.setTypeOfAgreement("Товарный");
        sixthPage.setAgreementDelay("10");
        sixthPage.setBestBefore();
        sixthPage.agreement("Save");
        sixthPage.waitForLoad();
        sixthPage.agreement("Use");
        sixthPage.waitForLoad();

        seventhPage = sixthPage.toTheNextStep();
        seventhPage.waitForLoad();

        seventhPage.saveAndDelivery();
        seventhPage.fillDeliveryForm();
        seventhPage.uploadFile2();
        seventhPage.confirmPayment();
        seventhPage.waitForLoad();
        seventhPage.initPage();

        eighthPage.waitForLoad();
        eighthPage.setCalendar2FirstDay();
        eighthPage.uploadNaklad();
        eighthPage.setNumber4();
        eighthPage.setCalendar2_2FirstDay();
        eighthPage.setNumber2();
        eighthPage.uploadInvoice();

        ninethPage = eighthPage.acceptOrder();

        sellersPage.open();
        sellersPage.openBalance();
        sellersPage.switchToBuy();

        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());

        ninethPage = eighthPage.acceptOrder();
        ninethPage.partialShipment();

        sellersPage.open();
        sellersPage.openBalance();

        sellersPage.makeAPay();

        sellersPage.payForItems("200");
        sellersPage.uploadFile();
        sellersPage.confirmPayment();

        sellersPage.makeAReturn();
        sellersPage.payForItems("100");
        sellersPage.confirmPayment();

        sellersPage.makeAPay();
        sellersPage.payForItems("400");
        sellersPage.confirmPayment();

        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());

        ninethPage.partialShipment();

        sellersPage.open();
        sellersPage.openBalance();



    }
}
