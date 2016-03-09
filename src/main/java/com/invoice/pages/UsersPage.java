package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 03.03.16.
 */
public class UsersPage extends Page {

    @FindBy (xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[1]/form/a[1]")
    WebElement buttonAddNewUser;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div[1]/div[1]/table/tbody/tr[1]/td[2]/div")
    public WebElement textNewUserEmail;

    public AddNewUserPage addNewUser() throws InterruptedException {
        Thread.sleep(500);
        buttonAddNewUser.click();
        return PageFactory.initElements(driver, AddNewUserPage.class);
    }

    public UsersPage(WebDriver driver) {
        super(driver);
    }

    @Override

    public void open() {
        driver.get(ConfigProperties.getProperty("users.url"));

    }
}
