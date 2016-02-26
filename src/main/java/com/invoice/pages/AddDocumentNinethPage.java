package com.invoice.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

/**
 * Created by NEXUS on 23.02.2016.
 */
public class AddDocumentNinethPage extends Page {

    @FindBy(xpath = ".//*[@id='buttons_div']/a[2]")
    WebElement buttonFullShipment;

    @FindBy(name = "invoice[2]")
    WebElement fieldCalendarNaklad;

    @FindBy(name = "invoice[4]")
    WebElement fieldCalendarBill;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement cellCalendarDateToday;

    @FindBy(xpath = "//*[@id=\"body-wrapper\"]/div[1]/div/div[3]/section/div[2]/div[7]/div[7]/div/div/div[2]/div/a")
    WebElement linkBill;

    @FindBy(linkText = "Накладная")
    WebElement linkInvoice;

    @FindBy(linkText = "Счёт-фактура")
    WebElement linkBillFacture;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[4]/div[1]/ul/li[4]/a/span")
    WebElement step4;

    @FindBy(css = "#body-wrapper > div.modal.fade.in > div > div > div.modal-footer > button.btn.btn-sm.btn-success")
    WebElement buttonOk;

    public void fullShipment() throws InterruptedException {
        Thread.sleep(500);
        buttonFullShipment.click();
    }

    public void fillFullShipmentForm() throws InterruptedException {
        Thread.sleep(1000);
        fieldCalendarBill.click();
        Thread.sleep(500);
        cellCalendarDateToday.click();
        Thread.sleep(500);
        fieldCalendarNaklad.click();
        Thread.sleep(500);
        cellCalendarDateToday.click();
        Thread.sleep(1000);
        buttonOk.click();

    }

    public AddDocumentNinethPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get("http://invoicedev.php.attractgroup.com/public/document/show/63");

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
            default:
                throw new NoSuchElementException("Invalid document name");
        }


//        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN); //открываю в фоне в новом табе с помощью этой комбинации
//        linkBill.sendKeys(selectLinkOpeninNewTab);
//        Thread.sleep(1000);

    }

}
