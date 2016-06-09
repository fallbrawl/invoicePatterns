package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

/**
 * Created by paul on 22.04.16.
 */
public class DocumentExportTest extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddNewUserPage addNewUserPage = PageFactory.initElements(getWebDriver(), AddNewUserPage.class);
    private UsersPage usersPage = PageFactory.initElements(getWebDriver(), UsersPage.class);
    private ProfilePage profilePage = PageFactory.initElements(getWebDriver(), ProfilePage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private ProductsPage productsPage = PageFactory.initElements(getWebDriver(), ProductsPage.class);
    private AddDocumentFourthPage fourthPage = PageFactory.initElements(getWebDriver(), AddDocumentFourthPage.class);

    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @Test(enabled = false)

    public void documentExportTest() throws InterruptedException, NoSuchFieldException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        //Добавляю первого пользователя

        usersPage.open();
        addNewUserPage = usersPage.addNewUser();

        addNewUserPage.setNewUserEmail("first");
        addNewUserPage.fillNewUserForm("Менеджер по продажам");
        addNewUserPage.checkEgoiste();
        usersPage = addNewUserPage.saveNewUser();

        UtilStore.goBack(getWebDriver());
        UtilStore.reload(getWebDriver());
        usersPage.initPage();

        System.out.println(UtilStore.userEmail + "first" + "@mail.ru");
        Assert.assertTrue(usersPage.textNewUserEmail.getText().contains(UtilStore.userEmail + "first" + "@mail.ru")); //Проверка на то что новый пользователь создан

        productsPage.open();
        productsPage.addNewItem();
        productsPage.fillNewItem("Товар","75","100");

        usersPage.logout();

        System.out.println("forst " + addNewUserPage.getNewUserEmail());
        loginPage.loginAs(addNewUserPage.getNewUserEmail(), addNewUserPage.getNewUserPassword()); //Логин под первым пользователем

        //Добавляю первый счет для первого пользователя

        firstPage.open();
        firstPage.enterNames();

        secondPage = firstPage.toTheNextStep();

        secondPage.uploadFile();

        secondPage.setTypeOfAgreementService("Товарный");

        secondPage.setAgreementDelay("10");
        secondPage.setBestBefore();

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

        thirdPage.waitForLoad();
        thirdPage.save();
        thirdPage.waitForLoad();

        fourthPage = thirdPage.saveAndInitiate();
        fourthPage.initPage();

        //Добавляю второй счет для первого пользователя

        firstPage.open();
        firstPage.enterNames();

        secondPage = firstPage.toTheNextStep();

        secondPage.uploadFile();

        secondPage.setTypeOfAgreementService("Товарный");

        secondPage.setAgreementDelay("10");
        secondPage.setBestBefore();

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

        thirdPage.waitForLoad();
        thirdPage.save();
        thirdPage.waitForLoad();

        fourthPage = thirdPage.saveAndInitiate();

        //Разлогин и логин под админом
        mainPage.open();
        mainPage.logout();
        mainPage = loginPage.loginAs(admin);

        usersPage.open();
        addNewUserPage = usersPage.addNewUser();

        //Добавляю второго пользователя

        addNewUserPage.setNewUserEmail("second");
        addNewUserPage.fillNewUserForm("Менеджер по продажам");
        addNewUserPage.checkEgoiste();
        usersPage = addNewUserPage.saveNewUser();

        usersPage.initPage();
        UtilStore.goBack(getWebDriver());
        UtilStore.reload(getWebDriver());
        usersPage.initPage();

        Assert.assertTrue(usersPage.textNewUserEmail.getText().contains(UtilStore.userEmail + "second" + "@mail.ru")); //Проверка на то что новый пользователь создан




        usersPage.logout();

        //Логин вторым пользователем

        System.out.println("second " + addNewUserPage.getNewUserEmail());

        loginPage.loginAs(addNewUserPage.getNewUserEmail(), addNewUserPage.getNewUserPassword());

        Assert.assertTrue(mainPage.isNullDocuments()); // Проверка, что у второго пользователя нет документов

        mainPage.logout();

        //Логин админом

        mainPage = loginPage.loginAs(admin);

        //Экспортирую документы
        //Открываю редактирование первого пользователя

        usersPage.open();
        addNewUserPage.setNewUserEmail("first");
        usersPage.openFirstUser(addNewUserPage.getNewUserEmail());

        addNewUserPage.setNewUserEmail("second");
        usersPage.selectUserToCopyDocument(addNewUserPage.getNewUserEmail());
        usersPage.exportDocuments();
        UtilStore.goBack(getWebDriver());

        //Логин вторым пользователем

        addNewUserPage.setNewUserEmail("second");
        usersPage.logout();
        mainPage = loginPage.loginAs(addNewUserPage.getNewUserEmail(), addNewUserPage.getNewUserPassword());
        mainPage.initPage();
        Assert.assertTrue(!mainPage.isNullDocuments()); //что документы видны
        mainPage.logout();

        //Логин админом

        mainPage = loginPage.loginAs(admin);

        usersPage.open();
        addNewUserPage.setNewUserEmail("first");
        usersPage.openFirstUser(addNewUserPage.getNewUserEmail());

        //Копирую документы единолично

        addNewUserPage.setNewUserEmail("second");
        usersPage.selectUserToCopyDocument(addNewUserPage.getNewUserEmail());
        usersPage.setSoloAccessToDocuments();
        usersPage.exportDocuments();
        UtilStore.goBack(getWebDriver());
        usersPage.logout();

        //Логин вторым пользователем

        addNewUserPage.setNewUserEmail("first");
        mainPage = loginPage.loginAs(addNewUserPage.getNewUserEmail(), addNewUserPage.getNewUserPassword());
        mainPage.logout();
        //
        // Assert.assertTrue(mainPage.isNullDocuments()); // Проверка, что первый пользователь не видит документы после галочки

    }
}
