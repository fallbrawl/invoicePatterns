package invoice;

import com.invoice.pages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoresTest extends BasicTestCase {

//    Тест страницы Склады. http://screencast.com/t/iFLs43it3vn
//    1. Перейти на старницу "Склады"
//    2. Добавить новый склад
//    3. Проверить, что он добавился на старнице "Склады"
//    4. Нажать на кнопку "Редактировать" напротив склада
//    5. Изменить данные во всех полях и сохранить
//    6. Проверить, что данные изменились
//    7. Перейти на страницу склада
//    8. Проверить, что перешел на страницу склада
//    9. Нажать на кнопку "Удалить" склад
//    10. Проверить, что склад удалился

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private DynamicPayments dynamicPayments = PageFactory.initElements(getWebDriver(), DynamicPayments.class);
    private AddDocumentNinethPage ninethPage = PageFactory.initElements(getWebDriver(), AddDocumentNinethPage.class);
    private AddDocumentFourthPage fourthPage = PageFactory.initElements(getWebDriver(), AddDocumentFourthPage.class);
    private AddDocumentFifthPage fifthPage = PageFactory.initElements(getWebDriver(), AddDocumentFifthPage.class);
    private AddDocumentSixthPage sixthPage = PageFactory.initElements(getWebDriver(), AddDocumentSixthPage.class);
    private AddDocumentSeventhPage seventhPage = PageFactory.initElements(getWebDriver(), AddDocumentSeventhPage.class);
    private AddDocumentEighthPage eighthPage = PageFactory.initElements(getWebDriver(), AddDocumentEighthPage.class);
    private WarehousePage warehousePage = PageFactory.initElements(getWebDriver(), WarehousePage.class);
    private EditWarehousePage editWarehousePage = PageFactory.initElements(getWebDriver(), EditWarehousePage.class);

    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @Test
    public void storesTest() throws InterruptedException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        warehousePage.open();
        editWarehousePage = warehousePage.addNewStore();

        editWarehousePage.fillFieldsNewStore();
        warehousePage = editWarehousePage.acceptNewStore();
        warehousePage.checkNumberOfStores();

        Assert.assertTrue(warehousePage.linkNewStore.getText().equals("wow " + UtilStore.nameProduct));



    }

}
