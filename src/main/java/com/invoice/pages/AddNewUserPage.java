package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddNewUserPage extends Page {

    private String newUserEmail;
    private String newUserPassword = "attract";
    private String newUserName = "attract";


    @FindBy(id = "email")
    WebElement fieldNewUserEmail;

    @FindBy(id = "password")
    WebElement fieldNewUserPassword;

    @FindBy(id = "confirm_password")
    WebElement fieldNewUserConfirmPassword;

    @FindBy(id = "name")
    WebElement fieldNewUserName;

    @FindBy(id = "select2-chosen-1")
    WebElement dropdownNewUserRole;

    @FindBy(xpath = ".//*[@id='select2-result-label-23']")
    WebElement dropdownVariantSalesManagerRole;

    @FindBy(xpath = ".//*[@id='form-add_edit_user']/div[7]/div/a")
    WebElement buttonCreateNewUser;

    @FindBy(className = "check_label")
    WebElement checkboxSetEgoiste;


    public AddNewUserPage(WebDriver driver) {
        super(driver);
    }



    @Override
    public void open() {

    }

    public void setNewUserEmail(String numberOfUser){
        newUserEmail = UtilStore.userEmail + numberOfUser + "@mail.ru";
    }

    public void fillNewUserForm(String whatRole) throws InterruptedException {
//
//        newUserEmail = UtilStore.userEmail + numberOfUser + "@mail.ru";

        Thread.sleep(500);
        typeHere(fieldNewUserEmail, newUserEmail);
        Thread.sleep(500);
        typeHere(fieldNewUserPassword, newUserPassword);
        Thread.sleep(500);
        typeHere(fieldNewUserConfirmPassword, newUserPassword);
        Thread.sleep(500);
        dropdownNewUserRole.click();
        Thread.sleep(1000);

        List<WebElement> arrayOfDropdownElements = driver.findElements(By.className("select2-result-label"));

        for (WebElement a : arrayOfDropdownElements) {
            if (a.getText().equals(whatRole)) {
                Thread.sleep(500);
                a.click();
                break;
            }
        }

        Thread.sleep(500);
        typeHere(fieldNewUserName, newUserName);

    }

    public UsersPage saveNewUser() {
        buttonCreateNewUser.click();
        return PageFactory.initElements(driver, UsersPage.class);
    }

    public String getNewUserEmail() {
        System.out.println("Mail " + newUserEmail);
        return newUserEmail;
    }

    public String getNewUserPassword() {
        System.out.println("Password " + newUserPassword);
        return newUserPassword;
    }


    public void checkEgoiste() throws InterruptedException {
        Thread.sleep(500);
        checkboxSetEgoiste.click();
    }
}
