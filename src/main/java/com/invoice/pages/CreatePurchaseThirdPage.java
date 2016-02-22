package com.invoice.pages;

import com.invoice.utils.UtilStore;
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

    @FindBy(xpath = ".//*[@id='select2-chosen-10']")
    WebElement fieldChooseProducer;

    @FindBy(name = "price_product")
    WebElement fieldProductPrice;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[10]/div/div/div[3]/button[1]")
    WebElement buttonOk;
    //Закончились элементы формы добавления продукта
    //

    @FindBy(xpath = ".//*[@id='buttons_div']/a[1]")
    WebElement buttonSave;

    @FindBy(id = "save_and_purchase")
    WebElement buttonSaveAndInitiate;

    @FindBy(id = "save_an_deliv")
    WebElement buttonSaveAndOrder;

    @FindBy(name = "date_transit_form")
    WebElement fieldCalendar;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement cellNeededDate;

    @FindBy(id = "ui-datepicker-div")
    WebElement fieldWholeCalendar; //not used

    @FindBy(className = "btn-success")
    WebElement buttonOkOnTransit;

    @FindBy(className = "qq-upload-button")
    WebElement buttonUpload;

    @FindBy(xpath = ".//*[@id='file_upload_div_invoicefact_date']/div/div[2]/input")
    WebElement fieldUploadFile;



    public void addProduct() throws InterruptedException {
        buttonPlus.click();
        Thread.sleep(1000);

    }

    public void save() {
        buttonSave.click();
    }

    public void fillProductForm() throws InterruptedException {

        typeHere(fieldProductName, UtilStore.nameProduct);
        System.out.println("nameproduct " + UtilStore.nameProduct);
        typeHere(fieldProductCode, "607" + UtilStore.addDate());
        fieldCategory.click();
        Thread.sleep(500);
        fieldCategoryHighlighted.click();
        typeHere(fieldRecommendedProductPrice, "2");
        typeHere(fieldProductPrice, "40");
        buttonOkOnTransit.click();
    }

    public void saveAndInitiate() throws InterruptedException {
        Thread.sleep(2000);
        buttonSaveAndOrder.click();
        Thread.sleep(2000);

    }

    public void upl() throws InterruptedException {

        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            fieldUploadFile.sendKeys(pathLinux);
        } else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            fieldUploadFile.sendKeys(pathWindows);
        }
    }

    public CreatePurchaseFourthPage sendToTransit() throws InterruptedException {
        Thread.sleep(1000);
        buttonOkOnTransit.click();
        return PageFactory.initElements(driver, CreatePurchaseFourthPage.class);
    }


    public CreatePurchaseThirdPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void setCalendar() throws InterruptedException {
        Thread.sleep(1000);
        fieldCalendar.click();
        cellNeededDate.click();
    }
}
