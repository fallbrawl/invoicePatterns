package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
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

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div[1]/div[1]/table/tbody/tr[last()]/td[2]/div/a")
    public WebElement linkNewStore;

    @FindAll(@FindBy(linkText = "Редактировать"))
    List<WebElement> linkArrayEditStores;

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
        if (linkArrayEditStores.size() > 10) {
            Thread.sleep(1000);
            System.out.println(buttonLastPage.getText());
            buttonLastPage.click();
        }
    }
}
