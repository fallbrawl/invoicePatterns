package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 17.02.16.
 */
public class CreatePurchaseThirdPage extends Page {

    @FindBy(id = "autocomplete_document_product")
    WebElement fieldForName;

    @FindBy(className = "autocomplete-suggestion")
    WebElement fieldSuggestion;

    @FindBy(className = "icon_plus")
    WebElement buttonPlus;

    //
    //Элементы формы добавления продукта
    @FindBy(id = "name_product")
    WebElement fieldProductName;

    @FindBy(id = "code_product")
    WebElement fieldProductCode;

    @FindBy(className = "select2-choices")
    WebElement fieldCategory;

    @FindBy(className = "select2-highlighted")
    WebElement fieldCategoryHighlighted;

    @FindBy(id = "recprice_product_modal")
    WebElement fieldRecommendedProductPrice;

    //@FindBy (id = "select2-chosen-24")
    @FindBy(xpath = ".//*[@id='select2-chosen-10']")
    WebElement fieldChooseProducer;

    @FindBy(name = "price_product")
    //@FindBy (id = "price_product")
            WebElement fieldProductPrice;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[10]/div/div/div[3]/button[1]")
    //   @FindBy (className = "btn-success")
            WebElement buttonOk;
    //Закончились элементы формы добавления продукта
    //

    @FindBy(xpath = ".//*[@id='buttons_div']/a[1]")
    WebElement buttonSave;

    @FindBy(id = "save_and_purchase")
    WebElement buttonSaveAndInitiate;

    public void addProduct() throws InterruptedException {
        buttonPlus.click();
        Thread.sleep(1000);

    }

    public void save() {
        buttonSave.click();
    }

    public void fillProductForm() throws InterruptedException {
        typeHere(fieldProductName, "product " + UtilStore.addDate());
        typeHere(fieldProductCode, "607" + UtilStore.addDate());
        fieldCategory.click();
        Thread.sleep(500);
        fieldCategoryHighlighted.click();
        typeHere(fieldRecommendedProductPrice, "2");
        typeHere(fieldProductPrice, "40");
        buttonOk.click();
    }

    public AddDocumentFourthPage saveAndInitiate() throws InterruptedException {
        Thread.sleep(2000);
        buttonSaveAndInitiate.click();
        Thread.sleep(2000);
        return PageFactory.initElements(driver, AddDocumentFourthPage.class);
    }

    public CreatePurchaseThirdPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }
}
