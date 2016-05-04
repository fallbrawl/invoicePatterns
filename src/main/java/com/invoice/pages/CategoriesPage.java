package com.invoice.pages;

import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by paul on 28.04.16.
 */
public class CategoriesPage extends Page {

    private String nameCategory = "category" + UtilStore.addSimpleDate();

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[1]/form/a")
    WebElement buttonAddCategory;

    @FindBy(id = "name_category")
    WebElement fieldNameCategory;

    @FindBy(className = "btn-primary")
    WebElement buttonSave;

    @FindBy(id = "s2id_select2multi")
    WebElement dropdownSelectMutualCategory;

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void expandCreatedParentCategory(int toLevel) throws InterruptedException {
        //System.out.println(driver.findElement(By.xpath("//div/span[position() = 4 and normalize-space(.) = 'category140353Parent']")).getText());

        Thread.sleep(500);

        driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/a")).click();
        Thread.sleep(500);

        driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/a/../../ul/li/div/span[3]")).click();


    }

    public void enterNameOfCategoryInTree(String whatLevel) throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldNameCategory, nameCategory + whatLevel);
        Thread.sleep(500);
        buttonOk.click();
    }


    public void expandCreatedSubCategories() throws InterruptedException {
        Thread.sleep(500);

        driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/a/../../ul/li/div/a")).click();
    }

    public void createSubSubCategory() throws InterruptedException {
        Thread.sleep(500);

        driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/a/../..//ul/li/ul/li/div/span[3]")).click();

    }

    public void expandCreatedSubSubCategories() throws InterruptedException {
        Thread.sleep(500);

        driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/a/../..//ul/li/ul/li/div/a")).click();

    }

    public void deleteSubSubCategory() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/a/../..//ul/li/ul/li/div/span[1]")).click();
        Thread.sleep(500);
        buttonOk.click();

    }

    public void editSubCategoryName() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/a/../../ul/li/div/span[2]")).click();

    }

    public boolean isCategoryEdited() throws InterruptedException {
        Thread.sleep(500);
        System.out.println(nameCategory + "SubEdited");
        System.out.println(driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/a/../../ul/li/div//span[4]")).getText());


        if (driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/a/../../ul/li/div//span[4]")).getText().contains(nameCategory + "SubEdited")) {
            return true;
        } else
            return false;
    }

    public boolean isSubCategoriesNotDeleted() throws InterruptedException {
        Thread.sleep(550);
        return (driver.getPageSource().contains(nameCategory + "SubSub") && driver.getPageSource().contains("SubSubSub"));
    }

    public void deleteParentCategory() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[span = '" + nameCategory + "Parent" + "']/span[1]")).click();
        Thread.sleep(500);
        buttonOk.click();
    }
}
