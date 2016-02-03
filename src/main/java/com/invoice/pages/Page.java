package com.invoice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by NEXUS on 01.02.2016.
 */
public abstract class Page {
    protected WebDriver driver;
//
//    @FindBy(className = "loader_wrapper")
//    public WebElement loader;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    protected void typeHere(WebElement elementToTypeIn, String whatToType) {
        elementToTypeIn.clear();
        elementToTypeIn.sendKeys(whatToType);
    }

    public abstract void open();

    protected void click(WebElement elementToClickOn){
        elementToClickOn.click();
    }

    public void submit(WebElement elementToSubmit){
        elementToSubmit.click();
    }


    public boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
