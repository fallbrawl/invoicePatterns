package com.invoice.pages.AddDocumentPages;

import com.invoice.pages.Page;
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
 * Created by paul on 01.02.16.
 */
public class AddDocumentSecondPage extends Page {

    private String documentName = null;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/div/a[2]/span")
    public WebElement buttonGoToTheNextMonthInCalendar;

    @FindBy(name = "file_delay")
    public WebElement fieldAgreementDelay;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[1]/a")
    public WebElement cellFirstDayOfTheNextWeekInCalendar;

    @FindBy(css = "li.active")
    public WebElement textNumber;

    @FindBy(name = "file")
    public WebElement formForFile;

    @FindBy(id = "s2id_type_agreement")
    WebElement dropdownSetTypeOfAgreement;

    @FindBy(id = "select2-result-label-21")
    WebElement dropdownvariantTypeOfAgreementServices;

    @FindBy(className = "select2-focused")
    WebElement fieldForSearchTypeOfAgreement;

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

    public void setTypeOfAgreementService(String whatType) throws InterruptedException, NoSuchFieldException {

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

    public void setAgreementDelay() throws InterruptedException {
        Thread.sleep(1000);
        typeHere(fieldAgreementDelay,"7");
    }

    public void setBestBefore() throws InterruptedException {
        fieldCalendarForFile.click();
        Thread.sleep(500);
        buttonGoToTheNextMonthInCalendar.click();
        Thread.sleep(500);
        cellFirstDayOfTheNextWeekInCalendar.click();

    }

    public AddDocumentThirdPage toTheNextStep() throws InterruptedException {

        Thread.sleep(1000);
        allElementsInList.get(2).click();
        return PageFactory.initElements(driver,AddDocumentThirdPage.class);
    }

    public AddDocumentSecondPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }
}


