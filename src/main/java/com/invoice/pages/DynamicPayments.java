package com.invoice.pages;

import com.invoice.pages.AddDocumentPages.AddDocumentSeventhPage;
import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 04.02.16.
 */
public class DynamicPayments extends Page {

    private AddDocumentSeventhPage seventhPage;

    @FindBy(name = "sum_payment")
    WebElement inputSum;

    @FindBy(name = "file_text")
    WebElement inputNumber;

    @FindBy(name = "date_form")
    WebElement inputDate;

    @FindBy(name = "file")
    WebElement inputFile;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement neededDate;

    public String target = UtilStore.nameOfDocument1;

    // погуглить команду java.string.format

    public DynamicPayments(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("dynamicPayments.url"));
    }


    public void openPayWindow() throws InterruptedException {
        System.out.println(target);
        driver.navigate().refresh();
        WebElement cells = driver.findElement(By.xpath("//td/a[text()='" + target + "']/../../td[9]/a[2]"));
        System.out.println("Element: " + cells.getText());
        cells.click();
        Thread.sleep(1000);

    }

    public void openPayWindowS() throws InterruptedException {
        System.out.println(target);
        driver.navigate().refresh();
        WebElement cells = driver.findElement(By.xpath("//td/a[text()='" + UtilStore.nameOfDocument2 + "']/../../td[9]/a[2]"));
        System.out.println("Element: " + cells.getText());
        cells.click();
        Thread.sleep(1000);

    }



    public void uploadFile() {
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

    public void setValuesPayWindow() throws InterruptedException {
        typeHere(inputSum, "200");
        typeHere(inputNumber, "345345");
        inputDate.click();
        Thread.sleep(1000);
        neededDate.click();
        Thread.sleep(500);
    }

    public void confirmPayment() throws InterruptedException {
        buttonOk.click();
        Thread.sleep(500);
    }


    public void openBying() throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("Problem: " + UtilStore.buyingNumber);
        driver.findElement(By.linkText(UtilStore.buyingNumber)).click();
    }

    public void openComment() throws InterruptedException {
        Thread.sleep(500);
        WebElement neededLink = driver.findElement(By.xpath("//a[text() = '" + UtilStore.buyingNumber + "']/../a[2]"));
        //WebElement neededLink = driver.findElement(By.xpath("//a[text() = 'S310516-134']/../a[2]"));
        neededLink.click();
    }

    public boolean isBadCommentSaved() throws InterruptedException {
        Thread.sleep(500);

        if (driver.findElement(By.className("popover-content")).getText().equals("Bad comment on decline")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPaymentCreated() {
        if (driver.findElement(By.xpath("//tr[2]/td[5]/div")).getText().contains("(Отгружено: 500 RUB)")) {
            return true;
        } else return false;
    }

    public void openReturns() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(target);
        driver.navigate().refresh();
        WebElement cells = driver.findElement(By.xpath("//td/a[text()='" + target + "']/../../td[9]/a[3]"));
        System.out.println("Element: " + cells.getText());
        cells.click();

    }

    public void setReturnsPayWindow() throws InterruptedException {
        typeHere(inputSum, "100");
        typeHere(inputNumber, "345345");
        inputDate.click();
        Thread.sleep(1000);
        neededDate.click();
        Thread.sleep(500);
    }


}
