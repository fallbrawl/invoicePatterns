package com.invoice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by paul on 22.02.16.
 */
public class PitchPage extends Page {
    @FindBy(className = "pitch_move")
    WebElement buttonPitchMove;

    @FindBy(xpath = ".//*[@id='body-wrapper']/div[1]/div/div[3]/section/div[2]/div[2]/div[2]/form/a")
    WebElement buttonMoveToAnotherStore;

    public PitchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void moveFromParty() throws InterruptedException {
        buttonPitchMove.click();
        Thread.sleep(1000);

    }

    public MovementPage moveFromStore() {
        buttonMoveToAnotherStore.click();
        return PageFactory.initElements(driver, MovementPage.class);

    }
}
