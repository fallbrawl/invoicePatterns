package com.invoice.pages;

import org.openqa.jetty.html.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    protected WebDriver driver;

    @FindBy(id = "modal_success")
    public WebElement buttonOk;

    @FindBy(className = "copyright")
    WebElement fieldCopyrightForInit;

    @FindBy(className = "loader_wrapper")
    public WebElement loader;

    @FindAll(@FindBy(className = "loader_wrapper"))
    public java.util.List<WebElement> loaderArray;

    @FindBy(linkText = "Выйти")
    public WebElement linkExit;

    @FindBy(className = "account-dropdown")
    public WebElement panelAccount;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/header/div[3]/ul/li[2]/ul/li[3]/a")
    public WebElement linkProfile;

    @FindBy(id = "submit_form_button_login")
    public WebElement buttonEnter;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void logout() throws InterruptedException {
        Thread.sleep(1500);
        panelAccount.click();
        Thread.sleep(500);
        linkExit.click();
        Thread.sleep(500);
        if (buttonEnter.getText().equals("Войти")) {
            System.out.println("Successfully logged out of: " + this.getClass().getSimpleName());
        }
        Thread.sleep(500);
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
        } else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            formForFile.sendKeys(pathWindows);
        }
    }

    protected void typeHere(WebElement elementToTypeIn, Keys whatKey) {
        elementToTypeIn.sendKeys(whatKey);
    }

    public void initPage() {
        System.out.println("Initializing  " + fieldCopyrightForInit.getText() + " " + driver.getTitle());
    }

    public abstract void open();

    protected void click(WebElement elementToClickOn) {
        elementToClickOn.click();
    }

    public void waitForLoad() throws InterruptedException {

        if (loaderArray.size() > 0) {
            Thread.sleep(3500);
        }
    }

    public void confirmPayment() throws InterruptedException {
        Thread.sleep(1000);
        buttonOk.click();
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

    public ProfilePage openProfile() throws InterruptedException {
        panelAccount.click();
        Thread.sleep(1000);
        linkProfile.click();

        return PageFactory.initElements(driver, ProfilePage.class);
    }



}
