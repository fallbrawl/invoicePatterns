package com.invoice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paul on 17.02.16.
 */
public class CreatePurchaseSecondPage extends Page {

    private String documentName = null;

    public CreatePurchaseSecondPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "li.active")
    public WebElement textNumber;

    @FindBy(name = "file")
    public WebElement fieldForFile;

    @FindBy(name = "file_delay")
    public WebElement fieldAgreementDelay;

    @FindBy(className = "icon_save_big")
    public WebElement buttonSaveAgreement;

    @FindBy(className = "fa-envelope")
    public WebElement buttonSendAgreement;

    @FindBy(className = "icon_in_big")
    public WebElement buttonUseAgreement;

    @FindBy(className = "loader_wrapper")
    public WebElement loader;

    @FindAll(@FindBy(className = "btn-primary"))
    public List<WebElement> allElementsInList;

    public void extractNumber() {

        String mydata = textNumber.getText();

        System.out.println("Text is : " + mydata);

        Pattern pattern = Pattern.compile("S[\\d -]*");
        Matcher matcher = pattern.matcher(mydata);

        if (matcher.find()) {
            documentName = matcher.group(0);
            System.out.println("EXTRACTED regex expression " + documentName);
        }
    }

    public void uploadFile() {

        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        //Вставить путь для Windows
        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            fieldForFile.sendKeys(pathLinux);
        }
        else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            fieldForFile.sendKeys(pathWindows);
        }
    }

    public void agreement(String what) throws NoSuchFieldException, InterruptedException {
        switch (what) {

            case "Save":
                click(buttonSaveAgreement);
                break;

            case "Send":
                click(buttonSendAgreement);
                break;

            case "Use":
                click(buttonUseAgreement);
                break;

            default:
                throw new NoSuchFieldException();
        }
    }

    public CreatePurchaseThirdPage toTheNextStep() {
//        for (WebElement anAllElementsInList : allElementsInList) {
//            System.out.println("Element: " + anAllElementsInList.getText());
//        }
        allElementsInList.get(2).click();
        return PageFactory.initElements(driver,CreatePurchaseThirdPage.class);
    }

    @Override
    public void open() {

    }

    public void setAgreementDelay() {
        typeHere(fieldAgreementDelay,"2");
    }
}
