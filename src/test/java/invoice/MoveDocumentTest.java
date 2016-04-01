package invoice;

import com.invoice.pages.*;
import com.invoice.pages.CreatePurchasePages.CreatePurchaseFirstPage;
import com.invoice.pages.CreatePurchasePages.CreatePurchaseFourthPage;
import com.invoice.pages.CreatePurchasePages.CreatePurchaseSecondPage;
import com.invoice.pages.CreatePurchasePages.CreatePurchaseThirdPage;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Created by paul on 17.02.16.
 */
public class MoveDocumentTest extends BasicTestCase {

    //    http://screencast.com/t/o5nKHxIIHog
    //    Закупка с новым товаром
    //    (лучше добавлять новый товар, чтобы он не закрепился автоматически за каким-нибудь старым счетом и тест не завалился), принятие транзита.
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
    private SellersPage sellersPage = PageFactory.initElements(getWebDriver(), SellersPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);

    private MainPage mainPage;

    @Test
    public void moveDocument() throws InterruptedException, NoSuchFieldException {

        //loginPage.open();
        //mainPage = loginPage.loginAs(admin);

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

        sellersPage.initPage();

        sellersPage.open();
        sellersPage.openCreatedSeller();
        sellersPage.changeData();
        sellersPage.setBalance();
        sellersPage.save();
        UtilStore.goBack(getWebDriver());
        UtilStore.goBack(getWebDriver());

        createPurchaseThirdPage = createPurchaseSecondPage.toTheNextStep();
        createPurchaseSecondPage.waitForLoad();

        createPurchaseThirdPage.addProduct();
        createPurchaseThirdPage.fillProductForm();
        createPurchaseThirdPage.saveAndInitiate();
        createPurchaseThirdPage.setCalendar("thisDay");
        createPurchaseThirdPage.upl();
        createPurchaseThirdPage.payTransit();
        //createPurchaseThirdPage.saveAndOrder();

        dynamicPayments.open();
        dynamicPayments.openPayWindow();
        dynamicPayments.setValuesPayWindow();
        dynamicPayments.uploadFile();
        dynamicPayments.confirmPayment();
        dynamicPayments.waitForLoad();
        dynamicPayments.initPage();
        UtilStore.goBack(getWebDriver());

        createPurchaseThirdPage.initPage();
        UtilStore.reload(getWebDriver());
        createPurchaseThirdPage.initPage();
//        createPurchaseThirdPage.saveAndOrder();
        createPurchaseThirdPage.saveAndOrder();
        createPurchaseThirdPage.setCalendar("thisDay");
        createPurchaseThirdPage.upl();
        createPurchaseThirdPage.payTransit();
//        createPurchaseThirdPage.saveAndOrder();
  //      createPurchaseThirdPage.payTransit();

     //   createPurchaseThirdPage.payTransit();


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
