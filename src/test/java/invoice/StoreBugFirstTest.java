package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by paul on 01.03.16.
 */
public class StoreBugFirstTest extends BasicTestCase {

//    Description :
//    Был баг. После создания счета, дойдя до Шага 3 я поменяла в профиле пользователя основной склад.
//    Затем вернулась в счет, сделала закупку товара к своему счету и после принятия транзита в Шаге 8 - меня, вместо Шага 9, перекинуло на ШАГ 4.
//    Перейдя в резервы я увидела, что мой товар распределился в другой счет.
//    [17:45:39] Юлия: В первой части видео обычная цепочка с добавлением абсолютно нового товара.
//    1. Создать счет, дойти до Шага 3
//    2. Перейти в профиль пользователя и изменить основной склад
//    3. Зайти обратно в счет, добавить товар, пройти всю цепочку.
//    4. Проверить, что после Шага 8 ты переходишь на Шаг 9
//    5. Проверить, что на странице "Резерв" в столбце "Склад" отображается твой текущий основной склад
//
//    http://www.screencast.com/t/vrovAmfUIuM

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

    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @Test(enabled = false)
    //-Dtest=LoginTest,AgreeAndBuyServicesTest test
    public void storeBugFirstTest() throws NoSuchFieldException, InterruptedException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

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
        thirdPage.setNumberOfItems();
        thirdPage.addProduct();
        thirdPage.fillProductForm();
        thirdPage.waitForLoad();
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
//        dynamicPayments.waitForLoad();
        dynamicPayments.initPage();
        UtilStore.goBack(getWebDriver());
        UtilStore.reload(driver);

        thirdPage.initPage();
        UtilStore.reload(getWebDriver());
        thirdPage.initPage();
        fourthPage = thirdPage.saveAndInitiate();
//        fourthPage.waitForLoad();
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
