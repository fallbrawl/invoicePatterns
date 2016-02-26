package invoice;

import com.invoice.pages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Created by paul on 17.02.16.
 */
public class MoveDocumentTest extends BasicTestCase {

    //    http://screencast.com/t/o5nKHxIIHog
    //    Закупка с новым товаром (лучше добавлять новый товар, чтобы он не закрепился автоматически за каким-нибудь старым счетом и тест не завалился),принятие транзита.
    //    Перемещение этого товара с помощью меню Перемещение, затем принятие перемещения на странице "Движение товара".
    //    После этого перейти в новосозданную партию и переместить товар из партии.
    //    Затем принять его на странице "Движение товара".

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private CreatePurchaseFirstPage createPurchaseFirstPage = PageFactory.initElements(getWebDriver(), CreatePurchaseFirstPage.class);
    private CreatePurchaseSecondPage createPurchaseSecondPage = PageFactory.initElements(getWebDriver(), CreatePurchaseSecondPage.class);
    private CreatePurchaseThirdPage createPurchaseThirdPage = PageFactory.initElements(getWebDriver(), CreatePurchaseThirdPage.class);
    private CreatePurchaseFourthPage createPurchaseFourthPage = PageFactory.initElements(getWebDriver(), CreatePurchaseFourthPage.class);
    private ManagersPage managersPage = PageFactory.initElements(getWebDriver(), ManagersPage.class);
    private MovePage movePage = PageFactory.initElements(getWebDriver(), MovePage.class);
    private PartiesPage partiesPage = PageFactory.initElements(getWebDriver(), PartiesPage.class);
    private MovementPage movementPage = PageFactory.initElements(getWebDriver(), MovementPage.class);
    private PitchPage pitchPage = PageFactory.initElements(getWebDriver(), PitchPage.class);
    private MainPage mainPage;

    @Test
    public void moveDocument() throws InterruptedException, NoSuchFieldException {

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
//        createPurchaseSecondPage.agreement("Send");
//
//        managersPage.open();
//        UtilStore.reload(getWebDriver());
//        managersPage.checkAndSave();
//        managersPage.enter();
//        UtilStore.goBack(getWebDriver());
//
//        createPurchaseSecondPage.waitForLoad();
        createPurchaseSecondPage.agreement("Use");
        createPurchaseSecondPage.waitForLoad();
        createPurchaseThirdPage = createPurchaseSecondPage.toTheNextStep();
        createPurchaseSecondPage.waitForLoad();

        createPurchaseThirdPage.addProduct();
        createPurchaseThirdPage.fillProductForm();
        createPurchaseThirdPage.saveAndInitiate();
        createPurchaseThirdPage.setCalendar("thisDay");
        createPurchaseThirdPage.upl();

        createPurchaseFourthPage = createPurchaseThirdPage.sendToTransit();
        createPurchaseFourthPage.init();

        managersPage.open();
        UtilStore.reload(getWebDriver());
        managersPage.checkAndSave();
        managersPage.enter();
        UtilStore.goBack(getWebDriver());
        createPurchaseFourthPage.init();

        UtilStore.reload(getWebDriver());
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

        managersPage.open();
        managersPage.checkAndSave();
        managersPage.enter();
        UtilStore.reload(getWebDriver());
        UtilStore.reload(getWebDriver());
        managersPage.checkAndSave();
        managersPage.enter();
        UtilStore.goBack(getWebDriver());
        createPurchaseFourthPage.init();
        UtilStore.reload(getWebDriver());

       // movePage.open();

        partiesPage.open();
        partiesPage.findProduct();
        partiesPage.selectItem();
        partiesPage.fillFields();
        movementPage = partiesPage.acceptMoving();

        partiesPage.waitForLoad();
        movementPage.save();
        movementPage.enter();
        movementPage.waitForLoad();
        movementPage.switchToLabelDone();
        pitchPage = movementPage.openNeededParty();

        pitchPage.moveFromParty();

        movementPage = pitchPage.moveFromStore();
        movementPage.save();
        movementPage.enter();



    }
}
