package otherStuff.mailChecher;

import webPages.BaseActionsOfWebPages;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailChecker {

    public static String YAHOO_PATH = "src\\test\\java\\properties\\yahooMail.properties";


    public static void confirmLinkOnYahooMail(String sender, WebDriver driver) throws InterruptedException, IOException {
        driver.get("https://www.yahoo.com/");
        Properties prop = BaseActionsOfWebPages.getProperties(YAHOO_PATH);

        TimeUnit.SECONDS.sleep(2);
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"header-signin-link\"]");

        TimeUnit.SECONDS.sleep(5);
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"login-username\"]", prop.getProperty("EMAIL"));
        TimeUnit.SECONDS.sleep(2);
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"login-signin\"]");
        TimeUnit.SECONDS.sleep(10);

        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"login-passwd\"]", prop.getProperty("PASSWORD"));
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"login-signin\"]");
        TimeUnit.SECONDS.sleep(10);
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"header-nav-bar\"]/li[1]/a");

        String realSender=BaseActionsOfWebPages.findElementGetText(driver, String.format("//*[contains(text(), \"%s\")]", sender));
        Assert.assertTrue(realSender.contains(sender));
        //BaseActionsOfWebPages.findElementClick(driver, String.format("//*[contains(text(), \"%s\")]", sender));
        //TimeUnit.SECONDS.sleep(2);

    }





}