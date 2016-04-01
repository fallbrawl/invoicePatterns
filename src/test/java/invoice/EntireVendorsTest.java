package invoice;

import com.invoice.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by paul on 14.03.16.
 */
public class EntireVendorsTest extends BasicTestCase {

//    Поставщики - создать, отредактировать, добавить догвоор,
//    удалить догвоор, удалить клиента. Ну и проверки, что все это делается.
//    http://screencast.com/t/Zysn727g

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddVendorPage addVendorPage = PageFactory.initElements(getWebDriver(), AddVendorPage.class);
    private ListVendorPage listVendorPage = PageFactory.initElements(getWebDriver(), ListVendorPage.class);
    private SpecificVendorPage specificVendorPage = PageFactory.initElements(getWebDriver(), SpecificVendorPage.class);

    private MainPage mainPage;

    @Test

    public void entireVendorsTest() throws InterruptedException {

        //loginPage.open();
        //mainPage = loginPage.loginAs(admin);

        addVendorPage.open();
        addVendorPage.enterVendorName();
        addVendorPage.submitNewVendor();

        listVendorPage.openLastCreatedVendor();
        Assert.assertTrue(specificVendorPage.ensureThatOpenedRightVendor());//открылся правильный, новый клиент  ?

        specificVendorPage.enableEditingVendorProperties();
        specificVendorPage.enterNewAddress();

        listVendorPage = specificVendorPage.save();
        listVendorPage.openLastCreatedVendor();
        Assert.assertTrue(specificVendorPage.ensureThatDataIsSaved()); //сохранился введенный адрес?

        specificVendorPage.openDocuments();
        specificVendorPage.addNewDocument();
        specificVendorPage.setDelay();
        specificVendorPage.setDate();
        specificVendorPage.saveDocument();
        specificVendorPage.openDocuments();
        specificVendorPage.deleteDocument();
        specificVendorPage.openDocuments();

        Assert.assertTrue(specificVendorPage.ensureDocumentDeleted());

        specificVendorPage.goToVendorsList();

        listVendorPage.openTransit();
        listVendorPage.open();

        listVendorPage.openReturns();
        listVendorPage.open();
        listVendorPage.openLastCreatedVendor();

        specificVendorPage.deleteUser();
        Assert.assertTrue(listVendorPage.isVendorDeleted());

    }


}
