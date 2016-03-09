package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindAll(@FindBy(className = "select2-result-label"))
    java.util.List<WebElement> arrayOfDropdownElements;

    public AddNewUserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void fillNewUserForm() throws InterruptedException {
        newUserEmail = UtilStore.userEmail + "@mail.ru";
        Thread.sleep(500);
        typeHere(fieldNewUserEmail, newUserEmail);
        Thread.sleep(500);
        typeHere(fieldNewUserPassword, newUserPassword);
        Thread.sleep(500);
        typeHere(fieldNewUserConfirmPassword, newUserPassword);
        Thread.sleep(500);
        typeHere(fieldNewUserName, newUserName);
        Thread.sleep(500);
        dropdownNewUserRole.click();
        Thread.sleep(500);
        arrayOfDropdownElements.get(3).click();
        Thread.sleep(500);

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


}
