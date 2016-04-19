package com.invoice.pages.AddDocumentPages;

import com.invoice.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by paul on 24.02.16.
 */
public class AddDocumentSixthPage extends Page {

    @FindBy(name = "file")
    public WebElement formForFile;

    @FindBy(css = ".fa.fa-floppy-o.fa-color-info_big")
    public WebElement buttonSaveAgreement;

    @FindBy(css = ".fa.fa-sign-in.fa-color-success")
    public WebElement buttonUseAgreement;

    @FindBy(className = "fa-envelope")
    public WebElement buttonSendAgreement;



    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/div/a[2]/span")
    public WebElement buttonGoToTheNextMonthInCalendar;

    //@FindBy(className = "col-xs-6")
    @FindBy(xpath = ".//*[@id='agreement-table']/tbody/tr/td[3]/input")
    public WebElement fieldAgreementDelay;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[1]/a")
    public WebElement cellFirstDayOfTheNextWeekInCalendar;

    @FindBy(className = "loader_wrapper")
    public WebElement loader;

    @FindAll(@FindBy(className = "btn-primary"))
    public List<WebElement> allElementsInList;

    @FindBy (name = "date_to_file")
    public WebElement fieldCalendarForFile;

    @FindBy(id = "s2id_type_agreement")
    WebElement dropdownSetTypeOfAgreement;

    @FindBy(id = "select2-result-label-18")
    WebElement dropdownvariantTypeOfAgreementServices;

    @FindBy(className = "select2-focused")
    WebElement fieldForSearchTypeOfAgreement;


    public void uploadFile() throws InterruptedException {
        Thread.sleep(500);
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

    public void setAgreementDelay(String howManyDays) throws InterruptedException {
        Thread.sleep(1000);
        typeHere(fieldAgreementDelay, howManyDays);
    }

    public void setBestBefore() throws InterruptedException {
        fieldCalendarForFile.click();
        Thread.sleep(500);
        buttonGoToTheNextMonthInCalendar.click();
        Thread.sleep(500);
        buttonGoToTheNextMonthInCalendar.click();
        Thread.sleep(500);
        cellFirstDayOfTheNextWeekInCalendar.click();

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
