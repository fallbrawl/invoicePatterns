package com.invoice.pages.ExpensesPages;

import com.invoice.pages.Page;
import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by paul on 04.04.16.
 */
public class ExpensesTypePage extends Page {


    @FindBy(className = "btn-warning")
    WebElement buttonAddExpenseType;

    @FindBy(id = "type_name")
    WebElement fieldTypeName;

    @FindBy(id = "numeration")
    WebElement fieldNumeration;

    @FindBy(className = "btn-primary")
    WebElement buttonSave;

    @FindAll(@FindBy(className = "link_divider"))
    List<WebElement> arrayForPageSwitch;


    public void addExpenseType() throws InterruptedException {
        Thread.sleep(500);
        buttonAddExpenseType.click();
    }

    public void fillFieldsOfNewExpenseType() throws InterruptedException {

        Thread.sleep(500);
        typeHere(fieldTypeName, UtilStore.expenseTypeName);
        System.out.println("Init " + UtilStore.expenseTypeName);
        Thread.sleep(500);
        typeHere(fieldNumeration, "4568994");
    }

    public ExpensesTypePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("expenseType.url"));
    }

    public void save() throws InterruptedException {
        Thread.sleep(500);
        buttonSave.click();
    }


    public void editCreatedExpenseType() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Now " + UtilStore.expenseTypeName);
        Thread.sleep(500);
        driver.findElement(By.xpath(".//tr[last()]/td[1]/../td[3]/div/a[1]")).click();

    }

    public void toTheLastPage() throws InterruptedException {
        System.out.println("size " + arrayForPageSwitch.size());
        if (arrayForPageSwitch.size() == 10) {
            Thread.sleep(500);
            System.out.println(driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[2]/div/ul/li[last() - 1]")).getText());
            driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[2]/div/ul/li[last() - 1]")).click();
        }
    }

    public void changeNameOfExpenseType() throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldTypeName, UtilStore.expenseTypeName + "1");
    }

    public boolean isNameChanged() throws InterruptedException {
        Thread.sleep(500);
        if (driver.getPageSource().contains(UtilStore.expenseTypeName + "1")) {
            return true;
        } else
            return false;
    }

    public void deleteCreatedExpenseType() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.xpath(".//tr[last()]/td[1]/../td[3]/div/a[2]")).click();
        Thread.sleep(500);
        buttonOk.click();
    }


    public boolean isTypeDeleted() throws InterruptedException {
        Thread.sleep(500);
        if (driver.getPageSource().contains(UtilStore.expenseTypeName + "1")) {
            return false;
        } else
            return true;
    }
}
