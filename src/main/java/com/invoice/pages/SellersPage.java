package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by paul on 21.03.16.
 */
public class SellersPage extends Page {

    @FindBy(className = "btn-primary")
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

    @FindAll(@FindBy(className = "select2-result-label"))
    List<WebElement> dropdownElements;

    @FindBy(className = "submit_btn")
    WebElement buttonSave;

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

    public void changeData() throws InterruptedException {
        Thread.sleep(1000);
        buttonChangeDataSeller.click();
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
        }
        else return false;

    }

    public void setPayAccount() throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldPayAccount, "4456456");
        Thread.sleep(500);

    }
}
