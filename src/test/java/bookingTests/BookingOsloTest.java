package bookingTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import otherStuff.BookingUtilities;
import webDriver.Config;
import settings.ScreenMode;
import webDriver.Driver;
import webPages.BaseActionsOfWebPages;

import java.util.concurrent.TimeUnit;

public class BookingOsloTest {

    private static final String BOOKING_URL = "https://www.booking.com/";
    int daysAmount = 1;
    int daysShift = 1;
    int adultNeed = 2;
    int roomNeed = 1;
    int childNeed = 1;
    WebElement element;
    WebDriver driver;


    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        driver.get(BOOKING_URL);
    }
//    @Before
//    public void preCondition() {
//        driver = Driver.getWebDriver(Config.CHROME);
//        BaseActionsOfWebPages.followTheLinkSetWindowMode(driver, BOOKING_URL, ScreenMode.MAXIMIZE);
//    }

    @Test
    public void booking3Test() throws InterruptedException {
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"ss\"]", "Осло");  //set City: Oslo
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"xp__input-group xp__date-time\")]");
        BaseActionsOfWebPages.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", BookingUtilities.generateDateXpath(daysShift)));
        BaseActionsOfWebPages.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", BookingUtilities.generateDateXpath(daysAmount + daysShift)));  //set days
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"xp__guests__toggle\"]");
        int adultAmount = Integer.parseInt(BaseActionsOfWebPages.findElementGetAttribute(driver, "//*[contains(@class,\"field-adult\")]//input", "value"));
        BaseActionsOfWebPages.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]", adultAmount, adultNeed);
        int roomAmount = Integer.parseInt(BaseActionsOfWebPages.findElementGetAttribute(driver, "//*[contains(@class,\"field-rooms\")]//input", "value"));
        BaseActionsOfWebPages.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]", roomAmount, roomNeed); //set adult and room amount
        int childAmount = Integer.parseInt(BaseActionsOfWebPages.findElementGetAttribute(driver, "//*[@id=\"group_children\"]", "value"));
        BaseActionsOfWebPages.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"group_children_desc\")][contains(@class, \"add\")]", childAmount, childNeed);
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@type, \"submit\")]");
        TimeUnit.SECONDS.sleep(4);
        BaseActionsOfWebPages.findElementClick(driver, "//*[@data-id=\"class-3\"]");
        BaseActionsOfWebPages.findElementClick(driver, "//*[@data-id=\"class-4\"]");
        TimeUnit.SECONDS.sleep(4);
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]"));
        TimeUnit.SECONDS.sleep(2);
        Actions actions = new Actions(driver);
        element = BookingUtilities.scriptsExecuter(element, driver, actions);
        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        Assert.assertEquals("color: red;", textColor);
    }

    @After
    public void postCondition() {
        BaseActionsOfWebPages.destroyDriver(driver);
    }
}
