package com.invoice.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

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

    protected void uploadFile(WebElement formForFile) {
        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        //Вставить путь для Windows
        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            formForFile.sendKeys(pathLinux);
        }
        else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            formForFile.sendKeys(pathWindows);
        }
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
