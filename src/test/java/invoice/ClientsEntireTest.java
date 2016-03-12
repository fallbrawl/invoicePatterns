package invoice;

import com.invoice.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by paul on 10.03.16.
 */
public class ClientsEntireTest extends BasicTestCase {
    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddClientPage addClientPage = PageFactory.initElements(getWebDriver(), AddClientPage.class);
    private ListClientPage listClientPage = PageFactory.initElements(getWebDriver(), ListClientPage.class);
    private SpecificClientPage specificClientPage = PageFactory.initElements(getWebDriver(), SpecificClientPage.class);
    private MainPage mainPage;

//    Клиенты - создать, отредактировать, добавить догвоор, удалить догвоор, удалить клиента.
//    Ну и проверки, что все это делается.
//    http://screencast.com/t/kOmnFGCooIh

    @Test

    public void clientsEntireTest() throws InterruptedException {
        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        addClientPage.open();
        addClientPage.enterClientName();
        addClientPage.submitNewClient();

        listClientPage.openLastCreatedClient();
        Assert.assertTrue(specificClientPage.ensureThatOpenedRightClient());//открылся правильный, новый клиент  ?

        specificClientPage.enableEditingClientProperties();
        specificClientPage.enterNewAddress();

        listClientPage = specificClientPage.save();
        listClientPage.openLastCreatedClient();
        Assert.assertTrue(specificClientPage.ensureThatDataIsSaved()); //сохранился введенный адрес?

        specificClientPage.openDocuments();
        specificClientPage.addNewDocument();
        specificClientPage.setDelay();
        specificClientPage.setDate();
        specificClientPage.saveDocument();
        specificClientPage.openDocuments();
        specificClientPage.deleteDocument();
        specificClientPage.openDocuments();

        Assert.assertTrue(specificClientPage.ensureDocumentDeleted());

        specificClientPage.goToClientsList();
        listClientPage.

    }


}
