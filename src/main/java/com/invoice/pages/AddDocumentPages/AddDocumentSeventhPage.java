package com.invoice.pages.AddDocumentPages;

import com.invoice.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 24.02.16.
 */
public class AddDocumentSeventhPage extends Page {

    @FindBy(id = "pay_transit")
    WebElement buttonPayTransit;

    @FindBy(name = "sum_payment")
    WebElement inputSum;

    @FindBy(name = "file_text")
    WebElement inputNumber;

    @FindBy(id = "save_an_deliv")
    WebElement buttonSaveAndDelivery;

    @FindBy(name = "date_form")
    WebElement inputDate;

    @FindBy(name = "date_transit_form")
    WebElement fieldCalendarTransit;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement cellCalendarDateToday;

    @FindBy(xpath = ".//*[@id='file_upload_div_1']/div/div[2]/input")
    WebElement inputFile;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement neededDate;

    @FindBy(className = "btn-success")
    WebElement buttonOk;



    @FindBy(xpath = ".//*[@id='file_upload_div_invoicefact_date']/div/div[2]/input")
    WebElement fieldUploadFile;


    public void setValuesPayWindow() throws InterruptedException {
        Thread.sleep(1000);
        typeHere(inputSum, "345435");
        typeHere(inputNumber, "345345");
        inputDate.click();
        neededDate.click();
    }

    public void uploadFile1() {
        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            inputFile.sendKeys(pathLinux);
        } else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            inputFile.sendKeys(pathWindows);
        }
    }

    public void confirmPayment() {
        buttonOk.click();

    }

    public void saveAndDelivery() {
        buttonSaveAndDelivery.click();

    }

    public AddDocumentSeventhPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void openPayWindow() throws InterruptedException {
        Thread.sleep(1000);
        buttonPayTransit.click();
        Thread.sleep(1000);
    }

    public void fillDeliveryForm() throws InterruptedException {
        Thread.sleep(500);
        fieldCalendarTransit.click();
        cellCalendarDateToday.click();
        Thread.sleep(500);


    }

    public void uploadFile2() throws InterruptedException {

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

    public AddDocumentEighthPage confirmDeliveryForm() throws InterruptedException {
        Thread.sleep(1000);
        buttonOk.click();
        return PageFactory.initElements(driver, AddDocumentEighthPage.class);
    }
}
