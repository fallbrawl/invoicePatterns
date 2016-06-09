package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 24.05.16.
 */
public class ArchivePage extends Page {

    @FindBy(css = "[for=\"status_2_1\"]")
    WebElement buttonClientsAgreements;

    public ArchivePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("archive.url"));
    }

    public void openSubPage(String whatSubPage) throws NoSuchFieldException, InterruptedException {

        Thread.sleep(1500);

        switch (whatSubPage) {
            case "Договора клиентов":
                buttonClientsAgreements.click();
                break;
            default:
                throw new NoSuchFieldException();
        }
    }

    public void openComment() throws InterruptedException {

        Thread.sleep(500);

        driver.findElement(By.xpath("//a[text() = '" + UtilStore.nameOfDocument2 + "']/../../../td[9]/div/a[1]")).click();
        //driver.findElement(By.xpath("//a[text() = 'documentcreated2 2016/05/2417:27:21']/../../../td[9]/div/a[1]")).click();
    }

    public boolean isBadCommentSaved() throws InterruptedException {
        Thread.sleep(500);

        if (driver.findElement(By.className("popover-content")).getText().equals("Bad comment on decline")) {
            return true;
        } else {
            return false;
        }
    }

    public void blockAgreement() throws InterruptedException {
        Thread.sleep(500);

        driver.findElement(By.xpath("//a[text() = '" + UtilStore.nameOfDocument2 + "']/../../../td[9]/div/a[1]")).click();

    }

    public void writeComment(String comment) throws InterruptedException {
        Thread.sleep(1000);

        typeHere(driver.findElement(By.id("comment")),comment);
    }
}
