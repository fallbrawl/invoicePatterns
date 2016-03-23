package invoice;

import com.invoice.pages.*;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by paul on 03.03.16.
 */
public class AccessRightsTest extends BasicTestCase {

    //    http://screencast.com/t/6W0ILtC6aMGh
    //    Суть в чем. Администратором создаешь нового пользователя - Менеджера по продажам.
    //    У него в настройках укажи клиента, чтобы документы на странице заказы на продажу отображались (можешь указать моего - Продавец админ).
    //    Потом администратором заходишь на страницу Типы пользователей, а потом переходишь на страницу прав доступа для менеджера по продажам.
    //    Там есть куча галочек просмотра, редактирования, удаления чего-либо.
    //    Желательно проверить все. Если это какая-то кнопка удаления, добавления или редактирования - она, при отсутствиии прав, будет полупрозрачной и неативной.
    //    Если это страница партии, или страница счета/закупки - то у тебя должен появляться котик с 403 ошибкой.
    //    В идеале также проверить доступы по ссылкам.
    //    Т.е. там где ты авторизован администратором, копируешь ссылку страницы, на которую у менеджера по продажам нет прав,
    //    и пытаешься перейти на эту страницу этим самым менеджером по продажам.
    //    В общем тест обширный, я сняла только часть.
    //    Если будет что-то непонятно - говори.

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private AddNewUserPage addNewUserPage = PageFactory.initElements(getWebDriver(), AddNewUserPage.class);
    private UsersPage usersPage = PageFactory.initElements(getWebDriver(), UsersPage.class);
    private ProfilePage profilePage = PageFactory.initElements(getWebDriver(), ProfilePage.class);
    private UserTypePage userTypePage  = PageFactory.initElements(getWebDriver(), UserTypePage.class);
    private MainPage mainPage;

    @Test
    public void accessRightsTest() throws InterruptedException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        usersPage.open();
        addNewUserPage = usersPage.addNewUser();

        addNewUserPage.fillNewUserForm();
        usersPage = addNewUserPage.saveNewUser();

        Assert.assertTrue(usersPage.textNewUserEmail.getText().contains(UtilStore.userEmail + "@mail.ru")); //Проверка на то что новый пользователь создан
        usersPage.logout();

        loginPage.loginAs(addNewUserPage.getNewUserEmail(), addNewUserPage.getNewUserPassword()); //Логин под свежесозданным пользователем
        profilePage.open();
        profilePage.selectClient();
        profilePage.saveProfile();

        //driver.quit();


    }
}
