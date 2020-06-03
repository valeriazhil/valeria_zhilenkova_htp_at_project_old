package tests.booking.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import web_driver.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import web_driver.Driver;
import web_pages.booking.ActionsMainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingParisTest {
    int daysAmount = 7;
    int daysShift = 3;
    int adultsNeed = 4;
    int childrenNeed = 0;
    int roomsNeed = 2;
    static WebDriver driver;
    String maxPrice;
    int firstOneDayPrice;

    @BeforeClass
    public static void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        driver = Driver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        ActionsMainPage.setCityPersonRoomDates(driver, "Paris", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I filter hotels at the maximum price")
    public void iFilterHotelsAtTheMaximumPrice() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[contains(@class,'sort_price')]/a");
        BaseSteps.findElementClick(driver, "//*[@id='filter_price']//a[5]");
        TimeUnit.SECONDS.sleep(2);
    }

    @And("I'm looking hotel with minimum price")
    public void iMLookingHotelWithMinimumPrice() {
        maxPrice = BaseSteps.findElementGetText(driver, "//*[@id='filter_price']//a[5]").replaceAll("\\D+", "");
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class,'bui-price-display')]/div[2]/div").replaceAll("\\D+", "");
        firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;
    }

    @And("I compare hotel's price and price in filters")
    public void iCompareHotelSPriceAndPriceInFilters() {
        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
        BaseSteps.destroy(driver);
    }

    @Test
    public void tripParisTest() throws InterruptedException {
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
        ActionsMainPage.setCityPersonRoomDates(driver, "Paris", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
        BaseSteps.findElementClick(driver, "//*[contains(@class,'sort_price')]/a");
        BaseSteps.findElementClick(driver, "//*[@id='filter_price']//a[5]");
        TimeUnit.SECONDS.sleep(2);
        String maxPrice = BaseSteps.findElementGetText(driver, "//*[@id='filter_price']//a[5]").replaceAll("\\D+", "");
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class,'bui-price-display')]/div[2]/div").replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;
        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
    }

    @AfterClass
    public static void postCondition() {
        BaseSteps.destroy(driver);
    }
}