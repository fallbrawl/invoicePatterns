package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 03.02.16.
 */
public class ManagersPage extends Page {
    public ManagersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "icon_check")
    public WebElement buttonCheck;

    @FindBy(className = "btn-success")
    public WebElement buttonDone;

    public void checkAndSave() throws InterruptedException {

        buttonCheck.click();
        buttonDone.click();
    }
    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("manager.url"));
    }
}
