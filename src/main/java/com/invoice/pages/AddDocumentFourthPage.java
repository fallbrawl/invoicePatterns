package com.invoice.pages;


import org.openqa.selenium.WebDriver;

/**
 * Created by paul on 11.02.16.
 */
public class AddDocumentFourthPage extends Page {


    public boolean checkTitle() {
        if (driver.getTitle().contains("wow")) {
            return true;
        } else return false;

    }

    public AddDocumentFourthPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }
}
