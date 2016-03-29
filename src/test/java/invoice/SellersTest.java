package invoice;

import com.invoice.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by paul on 29.03.16.
 */
public class SellersTest extends BasicTestCase {

    //Юлия: Продавцы http://screencast.com/t/gDxw8kzh

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddNewUserPage addNewUserPage = PageFactory.initElements(getWebDriver(), AddNewUserPage.class);
    private UsersPage usersPage = PageFactory.initElements(getWebDriver(), UsersPage.class);
    private ProfilePage profilePage = PageFactory.initElements(getWebDriver(), ProfilePage.class);
    private UserTypePage userTypePage  = PageFactory.initElements(getWebDriver(), UserTypePage.class);
    private SellersPage sellersPage  = PageFactory.initElements(getWebDriver(), SellersPage.class);
    private MainPage mainPage;


    @Test
    public void sellersTest() throws InterruptedException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        sellersPage.open();
        sellersPage.addNewSeller();

        sellersPage.save();
        Assert.assertTrue(sellersPage.isAlertPresent()); //поля обязательны для заполнения
        sellersPage.fillSellersFields();
        sellersPage.save();

        sellersPage.openCreatedSeller();
        sellersPage.changeData();

        sellersPage.setPayAccount();
    }
}
