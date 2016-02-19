package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;

/**
 * Created by paul on 19.02.16.
 */
public class MovePage extends Page {

    public MovePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("pitch.url"));

    }
}
