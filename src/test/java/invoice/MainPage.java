package invoice;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

/**
 * Created by NEXUS on 31.01.2016.
 */
public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String title() {
        String title = driver.getTitle();
        System.out.println(title);
        return title;
    }
}
