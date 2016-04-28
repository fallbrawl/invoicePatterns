package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by paul on 30.03.16.
 */
public class ProductsPage extends Page {

    @FindBy(className = "btn-warning")
    WebElement buttonAddNewProduct;

    @FindBy(name = "name_product")
    WebElement fieldProductName;

    @FindBy(name = "code_product")
    WebElement fieldProductCode;

    @FindBy(id = "name_category")
    WebElement fieldEnterNewCategoryName;

    @FindBy(id = "modal_success")
    WebElement buttonOk;

    @FindBy(id = "price_product")
    WebElement fieldPriceProduct;

    @FindBy(id = "recprice_product")
    WebElement fieldRecommendedProductPrice;

    @FindBy(className = "btn-high")
    WebElement buttonSave;

    @FindBy(id = "select2-chosen-4")
    WebElement dropdownTypeOfAgreement;

    @FindBy(className = "fa-tree")
    WebElement buttonProductsTree;


    @FindAll(@FindBy(className = "select2-result-selectable"))
    List<WebElement> dropdownArrayOfTypesAgreements;

    @FindAll(@FindBy(className = "btn-primary"))
    List<WebElement> arrayAddItemButtons;


    public void addNewItem() throws InterruptedException {
        Thread.sleep(1000);
        buttonAddNewProduct.click();
    }


    public void fillNewItem(String whatType) throws InterruptedException {

        dropdownTypeOfAgreement.click();
        Thread.sleep(1000);

        for (WebElement a : dropdownArrayOfTypesAgreements) {
            System.out.println(a.getText());
            System.out.println("Sze: " + dropdownArrayOfTypesAgreements.size());
            if (a.getText().contains(whatType)) {
                a.click();
                break;
            }
        }

        typeHere(fieldRecommendedProductPrice, "100");
        typeHere(fieldPriceProduct, "75");

        Thread.sleep(1000);
        WebElement buttonAddCategory = arrayAddItemButtons.get(0);
        Thread.sleep(1000);
        buttonAddCategory.click();
        Thread.sleep(1000);
        typeHere(fieldEnterNewCategoryName, "dfjgdk" + UtilStore.addSimpleDate());
        Thread.sleep(1000);
        buttonOk.click();
        Thread.sleep(1000);

        typeHere(fieldProductName, UtilStore.nameProduct);

        System.out.println("nameproduct " + UtilStore.nameProduct);

        typeHere(fieldProductCode, "607" + UtilStore.addExtendedDate());

        Thread.sleep(500);

        buttonSave.click();

    }


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("products.url"));
    }

    public void openProductsTree() throws InterruptedException {
        Thread.sleep(500);
        buttonProductsTree.click();
    }
}
