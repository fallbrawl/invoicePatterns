package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by paul on 21.03.16.
 */
public class SellersPage extends Page {

    @FindBy(className = "btn-warning")
    WebElement buttonAddSeller;

    @FindBy(id = "name_user_info")
    WebElement fieldSellerName;

    @FindBy(id = "seller_balance")
    WebElement fieldSellerBalance;

    @FindBy(className = "select_managers_user_info")
    WebElement dropdownManagers;

    @FindBy(className = "select_warehouses_user_info")
    WebElement dropdownStores;

    @FindBy(className = "btn-success")
    WebElement buttonChangeDataSeller;

    @FindBy(id = "pay_account")
    WebElement fieldPayAccount;

    @FindBy(name = "credit_limit")
    WebElement fieldCreditLimit;

    @FindAll(@FindBy(className = "select2-result-label"))
    List<WebElement> dropdownElements;

    @FindBy(className = "submit_btn")
    WebElement buttonSave;

    @FindBy(className = "btn-danger")
    WebElement buttonDelete;

    @FindBy(id = "modal_success")
    WebElement buttonOk;

    @FindBy(linkText = "Оплатить")
    WebElement linkOpenSpecificPayments;

    @FindBy(linkText = "Привязать платеж")
    WebElement linkAttachSpecificPayments;

    @FindBy(name = "sum_payment")
    WebElement fieldSetSumOfPayment;

    @FindBy(id = "file_text")
    WebElement fieldNumberOfPaymentDocument;

    @FindAll(@FindBy(className = "label-checkbox"))
    List<WebElement> buttonArraySwitchBetweenSellAndBuy;

    @FindBy(name = "date_form")
    WebElement fieldCalendar;

    @FindBy(name = "file")
    WebElement formForFile;

    @FindBy(linkText = "Возврат средств")
    WebElement buttonOpenSpecificReturn;

    public SellersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("sellers.url"));
    }

    public void addNewSeller() throws InterruptedException {
        Thread.sleep(500);
        buttonAddSeller.click();
        Thread.sleep(500);
    }

    public void changeCreditLimit() throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldCreditLimit, Keys.ARROW_LEFT);
        Thread.sleep(500);
        typeHere(fieldCreditLimit, Keys.ARROW_LEFT);
        Thread.sleep(500);
        typeHere(fieldCreditLimit, Keys.ARROW_LEFT);
        fieldCreditLimit.sendKeys("3");
    }

    public void changeData() throws InterruptedException {
        Thread.sleep(500);
        buttonChangeDataSeller.click();
        Thread.sleep(500);

    }

    public void fillSellersFields() throws InterruptedException {

        Thread.sleep(1000);

        typeHere(fieldSellerName, UtilStore.nameOfDocument1);
        Thread.sleep(500);

        typeHere(fieldSellerBalance, "10000000");
        Thread.sleep(500);

        dropdownManagers.click();
        Thread.sleep(500);
        dropdownElements.get(1).click();
        Thread.sleep(500);

        dropdownManagers.click();
        Thread.sleep(500);
        dropdownElements.get(0).click();
        Thread.sleep(500);

        dropdownStores.click();
        Thread.sleep(500);
        dropdownElements.get(1).click();

        dropdownStores.click();
        Thread.sleep(500);
        dropdownElements.get(0).click();

        Thread.sleep(500);
    }

    public void confirmAdding() throws InterruptedException {
        Thread.sleep(1000);
        buttonSave.click();
    }

    public void openCreatedSeller() throws InterruptedException {
        Thread.sleep(1000);
        // from ryba       WebElement needed = driver.findElement(By.xpath(".//td[position() = 1]/div[text() = '" + UtilsStore.getEmailNewUsername() + "']/ancestor::tr/td[last()]/div/a[contains(@class,'icon-remove')]"));
        WebElement createdSeller = driver.findElement(By.xpath(".//td[position() = 1]/div/a[text() = '" + UtilStore.nameOfDocument1 + "']"));
        createdSeller.click();
    }

    public void setBalance() throws InterruptedException {
        Thread.sleep(1000);
        typeHere(fieldSellerBalance, "1000000");
    }

    public void save() throws InterruptedException {
        Thread.sleep(500);
        buttonSave.click();
    }

    public boolean isAlertPresent() throws InterruptedException {
        Thread.sleep(1000);
        int count = StringUtils.countMatches(driver.getPageSource(), "обязательно для заполнения");
        System.out.println("Count " + count);
        if (count == 3) {
            return true;
        } else return false;
    }

    public void setPayAccount() throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldPayAccount, "4456456");
        Thread.sleep(500);

    }

    public void openBalance() throws InterruptedException {
        Thread.sleep(500);
        WebElement a = driver.findElement(By.xpath(".//td/div/a[text() = '" + UtilStore.nameOfDocument1 + "']/../../../td[3]/div/a"));
//       WebElement a = driver.findElement(By.xpath(".//td/div/a[text() = '" + "documentcreated1 2016/04/1909:31:18" + "']/../../../td[3]/div/a"));
        System.out.println(a.getText());
        a.click();
    }

    public void openReturn() throws InterruptedException {
        Thread.sleep(500);
        WebElement a = driver.findElement(By.xpath(".//td/div/a[text() = '" + UtilStore.nameOfDocument1 + "']/../../../td[4]/div/a"));
        System.out.println(a.getText());
        a.click();

    }

    public boolean isBalanceOpened() throws InterruptedException {
        Thread.sleep(1000);
        if (driver.getPageSource().contains("Баланс моего юридического лица")) {
            return true;
        } else
            return false;
    }

    public boolean isReturnsOpened() throws InterruptedException {
        Thread.sleep(1000);
        if (driver.getPageSource().contains("Возврат средств моему юридическому лицу")) {
            return true;
        } else
            return false;
    }

    public void deleteSeller() throws InterruptedException {
        Thread.sleep(1000);
        buttonDelete.click();
        Thread.sleep(1000);
        buttonOk.click();
        Thread.sleep(1000);
    }

    public boolean isUserSuccessfullyDeleted() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("NameDOc "+UtilStore.nameOfDocument1);
        if (driver.getPageSource().contains(UtilStore.nameOfDocument1)) {
            return false;
        } else {
            return true;
        }
    }

    public void switchToBuy() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("eeee" + buttonArraySwitchBetweenSellAndBuy.size());
        buttonArraySwitchBetweenSellAndBuy.get(1).click();

    }

    public void uploadFile() throws InterruptedException {
        Thread.sleep(1000);
        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        //Вставить путь для Windows
        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            formForFile.sendKeys(pathLinux);
        } else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            formForFile.sendKeys(pathWindows);
        }
    }

    public void makeAPay() throws InterruptedException {
        Thread.sleep(500);
        linkOpenSpecificPayments.click();
        Thread.sleep(500);
    }

    public void payForItems(String howMuch) throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldSetSumOfPayment, howMuch);
        Thread.sleep(500);
        typeHere(fieldNumberOfPaymentDocument,"43534");
        Thread.sleep(500);
        fieldCalendar.click();
        Thread.sleep(500);
        driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")).click();
        Thread.sleep(500);

    }

    public void makeAReturn() throws InterruptedException {
        Thread.sleep(500);
        buttonOpenSpecificReturn.click();

    }


    public void attachAPay() throws InterruptedException {
        Thread.sleep(1000);
        linkAttachSpecificPayments.click();
        Thread.sleep(1000);
    }
}
