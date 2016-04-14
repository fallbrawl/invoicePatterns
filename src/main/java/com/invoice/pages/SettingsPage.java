package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 14.04.16.
 */
public class SettingsPage extends Page {

    private MeasurePage measurePage = new MeasurePage(driver);
    private String measureName = measurePage.getMeasureName();

    @FindBy(className = "btn-high")
    WebElement buttonEditMeasures;

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("settings.url"));

    }

    public void editMeasures() throws InterruptedException {
        Thread.sleep(500);
        buttonEditMeasures.click();

    }

    public boolean isMeasureAdded() throws InterruptedException {
        Thread.sleep(500);

        if (driver.getPageSource().contains(measureName)) {
            return true;
        } else
            return false;
    }

    public boolean isMeasureHided() {
        if (driver.getPageSource().contains(measurePage.getEditedMeasureName())) {
            return false;
        } else
            return true;
    }
}
