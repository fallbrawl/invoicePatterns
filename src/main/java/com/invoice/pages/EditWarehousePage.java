package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by paul on 09.03.16.
 */
public class EditWarehousePage extends Page {

    public int numberOfElementsAfterDeletion;

    @FindBy(name = "name_warehouse")
    public WebElement fieldNameWarehouse;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[1]/ol/li[2]")
    public WebElement textNameOfStore;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[1]/ol/li[1]/a")
    public  WebElement linkStores;

    @FindAll(@FindBy(className = "select2-choices"))
    List<WebElement> dropdownArrayManagersWarehouse;

    @FindAll(@FindBy(className = "select2-result-selectable"))
    List<WebElement> dropdownArrayVariantsManagersWarehouse;

    @FindAll(@FindBy(className = "select2-search-choice-close"))
    List<WebElement> dropdownArrayDeleteEntriesWarehouse;

    @FindAll
            (@FindBy(className = "select2-search-choice"))
    List<WebElement> elementsManagersAndSellersAfterDeletion;

    @FindBy(xpath = ".//*[@id='form-add_edit_warehouse']/div[4]/div/a")
    WebElement buttonSaveNewStore;

    public EditWarehousePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

        driver.get(ConfigProperties.getProperty("editwarehouse.url"));
    }

    public void fillFieldsNewStore() throws InterruptedException {

        Thread.sleep(1000);

        typeHere(fieldNameWarehouse, "wow " + UtilStore.nameProduct);
        Thread.sleep(1000);

        dropdownArrayManagersWarehouse.get(0).click();
        Thread.sleep(1000);
        dropdownArrayVariantsManagersWarehouse.get(3).click();
        Thread.sleep(1000);

        dropdownArrayManagersWarehouse.get(1).click();
        Thread.sleep(500);
        dropdownArrayVariantsManagersWarehouse.get(2).click();
        Thread.sleep(500);

        dropdownArrayDeleteEntriesWarehouse.get(0).click();
        Thread.sleep(500);

        dropdownArrayManagersWarehouse.get(0).click();
        Thread.sleep(1000);
        dropdownArrayVariantsManagersWarehouse.get(2).click();
        Thread.sleep(1000);

        dropdownArrayManagersWarehouse.get(1).click();
        Thread.sleep(1000);
        dropdownArrayVariantsManagersWarehouse.get(3).click();
        Thread.sleep(500);
        dropdownArrayDeleteEntriesWarehouse.get(2).click();
        Thread.sleep(1000);

        dropdownArrayManagersWarehouse.get(0).click();
        Thread.sleep(1000);
        dropdownArrayVariantsManagersWarehouse.get(1).click();
        Thread.sleep(1000);

        numberOfElementsAfterDeletion = elementsManagersAndSellersAfterDeletion.size();
        System.out.println("Number " + numberOfElementsAfterDeletion);

        for (WebElement a : elementsManagersAndSellersAfterDeletion) {

            System.out.println(a.getText());
            UtilStore.arrayOfNamesSellersAndManagers.add(a.getText());
        }
    }

    public WarehousePage acceptNewStore() {
        buttonSaveNewStore.click();
        return PageFactory.initElements(driver, WarehousePage.class);

    }

    public boolean checkThatChangesAreSaved() {

        for (String a : UtilStore.arrayOfNamesSellersAndManagers) {
            System.out.println("assert" + a);
            if (!driver.getPageSource().contains(a) || elementsManagersAndSellersAfterDeletion.size() != numberOfElementsAfterDeletion) {
                return false;
            }
        }
        return true;
    }

    public void goToStores() throws InterruptedException {
        Thread.sleep(1000);
        linkStores.click();
    }


}
