package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.classpath.icedtea.Config;
import org.openqa.selenium.WebDriver;

/**
 * Created by paul on 04.02.16.
 */
public class DynamicPayments extends Page {
    public DynamicPayments(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("dynamicPayments.url"));
    }
}
