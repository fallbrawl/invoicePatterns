package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by paul on 07.06.16.
 */
public class EditUnloadTest extends BasicTestCase {
    //    http://screencast.com/t/tkQaSqTa
// [16:00:57] Юлия: Вот. Большой он. Суть в чем.
    // Теперь у нас есть возможность редактировать отгрузки (будет еще и удаление когда-то).
    // При измнении количества и стоимости должны меняться данные в динамике оплат и в фин показателях. Напоминаю, что:
//    Если сумма отгрузки у нас 200 р (2 ед товара), а оплачено 300р, то 200р идет как оплата по счету,
//    а еще 100р - как аванс. Авансы мы можем возвращать.
//    Если мы отгрузим еще на 100 р - авансы станут оплатами по счету.
//    Если же мы изменим количество товара в отгрузке с 2 на 1, то 100р у нас станет оплатой по счету,
//    а 200 р - авансом, который можно вернуть.

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
    private ProfilePage profilePage = PageFactory.initElements(getWebDriver(), ProfilePage.class);
    private ManagersPage managersPage = PageFactory.initElements(getWebDriver(), ManagersPage.class);
    private ArchivePage archivePage = PageFactory.initElements(getWebDriver(), ArchivePage.class);
    private ListClientPage clientPage = PageFactory.initElements(getWebDriver(), ListClientPage.class);
    private FinancialStatePage financialStatePage = PageFactory.initElements(getWebDriver(), FinancialStatePage.class);

    private SellersPage sellersPage = PageFactory.initElements(getWebDriver(), SellersPage.class);

    private MainPage mainPage = PageFactory.initElements(getWebDriver(), MainPage.class);
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @org.testng.annotations.Test
    public void editUnloadTest() throws InterruptedException, NoSuchFieldException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        firstPage.open();
        firstPage.enterNames();

        secondPage = firstPage.toTheNextStep();

        secondPage.uploadFile();
        secondPage.setTypeOfAgreementService("Услуги");

        secondPage.setAgreementDelay("10");
        secondPage.setBestBefore();
        secondPage.extractNumberDocument();

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
        productsPage.fillNewItem("Услуга", "100", "100");

        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());

        thirdPage = secondPage.toTheNextStep();
        thirdPage.initPage();

        thirdPage.enterExistingNameOfProduct();
        thirdPage.setNumberOfItems("5");
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
        sixthPage.setTypeOfAgreement("Услуги");
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
        eighthPage.uploadAct();
        eighthPage.setNumber4();
        eighthPage.setCalendar3FirstDay();
        eighthPage.setNumber3();
        eighthPage.uploadInvoice();

        ninethPage = eighthPage.acceptOrder();
        ninethPage.waitForLoad();
        ninethPage.waitForLoad();
        ninethPage.setFullLoad();
        ninethPage.waitForLoad();

        dynamicPayments.open();
        dynamicPayments.initPage();
        //Assert.assertTrue(dynamicPayments.isPaymentCreated());

        financialStatePage.open();
        financialStatePage.initPage();
        financialStatePage.checkWithWAT();
        financialStatePage.enterSellersName();
        financialStatePage.build();

        mainPage.open();
        mainPage.openCreatedDocument();

        ninethPage.waitForLoad();
        ninethPage.partialShipment("1");
        ninethPage.waitForLoad();

        dynamicPayments.open();

        //TODO: Ассерт

        financialStatePage.open();
        financialStatePage.checkWithWAT();
        financialStatePage.enterSellersName();
        financialStatePage.build();

        sellersPage.open();
        sellersPage.openCreatedSeller();
        sellersPage.changeData();
        sellersPage.setBalance();
        sellersPage.save();

        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());

        dynamicPayments.open();
        dynamicPayments.initPage();
        dynamicPayments.openPayWindowS();
        dynamicPayments.setValuesPayWindow();
        dynamicPayments.confirmPayment();

        dynamicPayments.initPage();
        dynamicPayments.openReturns();
        dynamicPayments.setReturnsPayWindow();
        dynamicPayments.confirmPayment();

        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());

        dynamicPayments.initPage();
        ninethPage.editUnload();
    }
}
