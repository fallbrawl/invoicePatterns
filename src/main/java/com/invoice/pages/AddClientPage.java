package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 10.03.16.
 */
public class AddClientPage extends Page {

    @FindBy(id = "name_user_info")
    WebElement fieldNameClient;

    @FindBy(className = "submit_btn")
    WebElement buttonSubmitClient;

    public void enterClientName() throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldNameClient, "client " + UtilStore.nameProduct);
        Thread.sleep(500);
    }

    public void submitNewClient(){
        buttonSubmitClient.click();
    }

    public AddClientPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("addClient.url"));

    }
}
