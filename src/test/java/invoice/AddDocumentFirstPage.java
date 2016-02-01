package invoice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by paul on 01.02.16.
 */
public class AddDocumentFirstPage {
    private final WebDriver driver;
    private String name2;

    public AddDocumentFirstPage(WebDriver driver) {
        this.driver = driver;

    }

    public void setName1(String name1) {

    }

    public void setName2(String name2) {

    }


    public AddDocumentSecondPage save() {
        return new AddDocumentSecondPage(driver);
    }
}
