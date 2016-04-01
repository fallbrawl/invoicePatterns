package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.*;
import com.invoice.pages.CreatePurchasePages.CreatePurchaseFirstPage;
import com.invoice.pages.CreatePurchasePages.CreatePurchaseFourthPage;
import com.invoice.pages.CreatePurchasePages.CreatePurchaseSecondPage;
import com.invoice.pages.CreatePurchasePages.CreatePurchaseThirdPage;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreBugSecondTest extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);
    private AddDocumentFourthPage fourthPage = PageFactory.initElements(getWebDriver(), AddDocumentFourthPage.class);
    private ProfilePage profilePage = PageFactory.initElements(getWebDriver(), ProfilePage.class);
    private AddDocumentFifthPage fifthPage = PageFactory.initElements(getWebDriver(), AddDocumentFifthPage.class);
    private AddDocumentSixthPage sixthPage = PageFactory.initElements(getWebDriver(), AddDocumentSixthPage.class);
    private AddDocumentSeventhPage seventhPage = PageFactory.initElements(getWebDriver(), AddDocumentSeventhPage.class);
    private AddDocumentEighthPage eighthPage = PageFactory.initElements(getWebDriver(), AddDocumentEighthPage.class);
    private AddDocumentNinethPage ninethPage = PageFactory.initElements(getWebDriver(), AddDocumentNinethPage.class);
    private ReservedPage reservedPage = PageFactory.initElements(getWebDriver(), ReservedPage.class);
    private CreatePurchaseFirstPage createPurchaseFirstPage = PageFactory.initElements(getWebDriver(), CreatePurchaseFirstPage.class);
    private CreatePurchaseSecondPage createPurchaseSecondPage = PageFactory.initElements(getWebDriver(), CreatePurchaseSecondPage.class);
    private CreatePurchaseThirdPage createPurchaseThirdPage = PageFactory.initElements(getWebDriver(), CreatePurchaseThirdPage.class);
    private CreatePurchaseFourthPage createPurchaseFourthPage = PageFactory.initElements(getWebDriver(), CreatePurchaseFourthPage.class);
    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    //    Во второй части видео другая цепочка:
//    1. Создать счет, дойти до Шага 3
//    2. Добавить товар, который есть на складе (для этого сначала создай инициативную закупку на этот товар)
//    3. Убедись, что после добавления товара есть текст "Доступно на складах: *число больше 0*"
//    4. Перейти в профиль пользователя и изменить основной склад
//    5. Пройти всю цепочку (проверить, чтобы четко переходило по шагам, без перепрыгивания на Шаг 4)
//    6. Проверить, что на странице "Резерв" в столбце "Склад" отображается твой текущий основной склад
//    http://www.screencast.com/t/vrovAmfUIuM

    @Test(enabled = false)
    public void storeBugSecondTest() throws Exception {


        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        createPurchaseFirstPage.open();
        createPurchaseFirstPage.enterNames();
        createPurchaseFirstPage.acceptPurchase();
        createPurchaseSecondPage.waitForLoad();

        createPurchaseSecondPage = createPurchaseFirstPage.toTheNextStep();
        createPurchaseSecondPage.uploadFile();
        createPurchaseSecondPage.extractNumber();
        createPurchaseSecondPage.setAgreementDelay();
        createPurchaseSecondPage.agreement("Save");
        createPurchaseSecondPage.waitForLoad();
        createPurchaseSecondPage.agreement("Use");
        createPurchaseSecondPage.waitForLoad();

        createPurchaseThirdPage = createPurchaseSecondPage.toTheNextStep();
        createPurchaseSecondPage.waitForLoad();
        createPurchaseThirdPage.setNumberOfItems("100");
        createPurchaseThirdPage.addProduct();
        createPurchaseThirdPage.fillProductForm();
        createPurchaseThirdPage.initPage();
        createPurchaseThirdPage.saveAndInitiate();
        createPurchaseThirdPage.setCalendar("thisDay");
        createPurchaseThirdPage.upl();

        createPurchaseFourthPage = createPurchaseThirdPage.sendToTransit();
        createPurchaseFourthPage.init();
        createPurchaseFourthPage.uploadNaklad();
        createPurchaseFourthPage.uploadInvoice();
        createPurchaseFourthPage.setNumber1();
        createPurchaseFourthPage.setNumber2();

        createPurchaseFourthPage.setCalendar1();
        createPurchaseFourthPage.setCalendar2();
        createPurchaseFourthPage.saveDocumentAndTransfer();
        createPurchaseFourthPage.waitForLoad();
        UtilStore.goBack(getWebDriver());
        createPurchaseFourthPage.init();
        UtilStore.reload(getWebDriver());
        createPurchaseFourthPage.init();

        firstPage.open();
        firstPage.enterNames();

        secondPage = firstPage.toTheNextStep();
        secondPage.uploadFile();
        secondPage.setTypeOfAgreementService("Товарный");
        secondPage.agreement("Save");
        secondPage.waitForLoad();
        secondPage.agreement("Use");
        secondPage.waitForLoad();
        thirdPage = secondPage.toTheNextStep();

        thirdPage.initPage();
        thirdPage.enterExistingNameOfProduct();
        thirdPage.addProduct();
        thirdPage.initPage();

        Assert.assertTrue(thirdPage.getNumberOfItems() > 0); //кол-во элементов больше нуля

        thirdPage.initPage();
        thirdPage.save();
        thirdPage.waitForLoad();

        profilePage = thirdPage.openProfile(); // в профиле чекаю
        profilePage.setStore();
        profilePage.initPage();
        profilePage.getCurrentStore();
        profilePage.saveProfile();
        profilePage.initPage();
        UtilStore.goBack(getWebDriver());

        thirdPage.waitForLoad();
        thirdPage.initPage();

        dynamicPayments.open();
        dynamicPayments.openPayWindow();
        dynamicPayments.setValuesPayWindow();
        dynamicPayments.uploadFile();
        dynamicPayments.confirmPayment();
        dynamicPayments.initPage();
        UtilStore.goBack(getWebDriver());
        UtilStore.reload(driver);

        thirdPage.initPage();
        UtilStore.reload(getWebDriver());
        thirdPage.initPage();

        fourthPage = thirdPage.saveAndInitiate();
        fourthPage.initPage();

        fifthPage = fourthPage.buyForAll();
        fifthPage.enterProviderName();
        fifthPage.initPage();
        fifthPage.initPurchase();
        fifthPage.waitForLoad();

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
        eighthPage.setCalendar1NextMonthFirstDay();
        eighthPage.uploadNaklad();
        eighthPage.setNumber2();
        eighthPage.setCalendar2NextMonthFirstDay();
        eighthPage.setNumber1();
        eighthPage.uploadInvoice();

        ninethPage = eighthPage.acceptOrder();

        ninethPage.waitForLoad();
        ninethPage.initPage();
        Assert.assertTrue(driver.getPageSource().contains("Формируем пакет отгрузочных документов на подпись"));
        profilePage = ninethPage.openProfile();
        System.out.println("Sklad " + profilePage.getCurrentStore());
        Assert.assertTrue(driver.getPageSource().contains(profilePage.getCurrentStore()));

        reservedPage.open();
        reservedPage.initPage();
        Assert.assertTrue(reservedPage.getLinkStore().getText().contains(profilePage.getStore()));

    }
}
