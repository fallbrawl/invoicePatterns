package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddDocumentFifthPage extends Page {

    @FindBy(id = "v-name_user_info")
    WebElement fieldProviderName;

    @FindBy(xpath = "//*[@id=\"form-edit_transit\"]/div[1]/div[1]/div[2]/div[2]/div/div/a[1]")
    WebElement buttonFormPurchase;

    @FindBy(xpath = "//*[@id=\"body-wrapper\"]/div[5]/div/div/div[3]/button[1]")
    WebElement buttonAcceptFormPurchase;

    public void enterProviderName() {
        typeHere(fieldProviderName, "Provider " + UtilStore.addDate());
    }

    public AddDocumentFifthPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void initPurchase() throws InterruptedException {
        Thread.sleep(1000);
        buttonFormPurchase.click();
    }

    public AddDocumentSixthPage formPurchase() throws InterruptedException {

        Thread.sleep(1000);
        buttonAcceptFormPurchase.click();

        return PageFactory.initElements(driver, AddDocumentSixthPage.class);
    }
}
