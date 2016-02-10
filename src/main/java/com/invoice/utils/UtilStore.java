package com.invoice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by paul on 01.02.16.
 */
public class UtilStore {

    public static String nameOfDocument1 = "documentcreated1 " + addDate();
    public static String nameOfDocument2 = "documentcreated2 " + addDate();

    public static void logout(WebDriver drv) {
        drv.findElement(By.className("account-dropdown")).click();
        waitForElementsPresence(drv, 2, "dropdown-menu-right", 'c');
        drv.findElement(By.linkText("Выйти")).click();
    }

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


    public static String addDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        String dateAndTimeAttachment = dateFormat.format(date);
        return dateAndTimeAttachment;
    }


    public static void waitForElementsAbsence(WebDriver drv, int howLong, String classOfelementToWaitFor) {
        WebDriverWait wait = new WebDriverWait(drv, howLong);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(classOfelementToWaitFor)));
        System.out.println("\n$$$$$$$$$$$$$$ " + classOfelementToWaitFor + " on the page " + drv.getCurrentUrl() + " has begun his work!$$$$$$$$$$$$$$$$");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(classOfelementToWaitFor)));
        System.out.println("\n$$$$$$$$$$$$$$ " + classOfelementToWaitFor + " on the page " + drv.getCurrentUrl() + " has ended his work!$$$$$$$$$$$$$$$$");
    }

    public static void waitForElementsPresence(WebDriver drv, int howLong, String elementToWaitFor, char typeOfIdentifier) {

        WebDriverWait wait = new WebDriverWait(drv, howLong);

        switch (typeOfIdentifier) {
            case 'i':
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementToWaitFor)));
                break;
            case 'c':
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className(elementToWaitFor)));
                break;
            default:
                throw new IllegalArgumentException("Invalid identifier: " + typeOfIdentifier);
        }

        System.out.println("\n$$$$$$$$$$$$$$ " + elementToWaitFor + " on the page " + drv.getCurrentUrl() + " is present!$$$$$$$$$$$$$$$$");

    }


    public static void goBack(WebDriver webDriver) {
        webDriver.navigate().back();
    }

    public static void reload(WebDriver webDriver) {
        webDriver.navigate().refresh();

    }

}
