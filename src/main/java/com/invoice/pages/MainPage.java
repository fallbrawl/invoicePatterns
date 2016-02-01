package com.invoice.pages;

import org.openqa.selenium.WebDriver;


/**
 * Created by NEXUS on 31.01.2016.
 */
public class MainPage extends Page {


    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public boolean isLoggedIn() {
        if (driver.getTitle().equals("Invoice"))
            return true;
        else
            return false;
    }
}
