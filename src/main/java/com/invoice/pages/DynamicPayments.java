package com.invoice.pages;

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

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[6]/div/div/div[3]/button[1]")
    WebElement buttonOk;

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
        WebElement cells = driver.findElement(By.xpath("//td/a[text()=\"" + target + "\"]/../../td[8]/a[2]"));
        System.out.println("Element: " + cells.getText());
        cells.click();
        Thread.sleep(1000);

//        for (int i = 0; i < cells.size(); i++) {
//            System.out.println("Element: " + cells.get(i));
//        }
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

    public void setValuesPayWindow() {
        typeHere(inputSum, "345435");
        typeHere(inputNumber, "345345");
        inputDate.click();
        neededDate.click();
    }

    public void confirmPayment() {
        buttonOk.click();
        driver.navigate().back();
    }
}