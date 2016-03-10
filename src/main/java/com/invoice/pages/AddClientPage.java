package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;

/**
 * Created by paul on 10.03.16.
 */
public class AddClientPage extends Page {



    public AddClientPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("addClient.url"));

    }
}
