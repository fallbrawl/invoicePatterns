package com.invoice.pages;

import com.invoice.data.UserData;
import com.invoice.utils.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {

    @FindBy(name = "email")
    public WebElement fieldEmailLogin;

    @FindBy(name = "password")
    public WebElement fieldPasswordLogin;

    @FindBy(xpath = "//*[@id=\"submit_form_button_login\"]")
    public WebElement buttonEnterLogin;

    public MainPage loginAs(UserData admin) throws InterruptedException {
        Thread.sleep(1000);
        typeHere(fieldEmailLogin, admin.email);
        typeHere(fieldPasswordLogin, admin.password);
        buttonEnterLogin.submit();
        return PageFactory.initElements(driver, MainPage.class);
    }

    public MainPage loginAs(String newUserEmail, String newUserPassword) throws InterruptedException {
        Thread.sleep(1000);
        typeHere(fieldEmailLogin, newUserEmail);
        typeHere(fieldPasswordLogin, newUserPassword);
        buttonEnterLogin.submit();
        return PageFactory.initElements(driver, MainPage.class);
    }

    @Override
    public void open() {

        driver.get(ConfigProperties.getProperty("login.url"));
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
