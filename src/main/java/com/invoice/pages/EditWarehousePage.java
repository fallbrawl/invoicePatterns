package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 09.03.16.
 */
public class EditWarehousePage extends Page {

    public List<String> arrayOfNamesSellersAndManagers = new ArrayList<String>();
    public int i = 0;
    public String[] arrayOfNamesSellersAndManagers1;

    @FindBy(name = "name_warehouse")
    public WebElement fieldNameWarehouse;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[1]/ol/li[2]")
    public WebElement textNameOfStore;

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

        dropdownArrayManagersWarehouse.get(0).click();
        Thread.sleep(1000);

        dropdownArrayVariantsManagersWarehouse.get(2).click();
        Thread.sleep(1000);

        dropdownArrayManagersWarehouse.get(0).click();
        Thread.sleep(1000);

        dropdownArrayVariantsManagersWarehouse.get(1).click();
        Thread.sleep(1000);

        dropdownArrayManagersWarehouse.get(1).click();
        Thread.sleep(500);

        dropdownArrayVariantsManagersWarehouse.get(3).click();
        Thread.sleep(500);

        dropdownArrayManagersWarehouse.get(1).click();
        Thread.sleep(500);

        dropdownArrayVariantsManagersWarehouse.get(2).click();
        Thread.sleep(500);

        dropdownArrayDeleteEntriesWarehouse.get(4).click();
        Thread.sleep(500);

        dropdownArrayDeleteEntriesWarehouse.get(1).click();
        Thread.sleep(1000);

        System.out.println("Size " + elementsManagersAndSellersAfterDeletion.size());

        for (WebElement a : elementsManagersAndSellersAfterDeletion) {

            arrayOfNamesSellersAndManagers.add(a.getText());
            arrayOfNamesSellersAndManagers1[i] = arrayOfNamesSellersAndManagers.get(i);
            i++;

        }
    }

    public WarehousePage acceptNewStore() {
        buttonSaveNewStore.click();
        return PageFactory.initElements(driver, WarehousePage.class);

    }

    public boolean checkThatChangesAreSaved() {
        for (WebElement a : elementsManagersAndSellersAfterDeletion) {
            System.out.println("Size " + arrayOfNamesSellersAndManagers1.length);
            System.out.println(arrayOfNamesSellersAndManagers1[1]);
            if (a.getText() != arrayOfNamesSellersAndManagers.get(i)) {

                return false;
            }
            i++;
        }
        return true;
    }
}
