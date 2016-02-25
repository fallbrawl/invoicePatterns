package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddDocumentThirdPage extends Page {

    @FindBy(id = "autocomplete_document_product")
    WebElement fieldForName;

    @FindBy(className = "autocomplete-suggestion")
    WebElement fieldSuggestion;

    @FindBy(className = "icon_plus")
    WebElement buttonPlus;

    @FindBy(xpath = ".//*[@id='buttons_div']/a[1]")
    WebElement buttonSave;
    //*[@id="buttons_div"]/a[1]  //*[@id="buttons_div"]/a[1]
    @FindBy(id = "save_and_purchase")
    WebElement buttonSaveAndInitiate;

    @FindBy(xpath = "//*[@id=\"body-wrapper\"]/div[10]/div/div/div[2]/div/a")
    WebElement buttonReserveAndInitiate;

    public void enterNameOfProduct() throws InterruptedException {
        fieldForName.click();
        typeHere(fieldForName, UtilStore.nameProduct);

    }

    public void enterExistingNameOfProduct() throws InterruptedException {
        fieldForName.click();
        typeHere(fieldForName, "product 2016/02/24 15:33:38");
    }

    public void addProduct() throws InterruptedException {
        Thread.sleep(2000);
        buttonPlus.click();
        Thread.sleep(500);

    }

    public void save() throws InterruptedException {

        buttonSave.click();
        Thread.sleep(500);
    }

    public AddDocumentFourthPage saveAndInitiate() throws InterruptedException {
        Thread.sleep(1000);
        buttonSaveAndInitiate.click();
        Thread.sleep(1000);
        return PageFactory.initElements(driver, AddDocumentFourthPage.class);
    }

    public void reserveAndInitiate() throws InterruptedException {
        Thread.sleep(1000);
        buttonReserveAndInitiate.click();
    }

    public AddDocumentThirdPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
    }


}
