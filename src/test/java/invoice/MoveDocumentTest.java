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
    private ManagersPage managersPage = PageFactory.initElements(getWebDriver(), ManagersPage.class);
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
        createPurchaseSecondPage.agreement("Save");

        createPurchaseSecondPage.waitForLoad();
        createPurchaseSecondPage.agreement("Send");

        managersPage.open();
        UtilStore.reload(getWebDriver());
        managersPage.checkAndSave();
        managersPage.enter();
        UtilStore.goBack(getWebDriver());

        createPurchaseSecondPage.waitForLoad();
        createPurchaseSecondPage.agreement("Use");
        createPurchaseSecondPage.waitForLoad();
        createPurchaseThirdPage = createPurchaseSecondPage.toTheNextStep();
        createPurchaseSecondPage.waitForLoad();
        createPurchaseThirdPage.addProduct();
        createPurchaseThirdPage.fillProductForm();


    }
}
