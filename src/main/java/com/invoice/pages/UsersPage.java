package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import groovy.transform.Undefined;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by paul on 03.03.16.
 */
public class UsersPage extends Page {

    AddNewUserPage addNewUserPage = new AddNewUserPage(driver);
    @FindBy(className = "btn-warning")
    //@FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[1]/form/a[1]")
    WebElement buttonAddNewUser;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div[1]/div[1]/table/tbody/tr[1]/td[2]/div")
    public WebElement textNewUserEmail;

    @FindBy(id = "s2id_select2_export_documents")
    WebElement dropdownElementsExport;

    @FindBy(className = "export_btn")
    WebElement buttonExportDocuments;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[3]/div/form/div[1]/div[9]/div/div[2]/div[2]/div/label")
    WebElement checkboxSoloAccess;

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

    public void openFirstUser(String newUserEmail) {

        System.out.print("ele " + driver.findElement(By.xpath("//tr/td[2]/div[normalize-space(.) = '" + newUserEmail + "']/../../td[5]/div/a[1]")).getText());
        driver.findElement(By.xpath("//tr/td[2]/div[normalize-space(.) = '" + newUserEmail + "']/../../td[5]/div/a[1]")).click();
        //driver.findElement(By.xpath("//tr/td[2]/div[normalize-space(.) = '" + "email142845second@mail.ru" + "']/../../td[5]/div/a[1]")).click();
    }

    public void selectUserToCopyDocument(String newUserEmail) throws InterruptedException {
        Thread.sleep(500);
        dropdownElementsExport.click();
        Thread.sleep(500);

        List<WebElement> dropdownArrayElements = driver.findElements(By.className("select2-result-label"));
        for (WebElement a : dropdownArrayElements) {
            if (a.getText().equals(newUserEmail)) {
                a.click();
                break;
            }
        }
    }

    public void exportDocuments() throws InterruptedException {

        Thread.sleep(500);
        buttonExportDocuments.click();
        Thread.sleep(500);

    }

    public void setSoloAccessToDocuments() throws InterruptedException {

        Thread.sleep(500);
        checkboxSoloAccess.click();

    }
}
