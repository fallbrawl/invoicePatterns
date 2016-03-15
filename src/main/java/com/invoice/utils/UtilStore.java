package com.invoice.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by paul on 01.02.16.
 */
public class UtilStore {
    public static String nameProduct = ("product " + UtilStore.addDateForProduct());
    public static String userEmail = ("email" + UtilStore.addDateForEmail());
    public static String nameOfDocument1 = "documentcreated1 " + addDateForProduct();
    public static String nameOfDocument2 = "documentcreated2 " + addDateForProduct();
    public static ArrayList<String> arrayOfNamesSellersAndManagers = new ArrayList<String>();
    public static ArrayList<WebElement> arrayForSmth = new ArrayList<>();

    public static boolean checkHttpResponseCode(String url) {
        com.jayway.restassured.response.Response response =
                given().get(url)
                        .then().extract().response();

        int returnedResponse = response.getStatusCode();

        if (returnedResponse >= 200 && returnedResponse < 400) {
            System.out.println("Test on \"" + url + "\" is OK! And the code is: " + returnedResponse);
            return true;
        } else {
            System.out.println("Test on \"" + url + "\" is FAILED! And the code is: " + returnedResponse);
            return false;
        }
    }

    public static String addDateForProduct() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        String dateAndTimeAttachment = dateFormat.format(date);
        return dateAndTimeAttachment;
    }

    public static String addDateForEmail() {
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        //get current date time with Date()
        Date date = new Date();
        String dateAndTimeAttachment = dateFormat.format(date);
        return dateAndTimeAttachment;
    }



    public static void goBack(WebDriver webDriver) {
        webDriver.navigate().back();
    }

    public static void reload(WebDriver webDriver) {

        webDriver.navigate().refresh();
        System.out.println("RELOADED!");

    }

    public static void goForward(WebDriver webDriver) {
        webDriver.navigate().forward();
    }


}
