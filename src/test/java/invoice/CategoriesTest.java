package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.AddDocumentFirstPage;
import com.invoice.pages.AddDocumentPages.AddDocumentFourthPage;
import com.invoice.pages.AddDocumentPages.AddDocumentSecondPage;
import com.invoice.pages.AddDocumentPages.AddDocumentThirdPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

/**
 * Created by paul on 27.04.16.
 */

//        [14:57:16] Юлия: http://screencast.com/t/8uxfBO1piAd
//        [14:57:20] Юлия: Категории


public class CategoriesTest extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddNewUserPage addNewUserPage = PageFactory.initElements(getWebDriver(), AddNewUserPage.class);
    private UsersPage usersPage = PageFactory.initElements(getWebDriver(), UsersPage.class);
    private ProfilePage profilePage = PageFactory.initElements(getWebDriver(), ProfilePage.class);
    private AddDocumentFirstPage firstPage = PageFactory.initElements(getWebDriver(), AddDocumentFirstPage.class);
    private ProductsPage productsPage = PageFactory.initElements(getWebDriver(), ProductsPage.class);
    private CategoriesPage categoriesPage = PageFactory.initElements(getWebDriver(), CategoriesPage.class);
    private AddDocumentFourthPage fourthPage = PageFactory.initElements(getWebDriver(), AddDocumentFourthPage.class);

    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @org.testng.annotations.Test

    public void categoriesTest() throws InterruptedException {

        loginPage.open();
        loginPage.loginAs(admin);

        productsPage.open();
        productsPage.openProductsTree();

        //Добавляю родительскую категорию

//        categoriesPage.addCategory();
//        categoriesPage.enterNameOfCategory("Parent");
//        categoriesPage.saveCategory();
//
//        //Добавляю подкатегорию
//
//        categoriesPage.addCategory();
//        categoriesPage.enterNameOfCategory("Sub");
//        categoriesPage.setMutualCategory();
//        categoriesPage.saveCategory();

        categoriesPage.expandCreatedCategories();

    }
}
