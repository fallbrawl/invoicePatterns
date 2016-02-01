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

    public void as(String role) {
        if (role.equals("manager_buyer")) {

            driver.get("http://invoicedev.php.attractgroup.com/public/login");
            driver.findElement(By.name("email")).sendKeys("pavel.a.manager.buyer@attractgroup.com");
            driver.findElement(By.name("password")).sendKeys("attract");
            driver.findElement(By.id("submit_form_button_login")).click();

        } else if (role.equals("manager_seller")) {
            driver.get("http://invoicedev.php.attractgroup.com/public/login");
            driver.findElement(By.name("email")).sendKeys("pavel.a.manager_sales@attractgroup.com");
            driver.findElement(By.name("password")).sendKeys("attract");
            driver.findElement(By.id("submit_form_button_login")).click();
        } else if (role.equals("accounter")) {
            driver.get("http://invoicedev.php.attractgroup.com/public/login");
            driver.findElement(By.name("email")).sendKeys("pavel.a.accounter@attractgroup.com");
            driver.findElement(By.name("password")).sendKeys("attract");
            driver.findElement(By.id("submit_form_button_login")).click();
        } else if (role.equals("admin")) {
            driver.get("http://invoicedev.php.attractgroup.com/public/login");
            driver.findElement(By.name("email")).sendKeys("pavel.a@attractgroup.com");
            driver.findElement(By.name("password")).sendKeys("attract");
            driver.findElement(By.id("submit_form_button_login")).click();
        } else {
            throw new IllegalArgumentException("No such role of user!");
        }
    }
}
