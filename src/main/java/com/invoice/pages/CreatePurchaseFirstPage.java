package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePurchaseFirstPage extends Page {
    public CreatePurchaseFirstPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "vendor[name_user_info]")
    public WebElement fieldNameOne;

    @FindBy(id = "seller[name_user_info]")
    public WebElement fieldNameTwo;

    @FindBy(className = "btn-primary")
    public WebElement buttonNextStep;

    @FindBy(className = "btn-success")
    public WebElement buttonAcceptPurchase;

    public void enterNames() {
        typeHere(fieldNameOne, UtilStore.nameOfDocument1);
        typeHere(fieldNameTwo, UtilStore.nameOfDocument2);

    }

    public CreatePurchaseSecondPage toTheNextStep(){

        click(buttonAcceptPurchase);
        return PageFactory.initElements(driver, CreatePurchaseSecondPage.class);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("addTransit.url"));

    }

    public void acceptPurchase() {
        click(buttonNextStep);
    }
}
