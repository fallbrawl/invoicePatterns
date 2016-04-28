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

    public void addCategory() throws InterruptedException {
        Thread.sleep(500);
        buttonAddCategory.click();
    }


    public void enterNameOfCategory(String level) throws InterruptedException {
        Thread.sleep(500);
        typeHere(fieldNameCategory, nameCategory + level);

    }

    public void saveCategory() throws InterruptedException {
        Thread.sleep(500);
        buttonSave.click();
    }

    public void setMutualCategory() throws InterruptedException {

        Thread.sleep(500);
        dropdownSelectMutualCategory.click();
        Thread.sleep(500);

        List<WebElement> arrayVariantsOfMutualCategories = driver.findElements(By.className("select2-result-label"));
        for (WebElement a : arrayVariantsOfMutualCategories) {

            if (a.getText().equals(nameCategory + "Parent")) {
                a.click();
                break;
            }
        }
    }

    public void expandCreatedCategories() throws InterruptedException {

        Thread.sleep(500);
        System.out.println(driver.findElement(By.xpath("//div/span[position() = 4 and normalize-space(.) = 'category140353Parent']")).getText());
        driver.findElement(By.xpath("//div/span[position() = 4 and normalize-space(.) = 'category140353Parent']/preceding-sibling::a]")).click();
        //driver.findElement(By.xpath("//div[span[position() = 4 and normalize-space(.) = 'category140353Parent']/a")).click();
        //tr[td[@class='name'] ='Brand']/td[@class='desc']

    }
}
