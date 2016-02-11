package com.invoice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 04.02.16.
 */
public class AddDocumentThirdPage extends Page{

    //Addin' an item
//
//    UtilityStore.waitForElementsAbsence(drv, 2, "loader_wrapper");
//    drv.findElement(By.id("autocomplete_document_product")).sendKeys("n");
//    UtilityStore.waitForElementsPresence(drv, 2, "autocomplete-suggestion", 'c');
//    drv.findElement(By.id("autocomplete_document_product")).sendKeys(Keys.ARROW_DOWN);
//    drv.findElement(By.id("autocomplete_document_product")).sendKeys(Keys.ENTER);
//    UtilityStore.waitForElementsPresence(drv, 2, "pull-right", 'c');
//    drv.findElement(By.className("icon_plus")).click();
//
//    UtilityStore.waitForElementsAbsence(drv, 2, "loader_wrapper");
//    drv.findElement(By.cssSelector(".btn.btn-primary.btn-high.normal_document_step.normal_document_step_3")).click();
//    UtilityStore.waitForElementsAbsence(drv, 3, "loader_wrapper");
//
//    //
//    //Loggin' out and in like on Accounter's role to continue
//    //
//
//    UtilityStore.logout(drv);
//    UtilityStore.login(drv, "accounter");
//
//    drv.get("http://invoicedev.php.attractgroup.com/public/dynamic_payments");
//    drv.findElement(By.linkText(documentName)).click();

    @FindBy (id = "autocomplete_document_product")
    WebElement fieldForName;

    @FindBy (className = "autocomplete-suggestion")
    WebElement fieldSuggestion;

    @FindBy (className = "icon_plus")
    WebElement buttonPlus;

    @FindBy (xpath = ".//*[@id='buttons_div']/a[1]")
    WebElement buttonSave;

    @FindBy (id = "save_and_purchase")
    WebElement buttonSaveAndInitiate;

    public void enterNameOfProduct() throws InterruptedException {
        fieldForName.click();
        typeHere(fieldForName,"Яб");
        Thread.sleep(1000);
        typeHere(fieldForName, Keys.ARROW_DOWN);
        typeHere(fieldForName, Keys.ENTER);
    }

    public void addProduct() throws InterruptedException {
        buttonPlus.click();
        Thread.sleep(1000);
        buttonSave.click();

    }

    public void save(){
        buttonSave.click();
    }

    public void saveAndInitiate() throws InterruptedException {
        Thread.sleep(2000);
        buttonSaveAndInitiate.click();
        Thread.sleep(2000);
    }

    public AddDocumentThirdPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
    }


}
