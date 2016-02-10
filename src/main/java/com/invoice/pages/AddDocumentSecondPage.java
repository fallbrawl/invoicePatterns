package com.invoice.pages;

import com.invoice.utils.ConfigProperties;
import com.invoice.utils.UtilStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paul on 01.02.16.
 */
public class AddDocumentSecondPage extends Page {

    private String documentName = null;

//    public static void documentSecondStep(WebDriver drv) throws InterruptedException {
//
//        drv.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//
//        UtilityStore.waitForElementsAbsence(drv, 2, "loader_wrapper");
//
//        String mydata = drv.findElement(By.cssSelector("li.active")).getText();
//
//        System.out.println("Text is : " + mydata);
//
//        Pattern pattern = Pattern.compile("S[\\d -]*");
//        Matcher matcher = pattern.matcher(mydata);
//
//        if (matcher.find()) {
//            documentName = matcher.group(0);
//            System.out.println("EXTRACTED regex expression " + documentName);
//        }
//
//
//
//        UtilityStore.uploadFile(drv, "file", "/home/paul/Desktop/2015-12-30-150350_1920x1080_scrot.pdf");
//
//        drv.findElement(By.className("icon_save_big")).click();
//        UtilityStore.waitForElementsAbsence(drv, 2, "loader_wrapper");
//
//        drv.findElement(By.className("fa-envelope")).click();
//        UtilityStore.waitForElementsAbsence(drv, 2, "loader_wrapper");
//
//        //Confirmin' document in manager's section
//
//        drv.get("invoicedev.php.attractgroup.com/public/accounting/manager");
//
//        drv.findElement(By.className("icon_check")).click();
//        drv.findElement(By.className("btn-success")).click();
//        UtilityStore.waitForElementsAbsence(drv, 2, "loader_wrapper");
//
//        //Gettin' back to document's attaching
//
//        drv.navigate().back();
//        UtilityStore.waitForElementsAbsence(drv, 2, "loader_wrapper");
//
//        drv.findElement(By.className("icon_in_big")).click();
//        UtilityStore.waitForElementsAbsence(drv, 2, "loader_wrapper");
//
//        List<WebElement> neededBut = drv.findElements(By.className("btn-primary"));
//        System.out.println("Size of List: " + neededBut.size());
//
//        for (WebElement aNeededBut : neededBut) {
//
//            System.out.println("Classes in List: " + aNeededBut.getText());
//        }
//
//        WebElement wow = neededBut.get(2);
//        wow.click();
//
//    }

    @FindBy(css = "li.active")
    public WebElement textNumber;

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

        String paths = (System.getProperty("user.dir") + "/src/main/Resources/agreement.pdf");
        System.out.println(paths);
        formForFile.sendKeys(paths);
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

    public AddDocumentThirdPage toTheNextStep() {
//        for (WebElement anAllElementsInList : allElementsInList) {
//            System.out.println("Element: " + anAllElementsInList.getText());
//        }
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


