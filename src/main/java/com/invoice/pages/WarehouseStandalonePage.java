package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 10.03.16.
 */
public class WarehouseStandalonePage extends Page {

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[1]/ol/li[2]")
    WebElement fieldStandaloneWarehouseForAssert;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[1]/ol/li[1]/a")
    public  WebElement linkStores;

    public boolean isRightWarehouse() {
        System.out.println(fieldStandaloneWarehouseForAssert.getText());
        String s = "Склад \"wow " + UtilStore.nameProduct + "\"";
        System.out.println(s);
        if (fieldStandaloneWarehouseForAssert.getText().equals("Склад \"wow " + UtilStore.nameProduct + "\""))
            return true;
        else return false;
    }

    public void goToStores() throws InterruptedException {
        Thread.sleep(1000);
        linkStores.click();
    }

    public WarehouseStandalonePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }
}
