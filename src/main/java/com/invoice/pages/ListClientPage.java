package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 12.03.16.
 */
public class ListClientPage extends Page {

    public SpecificClientPage openLastCreatedClient() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.linkText("client " + UtilStore.nameProduct)).click();
        return null;
    }

    public ListClientPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("buyers.url"));
    }
}
