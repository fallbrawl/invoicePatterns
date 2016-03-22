package com.invoice.pages.AddDocumentPages;

import com.invoice.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 24.02.16.
 */
public class AddDocumentEighthPage extends Page {

    @FindBy(linkText = "Принять поставку")
    WebElement linkAcceptOrder;

    @FindBy(name = "number[2]")
    WebElement fieldNumber1;

    @FindBy(name = "number[3]")
    WebElement fieldNumber3;

    @FindBy(name = "number[4]")
    WebElement fieldNumber2;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[1]/a")
    WebElement getCellCalendarDateFirstDayOfTHeSecondWeekOnTheNextMonth;

    @FindBy(xpath = ".//*[@id='file_upload_div_naklad_date']/div/div[2]/input")
    public WebElement fieldUploadNaklad;

    @FindBy(xpath = ".//*[@id='file_upload_div_act_date']/div/div[2]/input")
    public WebElement fieldUploadAct;

    @FindBy(xpath = ".//*[@id='file_upload_div_invoicefact_date']/div/div[2]/input")
    public WebElement fieldUploadInvoice;

    @FindBy(name = "receive_date[2]")
    WebElement fieldCalendar1;

    @FindBy(name = "receive_date[3]")
    WebElement fieldCalendar3;

    @FindBy(name = "receive_date[4]")
    WebElement fieldCalendar2;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement cellCalendarDateToday;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement cellNeededDate;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/div/a[2]/span")
    public WebElement buttonGoToTheNextMonthInCalendar;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div[1]/div[3]/section/div[2]/div[9]/div[1]/div/a[1]")
    WebElement buttonSaveDocumentsAndAcceptTransfer;


    public void openOrder() throws InterruptedException {
        linkAcceptOrder.click();
        Thread.sleep(500);
    }

    public void uploadNaklad() {
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

    public void uploadAct() {
        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            fieldUploadAct.sendKeys(pathLinux);
        } else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            fieldUploadAct.sendKeys(pathWindows);
        }
    }

    public void setCalendar1NextMonthFirstDay() throws InterruptedException {
        Thread.sleep(1000);
        fieldCalendar1.click();
        buttonGoToTheNextMonthInCalendar.click();

        cellCalendarDateToday.click();
    }

    public void setCalendar3NextMonthFirstDay() throws InterruptedException {
        Thread.sleep(1000);
        fieldCalendar3.click();
        buttonGoToTheNextMonthInCalendar.click();

        cellCalendarDateToday.click();
    }

    public void setCalendar3FirstDay() throws InterruptedException {
        Thread.sleep(1000);
        fieldCalendar3.click();

        cellCalendarDateToday.click();
    }

    public void setNumber1() {
        typeHere(fieldNumber1, "wwww");
    }

    public void setNumber3() {
        typeHere(fieldNumber3, "wwww");
    }

    public void setCalendar2NextMonthFirstDay() throws InterruptedException {

        Thread.sleep(1000);
        fieldCalendar2.click();
        buttonGoToTheNextMonthInCalendar.click();

        cellCalendarDateToday.click();
    }

    public void setCalendar2FirstDay() throws InterruptedException {

        Thread.sleep(1000);
        fieldCalendar2.click();

        cellCalendarDateToday.click();
    }

    public void setNumber2() {
        typeHere(fieldNumber2, "wwww");
    }

    public AddDocumentNinethPage acceptOrder() throws InterruptedException {
        Thread.sleep(1000);
        buttonSaveDocumentsAndAcceptTransfer.click();
        return PageFactory.initElements(driver, AddDocumentNinethPage.class);
    }

    public AddDocumentEighthPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }
}
