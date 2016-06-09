package com.invoice.pages;

import com.invoice.pages.AddDocumentPages.AddDocumentSecondPage;
import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by NEXUS on 31.01.2016.
 */
public class MainPage extends Page {
    private AddDocumentSecondPage secondPage;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("mainpage.url"));
    }

    public boolean isLoggedIn() {
        if (driver.getTitle().equals("Заказы на продажу"))
            return true;
        else
            return false;
    }

    public boolean isNullDocuments() {
        if (driver.getPageSource().contains("Ничего не найдено!")) {
            return true;
        } else {
            return false;
        }
    }

    public void openCreatedDocument() throws InterruptedException {
        Thread.sleep(500);
        WebElement neededLink = driver.findElement(By.linkText(UtilStore.buyingNumber));
        neededLink.click();
    }

    public void openComment() throws InterruptedException {
        Thread.sleep(500);
        WebElement neededLink = driver.findElement(By.xpath("//a[text() = '" + UtilStore.buyingNumber + "']/../../a"));
        neededLink.click();
    }

    public boolean isBadCommentSaved() throws InterruptedException {
        Thread.sleep(500);

        if (driver.findElement(By.className("popover-content")).getText().equals("Bad comment on decline")) {
            return true;
        } else {
            return false;
        }
    }
}
