package invoice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by NEXUS on 31.01.2016.
 */
public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;


    }

    public void email(String email) {
        driver.findElement(By.name("email")).sendKeys(email);

    }

    public void password(String password) {
        driver.findElement(By.name("password")).sendKeys(password);

    }

    public MainPage enter() {
        driver.findElement(By.xpath("//*[@id=\"submit_form_button_login\"]")).submit();

        return new MainPage(driver);
    }
}
