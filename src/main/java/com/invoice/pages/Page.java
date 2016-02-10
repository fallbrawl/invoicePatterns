package com.invoice.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

/**
 * Created by NEXUS on 01.02.2016.
 */
public abstract class Page {
    protected WebDriver driver;

    @FindBy(className = "loader_wrapper")
    public WebElement loader;

    @FindBy(name = "Выйти")
    public WebElement linkExit;

    @FindBy(className = "account-dropdown")
    public WebElement panelAccount;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void logout(WebDriver drv) throws InterruptedException {
        panelAccount.click();
        Thread.sleep(1000);
        linkExit.click();
    }

    protected void typeHere(WebElement elementToTypeIn, String whatToType) {
        elementToTypeIn.clear();
        elementToTypeIn.sendKeys(whatToType);
    }

    protected void typeHere(WebElement elementToTypeIn, Keys whatKey) {
        elementToTypeIn.sendKeys(whatKey);
    }

    public abstract void open();

    protected void click(WebElement elementToClickOn) {
        elementToClickOn.click();
    }

    public void waitForLoad() throws InterruptedException {
        if (loader.isDisplayed()) {
            Thread.sleep(3000);
        }
    }

    public void submit(WebElement elementToSubmit) {
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
