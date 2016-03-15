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
public class ListVendorPage extends Page {

    public String productNameForXpath = UtilStore.nameProduct;


    public void openTransit() throws InterruptedException {

        WebElement accountLink = driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div[1]/div[1]/table/tbody/tr[1]/td[1]/div/a[text()=" + "\'vendor " + productNameForXpath + "\'" + "]/../../../td[2]/div/a"));
        System.out.println(accountLink.getText());
        accountLink.click();
        Thread.sleep(1000);
        UtilStore.reload(driver);
    }

    public void openReturns() throws InterruptedException {

        WebElement returnLink = driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div[1]/div[1]/table/tbody/tr[1]/td[1]/div/a[text()=" + "\'vendor " + productNameForXpath + "\'" + "]/../../../td[5]/div/a"));
        System.out.println(returnLink.getText());
        returnLink.click();
        Thread.sleep(1000);
        UtilStore.reload(driver);
//        driver.getWindowHandles();
//        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//        System.out.println("size" + tabs.size());
//        driver.switchTo().window(tabs.get(1));
//        driver.close();
//        driver.switchTo().window(tabs.get(0));
    }


    public SpecificVendorPage openLastCreatedVendor() throws InterruptedException {

        Thread.sleep(1000);
        driver.findElement(By.linkText("vendor " + UtilStore.nameProduct)).click();
        return null;
    }

    public boolean isVendorDeleted() throws InterruptedException {
        Thread.sleep(2000);
        if (driver.getPageSource().contains("vendor " + UtilStore.nameProduct)) {
            return false;
        } else {
            return true;
        }
    }

    public ListVendorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("vendorsList.url"));
    }
}
