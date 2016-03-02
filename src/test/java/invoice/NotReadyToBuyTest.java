package invoice;

import com.invoice.pages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotReadyToBuyTest extends BasicTestCase {

//    http://screencast.com/t/gF9wGgx2HX
//    Суть в чем.
//    Ты создаешь закупку на новый товар, при чем при отправке в транзит обязательно указываешь не сегодняшнюю дату (позже хотя бы на день-два).
//    Далее, при принятии транзита опять указываешь ту же дату.
//    Потом создаешь счет, в который добавляешь этот же товар.
//    При инициации закупки у тебя должен появиться поп ап с резервированием товара.
//    После резервирования тебя должно перекинуть сразу на Шаг 9, где не должно быть кнопок "Частичная отгрузка" и "Полная отгрузка".
//    Далее.
//    После резервирования товара для данного счета, на странице "Заказы на продажу" у этого счета будет статус "Ожидает отгрузки - готов полностью".
//    Переходим на страницу "Резерв" меню "Логистика" и напротив зарезервированного товара нажимаем на кнопку "Открепить".
//    После - переходим на старницу "Заказы на продажу". Статус напротив нашего счета должен поменяться на "Отправлен в закупку не готов".
//    Все.

    //Initializing objects via PageFactory

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private CreatePurchaseFirstPage createPurchaseFirstPage = PageFactory.initElements(getWebDriver(), CreatePurchaseFirstPage.class);
    private CreatePurchaseSecondPage createPurchaseSecondPage = PageFactory.initElements(getWebDriver(), CreatePurchaseSecondPage.class);
    private CreatePurchaseThirdPage createPurchaseThirdPage = PageFactory.initElements(getWebDriver(), CreatePurchaseThirdPage.class);
    private PurchasePage purchasePage = PageFactory.initElements(getWebDriver(), PurchasePage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);
    private ReservedPage reservedPage = PageFactory.initElements(getWebDriver(), ReservedPage.class);

    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @Test
    public void notReadyToBuy() throws NoSuchFieldException, InterruptedException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        createPurchaseFirstPage.open();
        createPurchaseFirstPage.enterNames();
        createPurchaseFirstPage.acceptPurchase();

        createPurchaseSecondPage.waitForLoad();
        createPurchaseSecondPage = createPurchaseFirstPage.toTheNextStep();
        createPurchaseSecondPage.uploadFile();
        createPurchaseSecondPage.setAgreementDelay();
        createPurchaseSecondPage.setBestBefore();
        createPurchaseSecondPage.agreement("Save");

        // createPurchaseSecondPage.waitForLoad();
        // createPurchaseSecondPage.agreement("Send");
        // managersPage.open();                  для НЕ эгоиста подписываю документы в бухгалтерии
        // UtilStore.reload(getWebDriver());
        // managersPage.checkAndSave();
        // managersPage.enter();
        // UtilStore.goBack(getWebDriver());

        createPurchaseSecondPage.waitForLoad();
        createPurchaseSecondPage.agreement("Use");
        createPurchaseSecondPage.waitForLoad();
        createPurchaseThirdPage = createPurchaseSecondPage.toTheNextStep();
        createPurchaseSecondPage.waitForLoad();

        createPurchaseThirdPage.addProduct();
        createPurchaseThirdPage.fillProductForm();
        createPurchaseThirdPage.saveAndInitiate();

        createPurchaseThirdPage.setCalendar("firstDayOfTHeSecondWeekOnTheNextMonth");
        createPurchaseThirdPage.upl();
        createPurchaseThirdPage.go();
        createPurchaseThirdPage.waitForLoad();

        purchasePage.openOrder();
        purchasePage.setCalendar1();
        purchasePage.uploadNaklad();
        purchasePage.setNumber2();
        purchasePage.setCalendar2();
        purchasePage.setNumber1();
        purchasePage.uploadInvoice();
        purchasePage.acceptOrder();

        purchasePage.waitForLoad();
        purchasePage.initPage();

        UtilStore.reload(getWebDriver());

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
        thirdPage.enterNameOfProduct();
        thirdPage.addProduct();
        thirdPage.save();
        thirdPage.waitForLoad();

        dynamicPayments.open();
        dynamicPayments.openPayWindow();
        dynamicPayments.setValuesPayWindow();
        dynamicPayments.uploadFile();
        dynamicPayments.confirmPayment();
//        dynamicPayments.waitForLoad();
        dynamicPayments.initPage();
        UtilStore.goBack(getWebDriver());

        thirdPage.waitForLoad();
        createPurchaseSecondPage.extractNumber();
        UtilStore.reload(getWebDriver());
        thirdPage.saveAndInitiate();
//        thirdPage.waitForLoad();
        thirdPage.reserveAndInitiate();

        UtilStore.reload(getWebDriver());

        reservedPage.open();
        reservedPage.initPage();
        reservedPage.unattach();
        reservedPage.enter();
        reservedPage.waitForLoad();

        mainPage.open();
        mainPage.initPage();
        Assert.assertTrue(driver.findElements(By.linkText(createPurchaseSecondPage.documentName)).size() == 1);

//        managersPage.open();
//        UtilStore.reload(getWebDriver());
//        managersPage.open();
//        UtilStore.reload(getWebDriver());
//        managersPage.checkAndSave();
//        managersPage.enter();                   для НЕ эгоиста подписываю документы в бухгалтерии
//        UtilStore.reload(getWebDriver());
//        UtilStore.reload(getWebDriver());
//        managersPage.checkAndSave();
//        managersPage.enter();
//        UtilStore.reload(getWebDriver());
//        UtilStore.reload(getWebDriver());
//        managersPage.checkAndSave();
//        managersPage.enter();
//        UtilStore.reload(getWebDriver());
//        UtilStore.reload(getWebDriver());
//        purchasePage.open();

    }
}
