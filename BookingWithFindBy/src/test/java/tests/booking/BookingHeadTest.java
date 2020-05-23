package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import properties.Path;
import settings.Config;
import steps.BaseSteps;
import webDriver.Driver;
import webPages.booking.MainPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingHeadTest {
    WebDriver driver;
    Properties properties;
    List<WebElement> list;

    @Before
    public void preCondition() throws IOException {
        driver = Driver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(Path.BOOKING_PATH);
        list = new ArrayList<>();
    }

    @Test
    public void CheckHeadTest() throws InterruptedException {
        MainPage.bookingLogIn(driver, properties);
        TimeUnit.SECONDS.sleep(3);
        addToList("//*[@class='header-wrapper']/img");
        addToList("//*[@data-id='currency_selector']");
        addToList("//*[@data-id='language_selector']");
        addToList("//*[@data-id='notifications']");
        addToList("//*[contains(@class,'uc_helpcenter')]");
        addToList("//*[contains(@class,'uc_account logged')]");
        addToList("//*[contains(@id,'current_account')]");
        addToList("//*[contains(@data-ga-track,'accommodation')]");
        addToList("//*[contains(@data-ga-track,'flights')]");
        addToList("//*[contains(@data-ga-track,'cars')]");
        addToList("//*[contains(@data-ga-track,'attractions')]");
        addToList("//*[contains(@data-ga-track,'airport_taxis')]");
        assertEquals(12, list.size());
    }

    public void addToList(String xPath) {
        list.add(driver.findElement(By.xpath(xPath)));
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}
