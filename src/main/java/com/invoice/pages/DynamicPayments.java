package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by paul on 04.02.16.
 */
public class DynamicPayments extends Page {

    public String target = UtilStore.nameOfDocument1;

    // погуглить команду java.string.format

    public DynamicPayments(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("dynamicPayments.url"));
    }

    public void getValues() {
        System.out.println(target);
        driver.navigate().refresh();
        //List<WebElement> cells = driver.findElements(By.xpath("//td/a[text()=\"" + target + "\"]"));
        while (driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div/div/div[1]/table/tbody/tr[1]/td[2]/a")).)
        {

        }
        //List<WebElement> cells = driver.findElements(By.xpath("//td/a[text()=\"Поставщик админ\"]"));

        for (int i = 0; i < cells.size(); i++) {
            System.out.println("Element: " + cells.get(i));
        }
    }
}
