package com.invoice.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 11.02.16.
 */
public class AddDocumentFourthPage extends Page {

    @FindBy(xpath = "//*[@id=\"body-wrapper\"]/div/div/div[3]/section/div[2]/div[5]/div[1]/div/div/div[1]/div[1]/table/tbody/tr[3]/td/a[2]")
    WebElement buttonBuyForAll;

    public AddDocumentFourthPage(WebDriver driver) {
        super(driver);
    }

    public AddDocumentFifthPage buyForAll() {
        buttonBuyForAll.click();
        return PageFactory.initElements(driver, AddDocumentFifthPage.class);
    }

    @Override
    public void open() {

    }
}
