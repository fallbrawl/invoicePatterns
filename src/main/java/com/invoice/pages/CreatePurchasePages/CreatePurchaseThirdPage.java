package com.invoice.pages.CreatePurchasePages;

import com.invoice.pages.Page;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paul on 17.02.16.
 */
public class CreatePurchaseThirdPage extends Page {

    public String documentBill = null;

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


    //Закончились элементы формы добавления продукта
    //

    @FindBy(xpath = ".//*[@id='buttons_div']/a[1]")
    WebElement buttonSave;

    @FindBy(id = "save_and_purchase")
    WebElement buttonSaveAndInitiate;

    @FindBy(id = "save_an_deliv")
    WebElement buttonSaveAndOrder;

    @FindBy(name = "date_transit_form")
    WebElement fieldCalendarTransit;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement cellCalendarDateToday;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[1]/a")
    //@FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[1]/span")
            WebElement getCellCalendarDateFirstDayOfTHeSecondWeekOnTheNextMonth;

    @FindBy(css = "li.active")
    WebElement textNumber;

    @FindBy(id = "ui-datepicker-div")
    WebElement fieldWholeCalendar; //not used

    @FindAll(@FindBy(className = "btn-success"))
    List<WebElement> buttonOArraykOnTransit;

    @FindBy(xpath = ".//*[@id='form-add_edit_product']/div[3]/div/a")
    WebElement buttonAddCategory;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/div/a[2]/span")
    public WebElement buttonGoToTheNextMonthInCalendar;

    @FindBy(className = "qq-upload-button")
    WebElement buttonUpload;

    @FindBy(xpath = ".//*[@id='load-content']/div/a[2]")
    WebElement buttonAcceptNewCategoryName;

    @FindBy(id = "name_category")
    WebElement fieldEnterNewCategoryName;

    @FindBy(xpath = ".//*[@id='file_upload_div_invoicefact_date']/div/div[2]/input")
    WebElement fieldUploadFile;

    @FindBy(className = "product_add_document_recalc")
    WebElement fieldNumberOfItems;

    @FindBy(id = "pay_transit")
    WebElement buttonAttachInvoice;

    public void addProduct() throws InterruptedException {
        buttonPlus.click();
        Thread.sleep(1000);

    }

    public void save() {
        buttonSave.click();
    }

    public void extractNumber() {

        String mydata = textNumber.getText();

        System.out.println("Text is : " + mydata);

        Pattern pattern = Pattern.compile("S[\\d -]*-\\d");
        Matcher matcher = pattern.matcher(mydata);

        if (matcher.find()) {
            documentBill = matcher.group(0);
            System.out.println("EXTRACTED regex expression " + documentBill);
        } else {
            System.out.println("NOTHING WAS FOUND ");
        }
    }

    public void setNumberOfItems(String howMuch) throws Exception {

        //If you'll be processing the number as text, then change:

        if (howMuch.matches("[0-9]+") && howMuch.length() > 0) {
            typeHere(fieldNumberOfItems, "100");
        } else {
            throw new Exception("Enter a number!");
        }
    }

    public void fillProductForm() throws InterruptedException {

        typeHere(fieldProductName, UtilStore.nameProduct);
        System.out.println("nameproduct " + UtilStore.nameProduct);
        typeHere(fieldProductCode, "607" + UtilStore.addExtendedDate());

        //fieldCategory.click();
        Thread.sleep(500);
        buttonAddCategory.click();
        Thread.sleep(500);
        typeHere(fieldEnterNewCategoryName, "dfjgdk");
        Thread.sleep(500);
        buttonAcceptNewCategoryName.click();
        Thread.sleep(500);

        // fieldCategoryHighlighted.click();
        typeHere(fieldRecommendedProductPrice, "2");
        typeHere(fieldProductPrice, "40");
        Thread.sleep(500);
        WebElement wow = driver.findElement(By.id("modal_success"));
        Thread.sleep(500);
        wow.click();
        Thread.sleep(500);
        wow.click();
        Thread.sleep(500);
        wow.click();
        Thread.sleep(500);
        wow.click();
    }

    public void saveAndInitiate() throws InterruptedException {
        Thread.sleep(1500);
        buttonAttachInvoice.click();
        Thread.sleep(1000);

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
        Thread.sleep(2000);
        buttonOk.click();
        return PageFactory.initElements(driver, CreatePurchaseFourthPage.class);
    }


    public CreatePurchaseThirdPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void setCalendar(String whatDate) throws InterruptedException {
        if (whatDate.equals("thisDay")) {
            Thread.sleep(500);
            fieldCalendarTransit.click();
            cellCalendarDateToday.click();
        } else if (whatDate.equals("firstDayOfTHeSecondWeekOnTheNextMonth")) {
            Thread.sleep(500);
            fieldCalendarTransit.click();
            buttonGoToTheNextMonthInCalendar.click();
            Thread.sleep(500);
            getCellCalendarDateFirstDayOfTHeSecondWeekOnTheNextMonth.click();
        }
    }


    public void go() throws InterruptedException {
        Thread.sleep(1000);
        buttonOk.click();

    }

    public void saveAndOrder() throws InterruptedException {
        Thread.sleep(1000);
        buttonSaveAndOrder.click();
        Thread.sleep(500);
    }

    public void payTransit() throws InterruptedException {
        Thread.sleep(1000);
        buttonOArraykOnTransit.get(0).click();

    }


}
