package com.invoice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paul on 01.02.16.
 */
public class AddDocumentSecondPage {

    private final WebDriver driver;
    public String documentName;

    public AddDocumentSecondPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getDocumentNumber() {
        String mydata = driver.findElement(By.cssSelector("li.active")).getText();

        System.out.println("Text is : " + mydata);
        Pattern pattern = Pattern.compile("S[\\d -]*");
        Matcher matcher = pattern.matcher(mydata);

        if (matcher.find()) {
            documentName = matcher.group(0);
            System.out.println("EXTRACTED regex expression " + documentName);
        }
    }

    public void attachPdf() {
        String s = System.getProperty("user.dir") + "/Geographic50.xlsx";
    }
}
