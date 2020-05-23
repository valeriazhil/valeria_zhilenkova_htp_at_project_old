package allTests.bookingTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import otherStuff.BookingUtilities;
import webDriver.Config;
import webDriver.Driver;
import webPages.BaseActionsOfWebPages;
import java.util.concurrent.TimeUnit;

public class BookingMoskowTest {

    private static final String BOOKING_URL = "https://www.booking.com/";
    String date = null;
    int daysAmount = 5;
    int daysShift = 10;
    WebElement element;
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        driver.get(BOOKING_URL);
    }

    @Test
    public void booking2Test() throws InterruptedException {
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"ss\"]", "Москва");
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"xp__input-group xp__date-time\")]");
        BaseActionsOfWebPages.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", BookingUtilities.generateDateXpath(daysShift)));
        BaseActionsOfWebPages.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", BookingUtilities.generateDateXpath(daysAmount + daysShift)));  //set days
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@type, \"submit\")]");
        TimeUnit.SECONDS.sleep(3);
        Actions actions = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@id=\"group_adults\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = driver.findElement(By.xpath("//*[@id=\"no_rooms\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"sort_price\")]/a");
        element = BaseActionsOfWebPages.findElementClickReturn(driver, "//*[@id=\"filter_price\"]//a[1]");
        String maxPrice = element.getText();
        maxPrice = maxPrice.replaceAll("([^1-9][^0-9]+)", "");
        TimeUnit.SECONDS.sleep(2);
        String firstPrice = BaseActionsOfWebPages.findElementGetText(driver, "//*[contains(@class, \"bui-price-display\")]/div[2]/div");

        firstPrice = firstPrice.replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount - daysShift);
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));
    }

    @After
    public void postCondition() {
        BaseActionsOfWebPages.destroyDriver(driver);
    }
}
