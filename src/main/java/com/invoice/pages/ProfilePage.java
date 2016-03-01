package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

/**
 * Created by paul on 01.03.16.
 */
public class ProfilePage extends Page {
    private int whatLink = -1;
    private String currentStore;

    @FindBy(id = "select2-chosen-1")
    WebElement dropdownSetStore;

    @FindBy(className = "select2-chosen")
    WebElement fieldCurrentStore;

    @FindBy(className = "submit-button")
    WebElement buttonSaveProfile;

    @FindAll(@FindBy(className = "select2-result-label"))
    java.util.List<WebElement> arrayOfDropdownElements;

    public void setStore() throws InterruptedException {

        dropdownSetStore.click();
        Thread.sleep(500);
        System.out.println("SIze: " + arrayOfDropdownElements.size());
        int sizeOfArray = arrayOfDropdownElements.size();

        for (int i = 0; i < sizeOfArray; i++) {

            while (true) { //тут проверяю чтоб не выбрал тот же склад, что и до этого
                int newWhatLink = (int) (Math.random() * sizeOfArray);
                if (whatLink != newWhatLink) {
                    whatLink = newWhatLink;
                    break;
                }
            }

            if (i > 0) {
                dropdownSetStore.click();
                Thread.sleep(500);
            }

            if (i == (sizeOfArray - 1)) {
//                currentStore = arrayOfDropdownElements.get(2).getText();
                System.out.println("Склад изначальный: " + currentStore);
            }

            arrayOfDropdownElements.get(whatLink).click();
            Thread.sleep(500);

            System.out.println("рандом: " + whatLink);


        }

//        System.out.println("Склад изначальный: " + currentStore);
    }

    public void saveProfile() throws InterruptedException {
        Thread.sleep(500);
        buttonSaveProfile.click();

    }

    public String getCurrentStore() {

        currentStore = fieldCurrentStore.getText();
        System.out.println("Склад изначальный: " + currentStore);
        return currentStore;
    }

    public String getStore() {

        System.out.println("Склад изначальный: " + currentStore);
        return currentStore;
    }

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("profile.url"));

    }
}
