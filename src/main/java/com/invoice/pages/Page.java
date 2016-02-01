package com.invoice.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by NEXUS on 01.02.2016.
 */
public abstract class Page {
    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    protected void typeHere(WebElement elementToTypeIn, String whatToType) {
        elementToTypeIn.clear();
        elementToTypeIn.sendKeys(whatToType);
    }

    public abstract void open();

    public boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
