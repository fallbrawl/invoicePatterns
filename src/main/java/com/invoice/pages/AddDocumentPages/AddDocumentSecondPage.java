package com.invoice.pages.AddDocumentPages;

import com.invoice.pages.Page;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
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

    //@FindBy(className = "col-xs-6")
    @FindBy(xpath = ".//*[@id='agreement-table']/tbody/tr/td[3]/input")
    public WebElement fieldAgreementDelay;

    @FindBy(xpath = ".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[1]/a")
    public WebElement cellFirstDayOfTheNextWeekInCalendar;

    @FindBy(className = "fa-commenting")
    public WebElement buttonComment;

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

//    @FindBy(css = ".fa.fa-floppy-o.fa-color-info_big")
//    public WebElement buttonSaveAgreement;

    @FindBy(className = "fa-floppy-o")
    public WebElement buttonSaveAgreement;

//    @FindBy(css = ".fa.fa-sign-in.fa-color-success")
//    public WebElement buttonUseAgreement;

    @FindBy(className = "fa-sign-in")
    public WebElement buttonUseAgreement;


    @FindBy(className = "send_to_manager")
    public WebElement buttonSendAgreement;

    @FindBy(className = "loader_wrapper")
    public WebElement loader;

    @FindAll(@FindBy(className = "btn-primary"))
    public List<WebElement> allElementsInList;

    @FindBy(name = "date_to_file")
    public WebElement fieldCalendarForFile;

    public void extractNumberDocument() {
        System.out.print("DOc = " + driver.findElement(By.xpath("//div[1]/span/a")).getText());
        UtilStore.buyingNumber = driver.findElement(By.xpath("//div[1]/span/a")).getText();
    }

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

    public String getDocumentName() {
        return documentName;
    }

    public void setTypeOfAgreementService(String whatType) throws InterruptedException, NoSuchFieldException {

        Thread.sleep(500);
        dropdownSetTypeOfAgreement.click();
        Thread.sleep(500);
//        fieldForSearchTypeOfAgreement.click();
//        Thread.sleep(500);

        List<WebElement> typesOfDocuments;

        switch (whatType) {

            case "Услуги":

                typesOfDocuments = driver.findElements(By.className("select2-result-label"));
                System.out.println("USLUGI:");
                for (WebElement a : typesOfDocuments) {
                    if (a.getText().contains("Услуги")) {
                        Thread.sleep(500);
                        a.click();
                        break;
                    }
                }
                break;

            case "Товарный":
                typesOfDocuments = driver.findElements(By.className("select2-result-label"));
                for (WebElement a : typesOfDocuments) {
                    if (a.getText().equals("Товарный")) {
                        Thread.sleep(500);
                        a.click();
                        break;
                    }
                }
                break;

            case "Лицензионный":
                typesOfDocuments = driver.findElements(By.className("select2-result-label"));
                for (WebElement a : typesOfDocuments) {
                    if (a.getText().equals("Лицензионный")) {
                        Thread.sleep(500);
                        a.click();
                        break;
                    }
                }
                break;

            default:
                throw new NoSuchFieldException();
        }
    }

    public void uploadFile() throws InterruptedException {
        Thread.sleep(1500);
        String osVersion = System.getProperty("os.name");
        System.out.println(osVersion);

        //Вставить путь для Windows
        if (osVersion.contains("Linux")) {
            String pathLinux = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
            System.out.println(pathLinux);
            formForFile.sendKeys(pathLinux);
        } else {
            String pathWindows = (System.getProperty("user.dir") + "\\src\\main\\Resources\\agreement.pdf");
            System.out.println(pathWindows);
            formForFile.sendKeys(pathWindows);
        }
    }

    public void agreement(String what) throws NoSuchFieldException, InterruptedException {
        switch (what) {

            case "Save":
                Thread.sleep(1000);
                click(buttonSaveAgreement);
                break;

            case "Send":
                Thread.sleep(1000);
                click(buttonSendAgreement);
                break;

            case "Use":
                Thread.sleep(1000);
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

    public AddDocumentThirdPage toTheNextStep() throws InterruptedException {

        Thread.sleep(2000);
        allElementsInList.get(2).click();
        return PageFactory.initElements(driver, AddDocumentThirdPage.class);
    }

    public AddDocumentSecondPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void openBadComment() throws InterruptedException {
        Thread.sleep(1000);
        buttonComment.click();
    }

    public boolean isBadCommentSaved() throws InterruptedException {
        Thread.sleep(500);

        if (driver.findElement(By.className("popover-content")).getText().equals("Bad comment on decline")) {
            return true;
        } else {
            return false;
        }
    }
}


