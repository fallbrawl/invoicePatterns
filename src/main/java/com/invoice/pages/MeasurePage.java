package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by paul on 14.04.16.
 */
public class MeasurePage extends Page {

    private String measureName = "measure" + UtilStore.addSimpleDate();
    private String editedMesureName = measureName + "1";

    @FindBy(className = "btn-warning")
    WebElement buttonAddNewMeasure;

    @FindBy(id = "name_measure")
    WebElement fieldEnterMeasureName;

    @FindBy(className = "btn-high")
    WebElement buttonSaveMeasure;

    @FindBy(css = "#form-add_edit_measure > div > div > div:nth-child(2) > div > div.input_wrapper > div.text-blue.check_style.check_blue.check_before > label")
    WebElement checkboxPublicAccess;

    @FindAll(@FindBy(className = "link_divider"))
    List<WebElement> arrayNumberOfItemsOnPage;


    public MeasurePage(WebDriver driver) {
        super(driver);
    }

    public String getMeasureName() {
        return measureName;
    }

    public String getEditedMeasureName() {
        return editedMesureName;
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("measures.url"));
    }

    public void addMeasure() throws InterruptedException {
//        List<WebElement> wow = driver.findElements(By.xpath("//td[position() = 1]/div[normalize-space(.)='measure111119']"));
//        //WebElement wow1 = driver.findElement(By.xpath(".//td[position() = 1]/div[contains(@class, 'td_text') and text()='measure111119']"));
//        //       System.out.println(wow.getText());
//        for (WebElement a : wow) {
//            System.out.println(a.getText());
//        }

        //System.out.println(driver.findElement(By.xpath("//td[1]/div[text() = ' measure111119 ']")).getText());
        Thread.sleep(500);
        buttonAddNewMeasure.click();
    }

    public void enterNameMeasure() throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldEnterMeasureName, measureName);
    }

    public void saveMeasure() throws InterruptedException {
        Thread.sleep(500);
        buttonSaveMeasure.click();
    }

    public void editCreatedMeasure() throws InterruptedException {
        Thread.sleep(500);

        System.out.println(measureName);
        //driver.findElement(By.xpath("//td[position() = 1]/div[normalize-space(.)='" + measureName + "']"));
        driver.findElement(By.xpath("//td[position() = 1]/div[normalize-space(.)='" + measureName + "']/../../td[3]/div/a[1]")).click();

        Thread.sleep(500);
        typeHere(fieldEnterMeasureName, editedMesureName);

    }

    public void checkHowManyPages() throws InterruptedException {
        System.out.println(arrayNumberOfItemsOnPage.size());
        if (arrayNumberOfItemsOnPage.size() == 10) {
            Thread.sleep(500);
            System.out.println(driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[2]/div/ul/li[last() - 1]")).getText());
            driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[2]/div/ul/li[last() - 1]")).click();
        }
    }

    public void hideMeasure() throws InterruptedException {
        Thread.sleep(500);
        checkboxPublicAccess.click();
    }

    public boolean isMeasureEdited() throws InterruptedException {
        Thread.sleep(500);

        if (driver.getPageSource().contains(editedMesureName)) {
            return true;
        } else return false;

    }
}
