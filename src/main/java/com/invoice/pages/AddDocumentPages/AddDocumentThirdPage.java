package com.invoice.pages.AddDocumentPages;

import com.invoice.pages.Page;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AddDocumentThirdPage extends Page {

    private int numberOfItems;

    @FindBy(id = "autocomplete_document_product")
    WebElement fieldForName;

    @FindBy(className = "autocomplete-suggestion")
    WebElement fieldSuggestion;

    @FindBy(className = "icon_plus")
    WebElement buttonPlus;

    @FindBy(id = "product-count")
    WebElement fieldNumberOfItems;

    @FindBy(xpath = ".//*[@id='buttons_div']/a[1]")
    WebElement buttonSave;

    @FindBy(id = "save_and_purchase")
    WebElement buttonSaveAndInitiate;

    @FindBy(xpath = "//*[@id=\"body-wrapper\"]/div[10]/div/div/div[2]/div/a")
    WebElement buttonReserveAndInitiate;

    @FindBy(id = "name_product")
    WebElement fieldProductName;

    @FindBy(id = "code_product")
    WebElement fieldProductCode;

    @FindBy(xpath = ".//*[@id='form-add_edit_product']/div[3]/div/a")
    WebElement buttonAddCategory;

    @FindBy(xpath = ".//*[@id='load-content']/div/a[2]")
    WebElement buttonAcceptNewCategoryName;

    @FindBy(id = "name_category")
    WebElement fieldEnterNewCategoryName;

    @FindBy(id = "recprice_product_modal")
    WebElement fieldRecommendedProductPrice;

    @FindBy(name = "price_product")
    WebElement fieldProductPrice;

    @FindBy(css = "#body-wrapper > div.modal.fade.in > div > div > div.modal-footer > button.btn.btn-sm.btn-success")
    WebElement buttonOkOnTransit;

    @FindBy(css = "#document_ready > div.message-footer.clearfix > div.normal_document_step.normal_document_step_3.step_3__checkboxs_wrapper.pull-left.nds_checkbox_wrapper > div:nth-child(4) > span")
    WebElement checkboxOfferService;

    @FindBy (xpath = "//*[@id=\"buttons_div\"]/span/button[3]")
    WebElement buttonSaveAndLoad;

    @FindBy(className = "available_by_gtr_span")
    WebElement textNumberOfItems;

    public int getNumberOfItems(){
        numberOfItems = Integer.parseInt(textNumberOfItems.getText());
        System.out.println("Number of itemns: " + numberOfItems);
        return numberOfItems;
    }

    public void enterNameOfProduct() throws InterruptedException {
        fieldForName.click();
        typeHere(fieldForName, UtilStore.nameProduct);

    }

    public void enterExistingNameOfProduct() throws InterruptedException {
        fieldForName.click();
        //System.out.println("nameproduct " + UtilStore.nameProduct);
        typeHere(fieldForName, "product 2016/03/01 17:36:19");
    }

    public void setOfferService() throws InterruptedException {
        Thread.sleep(1000);
        checkboxOfferService.click();
    }

    public void addProduct() throws InterruptedException {
        Thread.sleep(2000);
        buttonPlus.click();
        Thread.sleep(500);

    }

    public void setNumberOfItems(){
        typeHere(fieldNumberOfItems, "1");
    }

    public void save() throws InterruptedException {
        Thread.sleep(500);
        UtilStore.reload(driver);
        buttonSave.click();
        Thread.sleep(500);
    }

    public void fillProductForm() throws InterruptedException {

        typeHere(fieldProductName, UtilStore.nameProduct);
        System.out.println("nameproduct " + UtilStore.nameProduct);
        typeHere(fieldProductCode, "607" + UtilStore.addDateForProduct());

        Thread.sleep(500);
        buttonAddCategory.click();
        Thread.sleep(500);
        typeHere(fieldEnterNewCategoryName, "dfjgdk");
        Thread.sleep(500);
        buttonAcceptNewCategoryName.click();
        Thread.sleep(500);

        typeHere(fieldRecommendedProductPrice, "2");
        typeHere(fieldProductPrice, "40");
        buttonOkOnTransit.click();
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

    public AddDocumentNinethPage saveAndLoad() throws InterruptedException {
        Thread.sleep(1000);
        buttonSaveAndLoad.click();
        return PageFactory.initElements(driver, AddDocumentNinethPage.class);
    }

    public AddDocumentThirdPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
    }


}
