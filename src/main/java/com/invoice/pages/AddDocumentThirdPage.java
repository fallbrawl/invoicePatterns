package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by paul on 04.02.16.
 */
public class AddDocumentThirdPage extends Page{

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
        typeHere(fieldForName, UtilStore.nameProduct);
        Thread.sleep(1000);
        //typeHere(fieldForName, Keys.ARROW_DOWN);
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

    public AddDocumentFourthPage saveAndInitiate() throws InterruptedException {
        Thread.sleep(2000);
        buttonSaveAndInitiate.click();
        Thread.sleep(2000);
        return PageFactory.initElements(driver, AddDocumentFourthPage.class);
    }

    public AddDocumentThirdPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
    }


}
