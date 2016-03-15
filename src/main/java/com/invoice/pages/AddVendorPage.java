package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 10.03.16.
 */
public class AddVendorPage extends Page {

    @FindBy(id = "name_user_info")
    WebElement fieldNameVendor;

    @FindBy(className = "submit_btn")
    WebElement buttonSubmitVendor;

    public void enterVendorName() throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldNameVendor, "vendor " + UtilStore.nameProduct);
        Thread.sleep(500);
    }

    public void submitNewVendor(){
        buttonSubmitVendor.click();
    }

    public AddVendorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("addVendor.url"));

    }
}