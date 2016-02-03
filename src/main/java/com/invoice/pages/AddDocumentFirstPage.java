package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 01.02.16.
 */
public class AddDocumentFirstPage extends Page {

    @FindBy(id = "s-name_user_info")
    public WebElement fieldNameOne;

    @FindBy(id = "b-name_user_info")
    public WebElement fieldNameTwo;

    @FindBy(className = "btn-primary")
    public WebElement buttonNextStep;


    public void enterNames() {
        typeHere(fieldNameOne, "wow1");
        typeHere(fieldNameTwo, "wow2");
    }

    public AddDocumentSecondPage toTheNextStep(){
        click(buttonNextStep);
        return PageFactory.initElements(driver, AddDocumentSecondPage.class);
    }

    public AddDocumentFirstPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("addDocument.url"));
    }
}
