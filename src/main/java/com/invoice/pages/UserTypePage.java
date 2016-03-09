package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;

/**
 * Created by paul on 04.03.16.
 */
public class UserTypePage extends Page {

    public UserTypePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("userType.url"));
    }
}
