package tests.booking.cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web_driver.Config;
import steps.BaseSteps;
import steps.MailSteps;
import steps.trashMail.TrashMailNewUser;
import web_driver.Driver;
import web_pages.booking.ActionsMainPage;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingNewUserTest {
    WebDriver driver;
    Properties properties;
    static String BOOKING_PATH = "src/test/resources/booking.properties";

    @Before
    public void preCondition() throws IOException, InterruptedException {
        driver = Driver.getWebDriver(Config.CHROME);
        TrashMailNewUser.trashMailGetNewMail(driver);
        driver.get("https://www.booking.com/");
    }

    @cucumber.api.java.Before
    public void precondition() {
        driver = Driver.getWebDriver(Config.CHROME);

    }

    @Given("I go to trashmail.com")
    public void iGoToTrashmailCom() {
    }

    @Then("I get new trash mail")
    public void iGetNewTrashMail() throws IOException, InterruptedException {
        TrashMailNewUser.trashMailGetNewMail(driver);
    }

    @Then("I go to booking.com")
    public void iGoToBookingCom() {
        driver.get("https://www.booking.com/");
    }

    @Then("I create new user")
    public void iCreateNewUser() throws IOException, InterruptedException {
        ActionsMainPage.bookingRegistration(driver, properties, BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I go to yandex.ru")
    public void iGoToYandexRu() throws IOException, InterruptedException {
        MailSteps.confirmLinkOnYandexMail("booking.com", driver);
    }

    @Then("I confirm email")
    public void iConfirmEmail() throws InterruptedException {
        String currentHandle = driver.getWindowHandle();
        BaseSteps.findElementClick(driver, "//*[contains(text(),'Подтверждаю')]");
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
        TimeUnit.SECONDS.sleep(8);
    }

    @Then("I again go to booking.com")
    public void iAgainGoToBookingCom() throws InterruptedException {
        driver.get("https://www.booking.com/");
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I go to user page")
    public void iGoToUserPage() {
        BaseSteps.findElementClick(driver, "//*[@id='profile-menu-trigger--content']");
        BaseSteps.findElementClick(driver, "//*[contains(@class,'mydashboard')]");
    }

    @Then("I check the lack of a banner")
    public void iCheckTheLackOfABanner() {
        assertEquals(driver.findElements(By.xpath("//*[@class='email-confirm-banner']")).size(), 0);
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        ActionsMainPage.bookingRegistration(driver, properties, BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
        MailSteps.confirmLinkOnYandexMail("booking.com", driver);
        String currentHandle = driver.getWindowHandle();
        BaseSteps.findElementClick(driver, "//*[contains(text(),'Подтверждаю')]");
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
        TimeUnit.SECONDS.sleep(8);
        driver.get("https://www.booking.com/");
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[@id='profile-menu-trigger--content']");
        BaseSteps.findElementClick(driver, "//*[contains(@class,'mydashboard')]");
        assertEquals(driver.findElements(By.xpath("//*[@class='email-confirm-banner']")).size(), 0);
    }

    @cucumber.api.java.After
    public void postcondition() {
        BaseSteps.destroy(driver);
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}