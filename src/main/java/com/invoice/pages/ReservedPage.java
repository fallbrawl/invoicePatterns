package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 24.02.16.
 */
public class ReservedPage extends Page {

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[5]/div[2]/div[1]/table/tbody/tr[1]/td[11]/a")
    WebElement buttonUnattach;

    @FindBy(className = "btn-success")
    WebElement buttonOk;

    public ReservedPage(WebDriver driver) {
        super(driver);
    }

    public void unattach() {
        buttonUnattach.click();

    }

    public void enter() throws InterruptedException {
        Thread.sleep(1000);
        buttonOk.click();
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("reserve.url"));
    }
}
