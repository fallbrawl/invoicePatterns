package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PartiesPage extends Page {

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div/div/div/form/div/div/div[2]/div/div[2]/input")
    WebElement fieldPartyNumber;

    @FindBy(name = "active_from")
    WebElement fieldCalendar;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[1]")
    WebElement cellNeededDate;

    @FindBy(id = "select2-chosen-2")
    WebElement selectStore;


    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/form/div/button")
    WebElement buttonSelectItem;

    @FindBy(id = "autocomplete_document_product")
    WebElement fieldSearch;

    @FindBy(className = "btn-high")
    WebElement buttonAcceptMoving;

    public PartiesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("move.url"));
    }

    public void findProduct() throws InterruptedException {
        //  typeHere(fieldSearch, "product 2016/02/22 10:03:14");
        Thread.sleep(500);
        typeHere(fieldSearch, UtilStore.nameProduct);
        Thread.sleep(500);



    }

    public void selectItem() throws InterruptedException {
        Thread.sleep(500);
        buttonSelectItem.click();
        Thread.sleep(500);
        buttonSelectItem.click();
        Thread.sleep(500);
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("focus();", buttonSelectItem);
//        executor.executeScript("arguments[0].click();", buttonSelectItem);

        System.out.println("Button clicked!");

    }

    public void fillFields() throws InterruptedException {
        Thread.sleep(500);
        // typeHere(fieldPartyNumber, Keys.BACK_SPACE);
        fieldPartyNumber.click();
        typeHere(fieldPartyNumber, "1");
        Thread.sleep(500);
        fieldCalendar.click();
        cellNeededDate.click();

    }

    public MovementPage acceptMoving() {
        buttonAcceptMoving.click();
        return PageFactory.initElements(driver, MovementPage.class);

    }
}
