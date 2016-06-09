package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by paul on 08.06.16.
 */
public class FinancialStatePage extends Page {

    @FindBy(className = "select2-choices")
    WebElement dropdownSellersName;

    @FindBy(css = "[for=\"radio3\"]")
    WebElement radioWithWAT;

    @FindBy(id = "build")
    WebElement buttonBuildGraph;

    public FinancialStatePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("financial.url"));
    }

    public void enterSellersName() throws InterruptedException {
        Thread.sleep(500);
        dropdownSellersName.click();
        Thread.sleep(1000);


        List<WebElement> dropdownClients = driver.findElements(By.className("select2-result-label"));
        for (WebElement a:dropdownClients){

           // if (a.getText().contains("documentcreated1 2016/05/1115:27:51")){
           if (a.getText().contains(UtilStore.nameOfDocument1)){
                Thread.sleep(1000);
                a.click();
                break;
            };
        }
    }

    public void checkWithWAT() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("[for=\"radio3\"]")).click();
    }

    public void build() throws InterruptedException {
        Thread.sleep(1500);
        buttonBuildGraph.click();
    }
}
