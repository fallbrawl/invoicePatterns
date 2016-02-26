package invoice;

import com.invoice.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Created by paul on 26.02.16.
 */
public class AgreeServicesTest extends BasicTestCase {

//    http://screencast.com/t/zgMA3uFC0Bn
//    Договор на услуги, добавляем услуги,
//    устанавливаем галочку "Услугу указываем самостоятельно",
//    совершаем отгрузку (там сейчас ошибка 500, ребята правят),
//    проверяем отгрузочные документы (счет,акт, счет-фактура)

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);
    private AddDocumentFourthPage fourthPage = PageFactory.initElements(getWebDriver(), AddDocumentFourthPage.class);
    private AddDocumentFifthPage fifthPage = PageFactory.initElements(getWebDriver(), AddDocumentFifthPage.class);
    private AddDocumentSixthPage sixthPage = PageFactory.initElements(getWebDriver(), AddDocumentSixthPage.class);
    private AddDocumentSeventhPage seventhPage = PageFactory.initElements(getWebDriver(), AddDocumentSeventhPage.class);
    private AddDocumentEighthPage eighthPage = PageFactory.initElements(getWebDriver(), AddDocumentEighthPage.class);
    private AddDocumentNinethPage ninethPage = PageFactory.initElements(getWebDriver(), AddDocumentNinethPage.class);

    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @Test

    public void agreeServicesTest() throws NoSuchFieldException, InterruptedException {
        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        firstPage.open();
        firstPage.enterNames();

        secondPage = firstPage.toTheNextStep();
        secondPage.uploadFile();
        secondPage.setTypeOfAgreementService();
        secondPage.agreement("Save");
        secondPage.waitForLoad();
        secondPage.agreement("Use");
        secondPage.waitForLoad();

        thirdPage = secondPage.toTheNextStep();
        thirdPage.initPage();
        thirdPage.enterExistingNameOfProduct();
        thirdPage.addProduct();
        thirdPage.save();
        thirdPage.waitForLoad();
    }}
