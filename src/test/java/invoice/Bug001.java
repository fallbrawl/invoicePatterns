package invoice;

import com.invoice.pages.LoginPage;
import com.invoice.pages.ManagersPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Created by paul on 15.02.16.
 */
public class Bug001 extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private ManagersPage managersPage = PageFactory.initElements(getWebDriver(), ManagersPage.class);


//    Bug 001:
//    Steps to reproduce
//    Залогиниться в Invoice
//    Перейти в подраздел “Ожидают одобрения”
//    Нажать на кнопку “Одобрить”
//    После появления диалогового окна нажать кнопку “Enter”
//
//    Expected result: Окно закроется, документ подтвердится
//    Actual result: Окно закроется, появится снова, документ не подтвердится.

    @Test
    public void testBug001() throws InterruptedException {
        loginPage.open();
        loginPage.loginAs(admin);
        managersPage.open();
        managersPage.checkAndSave();
//        managersPage.save();
//        managersPage.enter();

    }


}
