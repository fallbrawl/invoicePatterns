package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 19.02.16.
 */
public class PartiesPage extends Page {

    @FindBy (id = "autocomplete_document_product")
    WebElement fieldSearch;

    public PartiesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("move.url"));

    }

    public void findProduct() {
        typeHere(fieldSearch, UtilStore.nameProduct);
        typeHere(fieldSearch, Keys.ENTER);

    }

    public void fillFields() {

    }
}
