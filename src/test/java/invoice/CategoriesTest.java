package invoice;

import com.invoice.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by paul on 27.04.16.
 */

//        [14:57:16] Юлия: http://screencast.com/t/8uxfBO1piAd
//        [14:57:20] Юлия: Категории


public class CategoriesTest extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private ProductsPage productsPage = PageFactory.initElements(getWebDriver(), ProductsPage.class);

    private CategoriesPage categoriesPage = PageFactory.initElements(getWebDriver(), CategoriesPage.class);
    private EditCategoriesPage editCategoriesPage = PageFactory.initElements(getWebDriver(), EditCategoriesPage.class);

    private MainPage mainPage;


    @org.testng.annotations.Test

    public void categoriesTest() throws InterruptedException {

        loginPage.open();
        loginPage.loginAs(admin);

        productsPage.open();
        productsPage.openProductsTree();

        //Добавляю родительскую категорию

        editCategoriesPage.addCategory();
        editCategoriesPage.enterNameOfCategory("Parent");
        editCategoriesPage.saveCategory();

        //Добавляю подкатегорию

        editCategoriesPage.addCategory();
        editCategoriesPage.enterNameOfCategory("Sub");

        editCategoriesPage.setMutualCategory();
        editCategoriesPage.saveCategory();

        categoriesPage.expandCreatedParentCategory(1);
        categoriesPage.enterNameOfCategoryInTree("SubSub");

        categoriesPage.expandCreatedSubCategories();
        categoriesPage.createSubSubCategory();
        categoriesPage.enterNameOfCategoryInTree("SubSubSub");

        //Удаляю

        categoriesPage.expandCreatedSubSubCategories();
        categoriesPage.deleteSubSubCategory();
        Assert.assertTrue(!categoriesPage.isSubCategoriesNotDeleted());

        //Редактирую

        categoriesPage.editSubCategoryName();
        categoriesPage.enterNameOfCategoryInTree("SubEdited");
        Assert.assertTrue(categoriesPage.isCategoryEdited());

        //Удаляю всё

        categoriesPage.deleteParentCategory();


    }
}
