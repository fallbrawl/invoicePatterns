package com.invoice.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paul on 24.02.16.
 */
public class AddDocumentSixthPage extends Page{

    @FindBy(name = "file")
    public WebElement formForFile;

    @FindBy(className = "icon_save_big")
    public WebElement buttonSaveAgreement;

    @FindBy(className = "icon_in_big")
    public WebElement buttonUseAgreement;

    @FindBy(className = "fa-envelope")
    public WebElement buttonSendAgreement;

    @FindBy(className = "loader_wrapper")
    public WebElement loader;

    @FindAll(@FindBy(className = "btn-primary"))
    public List<WebElement> allElementsInList;

    @FindBy (name = "date_to_file")
    public WebElement fieldCalendarForFile;

    @FindBy(xpath = ".//*[@id='select2-chosen-10']")
    WebElement dropdownSetTypeOfAgreement;

    @FindBy(id = "select2-result-label-18")
    WebElement dropdownvariantTypeOfAgreementServices;

    @FindBy(id = "s2id_autogen10_search")
    WebElement fieldForSearchTypeOfAgreement;


    public void uploadFile() {
        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        //Вставить путь для Windows
        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            formForFile.sendKeys(pathLinux);
        }
        else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            formForFile.sendKeys(pathWindows);
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

    public void setTypeOfAgreement(String whatType) throws InterruptedException, NoSuchFieldException {
        Thread.sleep(500);
        dropdownSetTypeOfAgreement.click();
        Thread.sleep(500);
        fieldForSearchTypeOfAgreement.click();
        Thread.sleep(500);
        switch (whatType) {
            case "Услуги":
                typeHere(fieldForSearchTypeOfAgreement, "Услуги");
                break;
            case "Товарный":
                typeHere(fieldForSearchTypeOfAgreement, "Товарный");
                break;
            case "Лицензионный":
                typeHere(fieldForSearchTypeOfAgreement, "Лицензионный");
                break;
            default: throw new NoSuchFieldException();
        }
        Thread.sleep(500);
        typeHere(fieldForSearchTypeOfAgreement,Keys.ENTER);

    }

    public AddDocumentSeventhPage toTheNextStep() {

        allElementsInList.get(2).click();
        return PageFactory.initElements(driver,AddDocumentSeventhPage.class);
    }

    public AddDocumentSixthPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }
}
