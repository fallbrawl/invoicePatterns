package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
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

    @FindBy(className = "fa-check")
    public WebElement buttonCheck;

    @FindBy(className = "btn-danger")
    public WebElement buttonCancel;

    @FindBy(id = "comments")
    public WebElement textfieldComments;

    @Override
    public void open() {

        driver.get(ConfigProperties.getProperty("manager.url"));
    }

    public void declineAgreement() {
        driver.findElement(By.xpath("//a[text() = '" + UtilStore.nameOfDocument2 + "' ]/../../td[6]/div/a[2]")).click();
    }

    public void writeComment(String comment) throws InterruptedException {
        Thread.sleep(1000);

        typeHere(driver.findElement(By.id("comment")),comment);
    }

    public void acceptAgreement() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[text() = '" + UtilStore.nameOfDocument2 + "' ]/../../td[6]/div/a[1]")).click();
        Thread.sleep(500);
        buttonOk.click();

    }
}
