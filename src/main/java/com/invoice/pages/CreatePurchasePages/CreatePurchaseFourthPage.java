package com.invoice.pages.CreatePurchasePages;

import com.invoice.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 18.02.16.
 */
public class CreatePurchaseFourthPage extends Page {

    @FindBy(xpath = ".//*[@id='file_upload_div_naklad_date']/div/div[2]/input" )
    public WebElement fieldUploadNaklad;

    @FindBy(xpath = ".//*[@id='file_upload_div_invoicefact_date']/div/div[2]/input" )
    public WebElement fieldUploadInvoice;

    @FindBy(xpath = ".//*[@id='form-edit_transit']/div[2]/div/div/div/div[1]/div[1]/div/div/div/div[2]/span")
    public WebElement check;

    @FindBy(name = "number[2]")
    WebElement fieldNumber1;

    @FindBy(name = "number[4]")
    WebElement fieldNumber2;

    @FindBy(name = "receive_date[2]")
    WebElement fieldCalendar1;

    @FindBy(name = "receive_date[4]")
    WebElement fieldCalendar2;

    @FindBy(xpath = ".//*[@id='form-edit_transit']/div[2]/div/div/div/div[1]/div[4]/div/div[2]/div[2]/div/div[1]/span")
    WebElement check2;

    @FindBy (id = "save_documents_and_accept_purchase")
    WebElement buttonSaveDocumentsAndAcceptTransfer;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement cellNeededDate;

    public void uploadNaklad(){
        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            fieldUploadNaklad.sendKeys(pathLinux);
        } else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            fieldUploadNaklad.sendKeys(pathWindows);
        }
    }

    public void uploadInvoice() {
        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            fieldUploadInvoice.sendKeys(pathLinux);
        } else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            fieldUploadInvoice.sendKeys(pathWindows);
        }
    }

    public CreatePurchaseFourthPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void init() {

        System.out.println("INIT " + check2.getText());

    }

    public void setCalendar1() throws InterruptedException {
        Thread.sleep(1000);
        fieldCalendar1.click();
        cellNeededDate.click();
    }

    public void setNumber1() throws InterruptedException {
        typeHere(fieldNumber1,"wwww");
    }

    public void setCalendar2() throws InterruptedException {

        Thread.sleep(1000);
        fieldCalendar2.click();
        cellNeededDate.click();
    }

    public void setNumber2() throws InterruptedException {
        typeHere(fieldNumber2,"wwww");
    }

    public void saveDocumentAndTransfer() {

        buttonSaveDocumentsAndAcceptTransfer.click();
    }
}
