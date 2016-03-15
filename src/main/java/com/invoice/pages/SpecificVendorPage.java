package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 12.03.16.
 */
public class SpecificVendorPage extends Page {

    private boolean isDocumentDeleted;

    @FindBy(xpath = "//*[@id=\"body-wrapper\"]/div[1]/div/div[3]/section/div[2]/div[1]/ol/li[2]")
    WebElement textForAssert;

    @FindBy(linkText = "Изменить данные")
    WebElement buttonChangeData;

    @FindBy(name = "fact_address")
    WebElement fieldVendorAddress;

    @FindBy(className = "submit_btn")
    WebElement buttonSave;

    @FindBy(name = "file")
    WebElement formUploadDocumentForVendor;

    @FindBy(xpath = "//*[@id=\"body-wrapper\"]/div[4]/div/div/div[2]/div[3]/form[2]/div/a")
    WebElement buttonSaveNewDocument;

    @FindBy(xpath = "//*[@id=\"body-wrapper\"]/div[1]/div/div[3]/section/div[2]/div[1]/div[1]/a[1]")
    WebElement buttonDocuments;

    @FindBy(className = "modal")
    WebElement modal;

    @FindBy(name = "file_delay")
    WebElement fieldDelay;

    @FindBy(linkText = "Поставщики")
    WebElement linkVendorsList;

    @FindBy(css = "#agreement-table > tbody > tr > td:nth-child(7) > a ")
    //@FindBy(xpath = "//*[@id=\"agreement-table\"]/tbody/tr/td[7]/a[2]")
            //@FindBy(className = "icon_del_big_red")
            WebElement buttonDeleteDocument;

    @FindBy(css = "#body-wrapper > div.modal.fade.in > div > div > div.modal-footer > button.btn.btn-sm.btn-success")
    WebElement buttonOkDelete;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td/a")
    WebElement cellFirstDayOfTheNextWeekInCalendar;

    @FindBy(name = "date_to_file")
    WebElement fieldDateForVendorAgreement;

    @FindBy(xpath = "//*[@id=\"agreement-table\"]/tbody/tr/td[5]")
    WebElement fieldTypeOfAgreement;


    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td[1]/div/a")
    WebElement linkAccountVendor;

    @FindBy(className = "btn-danger")
    WebElement buttonDeleteUser;

    @FindBy(css = "#body-wrapper > div.modal.fade.in > div > div > div.modal-footer > button.btn.btn-sm.btn-success")
    WebElement buttonConfirmDeleteUser;


    public boolean ensureThatOpenedRightVendor() {
        System.out.println("Редактирование поставщика " + "\"" + "vendor " + UtilStore.nameProduct + "\"");
        System.out.println(textForAssert.getText());
        if (textForAssert.getText().equals("Редактирование поставщика " + "\"" + "vendor " + UtilStore.nameProduct + "\"")) {
            return true;
        } else return false;
    }

    public SpecificVendorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void enableEditingVendorProperties() throws InterruptedException {
        Thread.sleep(1000);
        buttonChangeData.click();
    }

    public void enterNewAddress() {
        typeHere(fieldVendorAddress, UtilStore.nameProduct);
    }

    public ListVendorPage save() throws InterruptedException {
        Thread.sleep(1000);
        buttonSave.click();
        return PageFactory.initElements(driver, ListVendorPage.class);
    }

    public boolean ensureThatDataIsSaved() {
        if (fieldVendorAddress.getText().equals(UtilStore.nameProduct))
            return true;
        else return false;
    }

    public void openDocuments() throws InterruptedException {
        Thread.sleep(1000);
        buttonDocuments.click();
    }

    public void addNewDocument() {
        uploadFile(formUploadDocumentForVendor);
    }

    public void setDelay() {
        typeHere(fieldDelay, "10");
    }

    public void setDate() throws InterruptedException {
        Thread.sleep(1000);
        fieldDateForVendorAgreement.click();
        Thread.sleep(1000);
        cellFirstDayOfTheNextWeekInCalendar.click();

    }

    public void saveDocument() throws InterruptedException {
        Thread.sleep(1000);
        buttonSaveNewDocument.click();
        UtilStore.reload(driver);
        Thread.sleep(1000);

    }

    public void deleteDocument() throws InterruptedException {
//        Thread.sleep(4000);
  //      System.out.println(driver.getPageSource());
        Thread.sleep(1000);
        buttonDeleteDocument.click();
        Thread.sleep(1000);
        buttonOkDelete.click();
        Thread.sleep(1000);
        UtilStore.reload(driver);
    }

    public boolean ensureDocumentDeleted() {
        try {
            buttonDeleteDocument.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    public void goToVendorsList() throws InterruptedException {
        UtilStore.reload(driver);
        Thread.sleep(1000);
        linkVendorsList.click();
    }

    public void deleteUser() throws InterruptedException {
        Thread.sleep(1000);
        buttonDeleteUser.click();
        Thread.sleep(1000);
        buttonConfirmDeleteUser.click();

    }


}
