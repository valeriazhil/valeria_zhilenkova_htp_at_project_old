package allTests.bookingTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import otherStuff.BookingUtilities;
import webDriver.Config;
import settings.ScreenMode;
import webDriver.Driver;
import webPages.BaseActionsOfWebPages;




import java.util.concurrent.TimeUnit;

public class BookingParisTest {

    private static final String BOOKING_URL = "https://www.booking.com/";
    int daysAmount = 7;
    int daysShift = 3;
    int adultNeed = 4;
    int roomNeed = 2;
    WebElement element;
    WebDriver driver;

        @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BaseActionsOfWebPages.followTheLinkSetWindowMode(driver, BOOKING_URL, ScreenMode.MAXIMIZE);
    }

    @Test
    public void booking1Test() throws InterruptedException {

        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"ss\"]", "Париж");  //set City: Paris
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"xp__input-group xp__date-time\")]");
        BaseActionsOfWebPages.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", BookingUtilities.generateDateXpath(daysShift)));
        BaseActionsOfWebPages.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", BookingUtilities.generateDateXpath(daysAmount + daysShift)));  //set days
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"xp__guests__toggle\"]");
        int adultAmount = Integer.parseInt(BaseActionsOfWebPages.findElementGetAttribute(driver, "//*[contains(@class,\"field-adult\")]//input", "value"));
        BaseActionsOfWebPages.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]", adultAmount, adultNeed);
        int roomAmount = Integer.parseInt(BaseActionsOfWebPages.findElementGetAttribute(driver, "//*[contains(@class,\"field-rooms\")]//input", "value"));
        BaseActionsOfWebPages.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]", roomAmount, roomNeed); //set adult and room amount
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@type, \"submit\")]");
        TimeUnit.SECONDS.sleep(4);
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"sort_price\")]/a");
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"filter_price\"]//a[5]");
        TimeUnit.SECONDS.sleep(4);
        String maxPrice = BaseActionsOfWebPages.findElementGetText(driver, "//*[@id=\"filter_price\"]//a[5]").replaceAll("[^0-9]+", "");
        String firstPrice = BaseActionsOfWebPages.findElementGetText(driver, "//*[contains(@class, \"bui-price-display\")]/div[2]/div").replaceAll("[^0-9]+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;
        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));

    }

//    @After
//    public void postCondition() {
//        BaseActionsOfWebPages.destroyDriver(driver);
//    }
}
