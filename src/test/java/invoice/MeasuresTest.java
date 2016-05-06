package invoice;

import com.invoice.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by paul on 14.04.16.
 */
public class MeasuresTest extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private SettingsPage settingsPage = PageFactory.initElements(getWebDriver(), SettingsPage.class);
    private MeasurePage measurePage = PageFactory.initElements(getWebDriver(), MeasurePage.class);
    private MainPage mainPage;

    @org.testng.annotations.Test(priority = 11)

    public void measuresTest() throws InterruptedException {

        loginPage.open();
        mainPage = loginPage.loginAs(admin);

        settingsPage.open();
        settingsPage.editMeasures();

        measurePage.addMeasure();
        measurePage.enterNameMeasure();
        measurePage.saveMeasure();

        settingsPage.open();
        Assert.assertTrue(settingsPage.isMeasureAdded());

        measurePage.open();
        measurePage.checkHowManyPages();
        measurePage.editCreatedMeasure();
        measurePage.hideMeasure();
        measurePage.saveMeasure();
        measurePage.checkHowManyPages();
        Assert.assertTrue((measurePage.isMeasureEdited()));

        settingsPage.open();
        Assert.assertTrue(settingsPage.isMeasureHided());

        measurePage.open();
        measurePage.checkHowManyPages();
        measurePage.deleteCreatedMeasure();
        Assert.assertTrue(measurePage.isMeasureDeleted());
        measurePage.logout();


    }
}
