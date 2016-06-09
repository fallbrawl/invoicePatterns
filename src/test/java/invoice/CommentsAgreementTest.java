package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by paul on 24.05.16.
 */
public class CommentsAgreementTest extends BasicTestCase {

//    [17:40:20] Юлия: На скринкасте ты увидишь баг (как не странно).
//    Там, где в архиве не отображается текст комментария. Это Саша должен поправить.
//    http://screencast.com/t/CwGbuCvV8
//    http://joxi.ru/YmEaq3nUZoEwNm и еще тут проверку на этот попап с комментарием надо
//    http://joxi.ru/DrloEBQC48P0xA и тут на оба комментария

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

    private SellersPage sellersPage = PageFactory.initElements(getWebDriver(), SellersPage.class);

    private MainPage mainPage = PageFactory.initElements(getWebDriver(), MainPage.class);
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @org.testng.annotations.Test(priority = 97)
    public void commentsAgreementTest() throws InterruptedException, NoSuchFieldException {

        loginPage.open();
        loginPage.loginAs(admin);

        profilePage.open();
        profilePage.setEgoiste("Disabled");
        profilePage.saveProfile();

        firstPage.open();
        firstPage.enterNames();
        secondPage = firstPage.toTheNextStep();

        secondPage.uploadFile();
        secondPage.setAgreementDelay("10");
        secondPage.setBestBefore();
        secondPage.extractNumberDocument();
        secondPage.agreement("Save");
        secondPage.waitForLoad();
        secondPage.agreement("Send");
        secondPage.waitForLoad();

        managersPage.open();

        managersPage.declineAgreement();
        managersPage.writeComment("Bad comment on decline");
        managersPage.clickOk();

        archivePage.open();
        archivePage.openSubPage("Договора клиентов");
        archivePage.openComment();

        Assert.assertTrue(archivePage.isBadCommentSaved()); //Сохранился ли плохой комментарий об отклонении на странице архива ?

        mainPage.open();
        mainPage.openCreatedDocument();
        secondPage.openBadComment();

        Assert.assertTrue(secondPage.isBadCommentSaved()); //Сохранился ли плохой комментарий об отклонении на странице выбора счета для договора Шаг 2  ?

        secondPage.uploadFile();
        secondPage.setAgreementDelay("10");
        secondPage.setBestBefore();
        secondPage.extractNumberDocument();
        secondPage.agreement("Save");
        secondPage.waitForLoad();
        secondPage.agreement("Send");
        secondPage.waitForLoad();

        managersPage.open();
        managersPage.acceptAgreement();

        UtilStore.goBack(driver);
        UtilStore.reload(driver);
        secondPage.initPage();
        secondPage.agreement("Use");
        secondPage.toTheNextStep();

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
        thirdPage.setNumberOfItems("5");
        thirdPage.addProduct();
        thirdPage.fillProductForm();
        thirdPage.waitForLoad();
        thirdPage.save();
        thirdPage.waitForLoad();

        archivePage.open();
        archivePage.openSubPage("Договора клиентов");
        archivePage.blockAgreement();
        archivePage.writeComment("Bad comment on decline");
        archivePage.clickOk();
        archivePage.openComment();
        Assert.assertTrue(archivePage.isBadCommentSaved()); //Сохранился ли плохой комментарий об отклонении на странице архива ?

        mainPage.open();
        mainPage.openComment();
        Assert.assertTrue(mainPage.isBadCommentSaved()); //Сохранился ли плохой комментарий об отклонении на главной ?

        dynamicPayments.open();
        dynamicPayments.openComment();
        Assert.assertTrue(dynamicPayments.isBadCommentSaved()); //Сохранился ли плохой комментарий об отклонении на движении платежей ?

//        clientPage.open(); //TODO:не пашет из-за багов.
//        clientPage.openClient();
//        clientPage.openAgreements();

        //Assert.assertTrue(clientPage.isFirstBadCommentSaved()); //Сохранился ли первый плохой комментарий об отклонении на странице клиентов ?
        //Assert.assertTrue(clientPage.isSecondBadCommentSaved()); //Сохранился ли второй плохой комментарий об отклонении на странице клиентов ?


    }
}
