package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * Created by paul on 12.03.16.
 */
public class ListClientPage extends Page {

    public String productNameForXpath = UtilStore.nameProduct;


    public void openAccount() throws InterruptedException {

        WebElement accountLink = driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td[1]/div/a[text()=" + "\'client " + productNameForXpath + "\'" + "]/../../../td[2]/div/a"));
        System.out.println(accountLink.getText());
        accountLink.click();
        Thread.sleep(1000);
    }

    public void openReturns() throws InterruptedException {

        WebElement returnLink = driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td[1]/div/a[text()=" + "\'client " + productNameForXpath + "\'" + "]/../../../td[5]/div/a"));
        System.out.println(returnLink.getText());
        returnLink.click();
        Thread.sleep(1000);
        driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        System.out.println("size" + tabs.size());
        driver.switchTo().window(tabs.get(1));
        driver.close();
        driver.switchTo().window(tabs.get(0));
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
}
