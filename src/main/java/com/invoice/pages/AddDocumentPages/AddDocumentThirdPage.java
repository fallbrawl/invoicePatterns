package com.invoice.pages.AddDocumentPages;

import com.invoice.pages.Page;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.Keys;
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

    @FindBy(id = "save_this_document")
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
        typeHere(fieldForName, UtilStore.nameProduct);
        Thread.sleep(750);
        typeHere(fieldNumberOfItems, "5");
    }

    public void setOfferService() throws InterruptedException {
        Thread.sleep(1000);
        checkboxOfferService.click();
    }

    public void addProduct() throws InterruptedException {
        Thread.sleep(1500);
        buttonPlus.click();
        Thread.sleep(750);

    }

    public void setNumberOfItems(String numberOfItems) throws InterruptedException {
        typeHere(fieldNumberOfItems, Keys.BACK_SPACE);
        typeHere(fieldNumberOfItems, numberOfItems);
    }

    public void save() throws InterruptedException {
        Thread.sleep(1000);
        UtilStore.reload(driver);
        Thread.sleep(1000);
        buttonSave.click();
        Thread.sleep(1000);
    }

    public void fillProductForm() throws InterruptedException {

        typeHere(fieldProductName, UtilStore.nameProduct);
        System.out.println("nameproduct " + UtilStore.nameProduct);
        typeHere(fieldProductCode, "607" + UtilStore.addExtendedDate());
        typeHere(fieldNumberOfItems, "5");

        Thread.sleep(750);
        buttonAddCategory.click();
        Thread.sleep(750);
        typeHere(fieldEnterNewCategoryName, "dfjgdk");
        Thread.sleep(750);
        buttonAcceptNewCategoryName.click();
        Thread.sleep(750);

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
