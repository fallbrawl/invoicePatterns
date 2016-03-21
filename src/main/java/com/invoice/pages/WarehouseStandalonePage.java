package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by paul on 10.03.16.
 */
public class WarehouseStandalonePage extends Page {

    @FindAll(@FindBy(className = "active"))
    List<WebElement> fieldArrayStoreName;
    //WebElement fieldStandaloneWarehouseForAssert;

    @FindAll(@FindBy(linkText = "Склады"))
    List <WebElement> linkStores;

    public boolean isRightWarehouse() {
        String s = "Склад \"wow " + UtilStore.nameProduct + "\"";

        for (WebElement a : fieldArrayStoreName) {
            System.out.println("Element " + a.getText());

            if (a.getText().contains(s)) {
                return true;
            }
        }
        return false;


//        System.out.println(s);
//        if (fieldStandaloneWarehouseForAssert.getText().equals("Склад \"wow " + UtilStore.nameProduct + "\""))
//            return true;
//        else return false;
    }

    public void goToStores() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(linkStores.get(1).getText());
        linkStores.get(1).click();
    }

    public WarehouseStandalonePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }
}
