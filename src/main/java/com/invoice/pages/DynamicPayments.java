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
    public String target = "Продавец админ";
//    public String target = UtilStore.nameOfDocument1;

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
        WebElement cells = driver.findElement(By.xpath("//td/a[text()=\"" + target + "\"]/../../td[8]/a[2]"));
        //while (driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div/div/div[1]/table/tbody/tr[1]/td[2]/a")).)
//сделать цикл http://stackoverflow.com/questions/247135/using-xpath-to-search-text-containing http://stackoverflow.com/questions/18510576/find-an-element-by-text-and-get-xpath-selenium-webdriver-junit
        //List<WebElement> cells = driver.findElements(By.xpath("//td/a[text()=\"Поставщик админ\"]"));
        System.out.println("Element: " + cells.getText());
        cells.click();
//        for (int i = 0; i < cells.size(); i++) {
//            System.out.println("Element: " + cells.get(i));
//        }
    }
}
