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
 * Created by paul on 05.04.16.
 */
public class ExpensesPage extends Page {

    @FindBy(className = "btn-warning")
    WebElement buttonAddNewExpense;

    @FindBy(id = "name_expense")
    WebElement fieldNameExpense;

    @FindBy(id = "additional_expense_price")
    WebElement fieldAdditionalExpensePrice;

    @FindBy(id = "select2-chosen-2")
    WebElement dropdownSelectTypeExpense;

    @FindBy(className = "btn-primary")
    WebElement buttonSave;

    @FindAll(@FindBy(className = "select2-result-label"))
    List<WebElement> dropdownArrayListOfExpenseTypes;

    @FindAll(@FindBy(className = "link_divider"))
    List<WebElement> arrayForPageSwitch;


    public ExpensesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("expense.url"));
    }

    public void addNewExpense() throws InterruptedException {
        Thread.sleep(500);
        buttonAddNewExpense.click();
    }

    public void fillNewExpenseFields() throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldNameExpense, "NameExpense" + UtilStore.addSimpleDate());
        dropdownSelectTypeExpense.click();
        for (WebElement a : dropdownArrayListOfExpenseTypes) {
            if (a.getText().equals(UtilStore.expenseTypeName)) {
                Thread.sleep(500);
                System.out.println("Expense" + a.getText());
                a.click();
            }
        }
        typeHere(fieldAdditionalExpensePrice, "100.00");
        Thread.sleep(500);
    }

    public void save() throws InterruptedException {
        Thread.sleep(500);
        buttonSave.click();

    }

    public void toTheLastPage() throws InterruptedException {
       // if (driver.findElement(By.className("pagination")).isDisplayed()) {
        if (arrayForPageSwitch.size() == 10) {

            Thread.sleep(500);
            System.out.println(driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[2]/div/ul/li[last() - 1]")).getText());
            driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[2]/div/ul/li[last() - 1]")).click();
        }
    }

    public void deleteExpense() throws InterruptedException {
            Thread.sleep(500);
            driver.findElement(By.xpath(".//tr[last()]/td[1]/../td[3]/div/a[2]")).click();
            Thread.sleep(500);
            buttonOk.click();

    }

    public boolean isExpenseDeleted() {
        if (driver.getPageSource().contains(UtilStore.expenseTypeName)) {
            return false;
        } else
            return true;
    }
}
