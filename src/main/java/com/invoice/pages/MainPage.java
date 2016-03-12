package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;


/**
 * Created by NEXUS on 31.01.2016.
 */
public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("mainpage.url"));
    }



    public boolean isLoggedIn() {
        if (driver.getTitle().equals("За1казы на продажу"))
            return true;
        else
            return false;
    }
}
