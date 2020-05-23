package allTests.bookingTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import otherStuff.CreateTempEmailOnTrashMail;
import otherStuff.MailChecker;
import webDriver.Config;
import webDriver.Driver;
import webPages.BaseActionsOfWebPages;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class BookingNewUserTest {

    WebElement element;
    WebDriver driver;
    String BOOKING_PATH = "src\\test\\java\\properties\\booking.properties";
    Properties properties;



    @Before
    public void preCondition() throws IOException, InterruptedException {
        driver = Driver.getWebDriver(Config.CHROME);
        CreateTempEmailOnTrashMail.trashmailGetNewMail(driver);
        driver.get("https://www.booking.com/");
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {

        bookingRegistration();
        TimeUnit.SECONDS.sleep(3);
        MailChecker.confirmLinkOnMyRealMail("booking.com", driver);
        String currentHandle = driver.getWindowHandle();
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(text(), \"Подтверждаю\")]");
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }

        TimeUnit.SECONDS.sleep(8);
        driver.get("https://www.booking.com/");
        TimeUnit.SECONDS.sleep(2);
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"profile-menu-trigger--content\"]");
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"mydashboard\")]");
        Assert.assertEquals(driver.findElements(By.xpath("//*[@class=\"email-confirm-banner\"]")).size(), 0);

    }

    public void bookingRegistration() throws IOException, InterruptedException {
        properties = BaseActionsOfWebPages.getProperties(BOOKING_PATH);
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"current_account_create\"]");
        TimeUnit.SECONDS.sleep(1);
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"login_name_register\"]", properties.getProperty("NEW_MAIL"));
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"nw-register\")]/button");
        TimeUnit.SECONDS.sleep(1);
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"password\"]", properties.getProperty("PASSWORD"));
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"confirmed_password\"]", properties.getProperty("PASSWORD"));
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@type, \"submit\")]");
    }

    @After
    public void postCondition() {
        BaseActionsOfWebPages.destroyDriver(driver);
    }

}
