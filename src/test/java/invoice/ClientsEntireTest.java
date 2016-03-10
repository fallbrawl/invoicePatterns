package invoice;

import com.invoice.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Created by paul on 10.03.16.
 */
public class ClientsEntireTest extends BasicTestCase {
    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private WarehousePage warehousePage = PageFactory.initElements(getWebDriver(), WarehousePage.class);
    private EditWarehousePage editWarehousePage = PageFactory.initElements(getWebDriver(), EditWarehousePage.class);
    private WarehouseStandalonePage warehouseStandalonePage = PageFactory.initElements(getWebDriver(), WarehouseStandalonePage.class);
    private MainPage mainPage;
//    Клиенты - создать, отредактировать, добавить догвоор, удалить догвоор, удалить клиента.
//    Ну и проверки, что все это делается.
//    http://screencast.com/t/kOmnFGCooIh
    @Test
            public void clientsEntireTest() {
        loginPage.open();
        mainPage = loginPage.loginAs(admin);
    }


}
