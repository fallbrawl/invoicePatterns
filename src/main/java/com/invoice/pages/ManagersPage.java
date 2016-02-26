package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by paul on 03.02.16.
 */
public class ManagersPage extends Page {
    public ManagersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "icon_check")
    public WebElement buttonCheck;

    @FindBy(className = "btn-success")
    public WebElement buttonDone;

    @FindBy(className = "btn-danger")
    public WebElement buttonCancel;

    public void checkAndSave() {
        buttonCheck.click();

    }

    public void enter() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(buttonDone));
        buttonDone.click();
    }

    @Override
    public void open() {

        driver.get(ConfigProperties.getProperty("manager.url"));
    }
}
