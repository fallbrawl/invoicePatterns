package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 12.03.16.
 */
public class ListClientPage extends Page {

    @FindBy(className = "buyer_agreements")
    WebElement buttonAgreements;

    @FindAll(@FindBy(className = "popover_comment"))
    List<WebElement> buttonOpenCommentsPopup;

    public String productNameForXpath = UtilStore.nameProduct;


    public void openAccount() throws InterruptedException {
        System.out.println("wwwow");
        WebElement accountLink = driver.findElement(By.xpath("//a[text()=" + "\'client " + productNameForXpath + "\'" + "]/../../../td[2]/div/a"));
        System.out.println(accountLink.getText());
        accountLink.click();
        Thread.sleep(1000);
    }

    public void openReturns() throws InterruptedException {

        WebElement returnLink = driver.findElement(By.xpath("//a[text()=" + "\'client " + productNameForXpath + "\'" + "]/../../../td[5]/div/a"));
        System.out.println(returnLink.getText());
        returnLink.click();
        Thread.sleep(1000);
//        driver.getWindowHandles();
//        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//        System.out.println("size" + tabs.size());
//        driver.switchTo().window(tabs.get(1));
//        driver.close();
//        driver.switchTo().window(tabs.get(0));
    }


    public SpecificClientPage openLastCreatedClient() throws InterruptedException {

        Thread.sleep(1000);
        driver.findElement(By.linkText("client " + UtilStore.nameProduct)).click();
        return null;
    }

    public boolean isClientDeleted() throws InterruptedException {
        Thread.sleep(2000);
        if (driver.getPageSource().contains("client " + UtilStore.nameProduct)) {
            return false;
        } else {
            return true;
        }
    }

    public ListClientPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("buyers.url"));
    }

    public void openClient() throws InterruptedException {
        Thread.sleep(500);
        //driver.findElement(By.linkText(UtilStore.nameOfDocument2)).click();
        driver.findElement(By.linkText("documentcreated2 2016/05/3114:26:23")).click();
    }

    public void openAgreements() throws InterruptedException {
        Thread.sleep(500);
        buttonAgreements.click();
    }

    public boolean isFirstBadCommentSaved() throws InterruptedException {
        Thread.sleep(2500);
        buttonOpenCommentsPopup.get(0).click();
        Thread.sleep(500);
        if (driver.findElement(By.className("popover-content")).getText().equals("Bad comment on decline")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSecondBadCommentSaved() throws InterruptedException {
        Thread.sleep(2500);
        buttonOpenCommentsPopup.get(1).click();
        Thread.sleep(500);
        if (driver.findElement(By.className("popover-content")).getText().equals("Bad comment on decline")) {
            return true;
        } else {
            return false;
        }
    }
}
