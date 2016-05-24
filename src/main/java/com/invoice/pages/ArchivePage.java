package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;

/**
 * Created by paul on 24.05.16.
 */
public class ArchivePage extends Page {

    public ArchivePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("archive.url"));
    }
}
