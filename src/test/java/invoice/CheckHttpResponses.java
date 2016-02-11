package invoice;

import com.invoice.utils.UtilStore;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by paul on 11.02.16.
 */
public class CheckHttpResponses extends BasicTestCase {
    @Test
    public void checkPesponse() {

        //Заказы на продажу
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/document"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/document/add"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/document_delivery"));

        //Заказы на закупку
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/purchase"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/purchase/add-transit/0"));

        //Логистика
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/pitch/move"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/pitch"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/pitch/reserve"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/transit/movement"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/warehouse"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/products"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/manufacturer"));

        //Бухгалтерия
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/accounting/manager"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/accounting/archive"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/dynamic_payments"));

        //Клиенты
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/user_information/add-edit?type_user_info=3"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/user_information/buyers"));

        //Поставщики
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/user_information/add-edit?type_user_info="));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/user_information/vendors"));

        //Финансы
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/system_financial_state"));

        //Центр затрат
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/products?extra_expense=1"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/transit/list?internal_operation=1"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/extra_expenses_type"));

        //Настройки
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/user_information/sellers"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/users"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/user_type"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/measure"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/category"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/products/excel"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/logger"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/journal/list"));
        Assert.assertTrue(UtilStore.checkHttpResponseCode("http://invoicedev.php.attractgroup.com/public/settings"));
    }
}
