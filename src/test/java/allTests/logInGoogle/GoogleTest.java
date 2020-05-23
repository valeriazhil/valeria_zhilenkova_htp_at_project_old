package allTests.logInGoogle;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webDriver.Config;
import webDriver.Driver;
import webPages.BaseActionsOfWebPages;

public class GoogleTest {

    private static final  String LOGIN_URL = "https://accounts.google.com/signin";
    WebElement element;
    WebDriver driver;


    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.OPERA);
        driver.get(LOGIN_URL);
    }

    @Test
    public void googleTest() throws InterruptedException {

        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"identifierId\"]", "v.zhilenkova@zorka.mobi");
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"identifierNext\"]");

    }
}
