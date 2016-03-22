package com.invoice.pages.AddDocumentPages;


import com.invoice.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by paul on 11.02.16.
 */
public class AddDocumentFourthPage extends Page {

    @FindAll(@FindBy(className = "loader_on_event"))
    List<WebElement> buttonBuyForAll;

    public AddDocumentFourthPage(WebDriver driver) {
        super(driver);
    }

    public AddDocumentFifthPage buyForAll() {
        for (WebElement a : buttonBuyForAll) {
            if (a.getText().equals("Для всех")) {
                a.click();
            }

        }

        return PageFactory.initElements(driver, AddDocumentFifthPage.class);
    }

    @Override
    public void open() {

    }
}
