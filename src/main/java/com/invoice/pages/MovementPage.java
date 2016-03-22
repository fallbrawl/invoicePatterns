package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by paul on 22.02.16.
 */
public class MovementPage extends Page {

    String partyNumber = "";

    public MovementPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "icon_check")
    public WebElement buttonCheck;

    @FindBy(className = "btn-success")
    public WebElement buttonDone;

    @FindBy(xpath = ".//*[@id='purchaseListFilter']/div[2]/span/div[4]/label")
    public WebElement labelDone;

//    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[6]/div[1]/div[1]/table/tbody/tr[1]/td[3]/div/a")
//    public WebElement cellTransitNumber;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[5]/div[1]/div[1]/table/tbody/tr[1]/td[3]/div/a")  //TODO: перенести из икспаза во что то более приличное
    public WebElement cellPartyNumber;

//    public void getPartyNumber() {
//        partyNumber = cellTransitNumber.getText();
//        System.out.println("Party number is: " + partyNumber);
//
//    }

    public void save() {

        buttonCheck.click();
    }

    public void enter() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(buttonDone));
        buttonDone.click();
    }

    public void switchToLabelDone() {
        labelDone.click();
    }

    public PitchPage openNeededParty() {
        cellPartyNumber.click();
        return PageFactory.initElements(driver, PitchPage.class);
       // driver.findElement(By.linkText(partyNumber)).click();
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("pitch.url"));
    }
}
