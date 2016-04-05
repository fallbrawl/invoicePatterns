package invoice;

import com.invoice.pages.*;
import com.invoice.pages.AddDocumentPages.*;
import com.invoice.pages.ExpensesPages.ExpensesPage;
import com.invoice.pages.ExpensesPages.ExpensesTypePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by paul on 04.04.16.
 */
public class ArticlesTest extends BasicTestCase {
    //    [11:16:13] Юлия: http://screencast.com/t/2sSmDn5R
//    [11:16:21] Юлия: вот скринкаст
//    [11:17:09] Юлия: По статьям и типам расходов. Типы это что-то общее для статей. Когда заводишь статью - можно выбрать тип, к которому она относится
//    [11:17:27] Юлия: На видео есть бок
//    [11:17:58] Юлия: тут при создании статьи пользователь не переходит на страницу "Статьи расходов"
//    [11:18:04] Юлия: это сегодня сделают
//    [11:19:01] Юлия: На видео нет этого, но, после создания, и статьи и типы должны отображаться в доп затратах на Шаге 3 счета, Шаге 3 закупки и Шаге 7 закупки Эгоистом
    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private ExpensesTypePage expensesTypePage = PageFactory.initElements(getWebDriver(), ExpensesTypePage.class);
    private SellersPage sellersPage = PageFactory.initElements(getWebDriver(), SellersPage.class);
    private ExpensesPage expensesPage = PageFactory.initElements(getWebDriver(), ExpensesPage.class);

    private MainPage mainPage;
    private AddDocumentSecondPage secondPage;
    private AddDocumentThirdPage thirdPage;

    @Test

    public void articlesTest() throws InterruptedException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        expensesTypePage.open();
        expensesTypePage.addExpenseType();
        expensesTypePage.fillFieldsOfNewExpenseType();
        expensesTypePage.save();
        expensesTypePage.toTheLastPage();
        expensesTypePage.editCreatedExpenseType();
        expensesTypePage.changeNameOfExpenseType();
        expensesTypePage.save();
        expensesTypePage.toTheLastPage();
        Assert.assertTrue(expensesTypePage.isNameChanged());
        expensesTypePage.deleteCreatedExpenseType();
        Assert.assertTrue(expensesTypePage.isTypeDeleted());
        expensesTypePage.addExpenseType();
        expensesTypePage.fillFieldsOfNewExpenseType();
        expensesTypePage.save();

        expensesPage.open();
        expensesPage.addNewExpense();
        expensesPage.fillNewExpenseFields();
        expensesPage.save();
        expensesPage.toTheLastPage();
        expensesPage.deleteExpense();
        Assert.assertTrue(expensesPage.isExpenseDeleted());

    }



}
