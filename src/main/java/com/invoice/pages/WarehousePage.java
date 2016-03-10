package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by paul on 09.03.16.
 */
public class WarehousePage extends Page {

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[1]/form/a")
    WebElement buttonAddNewStore;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div[2]/div/ul/li[last()-1]/a")
    WebElement buttonLastPage;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div[1]/div[1]/table/tbody/tr[last()]/td[3]/div/a[1]")
    WebElement linkEditNewStore;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div[1]/div[1]/table/tbody/tr[last()]/td[2]/div/a")
    public WebElement linkNewStore;

    @FindAll(@FindBy(className = "link_divider"))
    List<WebElement> linkArrayEditStores;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div[1]/div[1]/table/tbody/tr[last()]/td[3]/div/a[2]")
    WebElement linkDeleteStore;

    @FindBy(className = "btn-success")
    WebElement buttonOk;

    public WarehousePage(WebDriver driver) {
        super(driver);
    }

    @Override

    public void open() {

        driver.get(ConfigProperties.getProperty("warehouse.url"));

    }

    public EditWarehousePage addNewStore() throws InterruptedException {
        Thread.sleep(1000);
        buttonAddNewStore.click();

        return PageFactory.initElements(driver, EditWarehousePage.class);
    }

    public void checkNumberOfStores() throws InterruptedException {

        System.out.println(linkArrayEditStores.size());

        if (linkArrayEditStores.size() == 10) {
            Thread.sleep(500);
            System.out.println(buttonLastPage.getText());
            buttonLastPage.click();
            Thread.sleep(500);
        }

    }

    public EditWarehousePage editStore() throws InterruptedException {
        linkEditNewStore.click();
        Thread.sleep(1000);
        return PageFactory.initElements(driver, EditWarehousePage.class);
    }

    public void goToLastPage() throws InterruptedException {
        Thread.sleep(1000);
        buttonLastPage.click();
    }

    public void enterStorePage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.linkText("wow " + UtilStore.nameProduct)).click();
    }

    public void deleteStore() throws InterruptedException {
        Thread.sleep(1000);
        linkDeleteStore.click();
        Thread.sleep(1000);
        buttonOk.click();
    }

    public boolean isStorePresentAfterDeletion() throws InterruptedException {
        Thread.sleep(1000);
        if (driver.getPageSource().contains("wow " + UtilStore.nameProduct))
        {
            return false;

        }
        else return true;

    }
}
