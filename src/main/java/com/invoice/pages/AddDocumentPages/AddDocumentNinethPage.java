package com.invoice.pages.AddDocumentPages;

import com.invoice.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NEXUS on 23.02.2016.
 */
public class AddDocumentNinethPage extends Page {

    @FindBy(id = "full_shipment")
    WebElement buttonFullShipment;

    //@FindBy(className = "btn-warning")
    @FindBy(id = "edit_invoice")
    WebElement buttonEditUnload;

    @FindBy(name = "invoice[2]")
    WebElement fieldCalendarNaklad;

    @FindBy(name = "invoice[4]")
    WebElement fieldCalendarBill;

    @FindBy(name = "invoice[3]")
    WebElement fieldCalendarAct;

    @FindBy(className = "")
    WebElement button;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement cellCalendarDateToday;

    @FindBy(linkText = "Счёт")
    WebElement linkBill;

    @FindBy(linkText = "Накладная")
    WebElement linkInvoice;

    @FindBy(linkText = "Счёт-фактура")
    WebElement linkBillFacture;

    @FindBy(linkText = "Акт")
    WebElement linkAct;

    @FindBy(id = "partly_shipment")
    WebElement buttonPartialShipment;

    @FindBy(className = "table-header")
    WebElement fieldTable;

    @FindBy(className = "document_pitch_partial_input")
    WebElement fieldHowMuchToPartialShip;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[4]/div[1]/ul/li[4]/a/span")
    WebElement step4;

    @FindBy(css = "#body-wrapper > div.modal.fade.in > div > div > div.modal-footer > button.btn.btn-sm.btn-success")
    WebElement buttonOk;

    public void fullShipmentForLicenseAgreement() throws InterruptedException {
        Thread.sleep(500);
        fieldCalendarAct.click();
        Thread.sleep(500);
        cellCalendarDateToday.click();
        Thread.sleep(500);
        buttonOk.click();
    }


    public void fullShipment() throws InterruptedException {

        Thread.sleep(500);
        fieldCalendarNaklad.click();

        Thread.sleep(500);
        cellCalendarDateToday.click();
        Thread.sleep(500);
        fieldCalendarBill.click();

        Thread.sleep(500);
        cellCalendarDateToday.click();
        Thread.sleep(500);

        buttonOk.click();
    }

    public void fullShipmentForServices() throws InterruptedException {
        Thread.sleep(1000);
        fieldCalendarAct.click();

        Thread.sleep(1000);
        cellCalendarDateToday.click();
        Thread.sleep(1000);
        fieldCalendarBill.click();

        Thread.sleep(1000);
        cellCalendarDateToday.click();
        Thread.sleep(1000);

        buttonOk.click();
    }

//    public void fillFullShipmentForm() throws InterruptedException {
//        Thread.sleep(1000);
//        fieldCalendarBill.click();
//        Thread.sleep(500);
//        cellCalendarDateToday.click();
//        Thread.sleep(500);
//        fieldCalendarNaklad.click();
//        Thread.sleep(500);
//        cellCalendarDateToday.click();
//        Thread.sleep(1000);
//        buttonOk.click();
//
//    }

    public AddDocumentNinethPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get("http://invoicedev.php.attractgroup.com/public/document/show/63");

    }

    public void setFullLoad() {
        buttonFullShipment.click();
    }

    public void checkDocs(String what) throws InterruptedException {

        switch (what) {

            case "Bill":
                linkBill.click();
                Thread.sleep(1000);

                driver.getWindowHandles();
                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                System.out.println("size" + tabs.size());
                driver.switchTo().window(tabs.get(1));
                driver.get(driver.getCurrentUrl() + "1");
                System.out.println(driver.getPageSource().contains("Счёт на оплату"));
                driver.close();
                driver.switchTo().window(tabs.get(0));
                break;

            case "Invoice":
                linkInvoice.click();
                Thread.sleep(1000);

                driver.getWindowHandles();
                ArrayList<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
                System.out.println("size" + tabs1.size());
                driver.switchTo().window(tabs1.get(1));
                driver.get(driver.getCurrentUrl() + "1");
                System.out.println(driver.getPageSource().contains("Товарная накладная"));
                driver.close();
                driver.switchTo().window(tabs1.get(0));
                break;

            case "BillFacture":
                linkBillFacture.click();
                Thread.sleep(1000);

                driver.getWindowHandles();
                ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
                System.out.println("size" + tabs2.size());
                driver.switchTo().window(tabs2.get(1));
                driver.get(driver.getCurrentUrl() + "1");
                System.out.println(driver.getPageSource().contains("СЧЕТ-ФАКТУРА"));
                driver.close();
                driver.switchTo().window(tabs2.get(0));
                break;

            case "Act":
                linkAct.click();
                Thread.sleep(1000);

                driver.getWindowHandles();
                ArrayList<String> tabs3 = new ArrayList<>(driver.getWindowHandles());
                System.out.println("size" + tabs3.size());
                driver.switchTo().window(tabs3.get(1));
                driver.get(driver.getCurrentUrl() + "1");
                System.out.println(driver.getPageSource().contains("Акт"));
                driver.close();
                driver.switchTo().window(tabs3.get(0));
                break;

            default:
                throw new NoSuchElementException("Invalid document name");
        }


//        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN); //открываю в фоне в новом табе с помощью этой комбинации
//        linkBill.sendKeys(selectLinkOpeninNewTab);
//        Thread.sleep(1000);

    }

    public void partialShipment(String howMuchItemsToShip) throws InterruptedException {
        Thread.sleep(1500);
        buttonPartialShipment.click();

        if (!howMuchItemsToShip.equals("1")) {
            Thread.sleep(500);
            typeHere(fieldHowMuchToPartialShip, Keys.BACK_SPACE);
            typeHere(fieldHowMuchToPartialShip, Keys.ARROW_LEFT);
            typeHere(fieldHowMuchToPartialShip, Keys.DELETE);
            Thread.sleep(500);
            typeHere(fieldHowMuchToPartialShip, howMuchItemsToShip);
        }


        Thread.sleep(500);
        fieldCalendarAct.click();
        Thread.sleep(500);
        cellCalendarDateToday.click();


        Thread.sleep(500);
        fieldCalendarBill.click();
        Thread.sleep(500);
        cellCalendarDateToday.click();

        fieldTable.click();
        Thread.sleep(500);

        buttonOk.click();
    }

    public void editUnload() throws InterruptedException {
        Thread.sleep(500);
        buttonEditUnload.click();
    }
}
