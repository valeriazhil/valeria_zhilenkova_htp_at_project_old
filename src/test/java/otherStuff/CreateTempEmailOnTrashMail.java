package otherStuff;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import otherStuff.MailChecker;
import webPages.BaseActionsOfWebPages;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CreateTempEmailOnTrashMail {
    private static boolean withoutAccount = true;
    private static String TRASHMAIL_PATH = "src\\test\\java\\properties\\trashMail.properties";
    private static String BOOKING_PATH = "src\\test\\java\\properties\\booking.properties";

    public static void trashmailGetNewMail(WebDriver driver) throws InterruptedException, IOException {

        Properties prop = BaseActionsOfWebPages.getProperties(TRASHMAIL_PATH);
        driver.get("https://trashmail.com/");
        if (withoutAccount)
            BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"fe-mob-forward\"]", prop.getProperty("EMAIL"));
        getNewMail(driver);
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]");
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]/option[contains(text(), \"1\")]");
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]");
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]/option[contains(text(), \"1 day\")]");
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"fe-mob-submit\"]");
        TimeUnit.SECONDS.sleep(2);
        if (driver.findElements(By.xpath("//*[contains(text(), \"address is not registered\")]")).size() > 0) {
            withoutAccount = false;
            trashmailRegistration(driver);
            trashmailGetNewMail(driver);
        }
        TimeUnit.SECONDS.sleep(3);
        String trashMail = BaseActionsOfWebPages.findElementGetText(driver, "//*[contains(text(), \"@trashmail.com\")]");
    }

    private static void getNewMail(WebDriver driver) throws IOException {
        String newMail = BaseActionsOfWebPages.findElementGetAttribute(driver, "//*[@id=\"fe-mob-name\"]", "value");
        newMail = newMail.concat("@trashmail.com");
        putEmailInProperty(newMail, BOOKING_PATH);
    }

    public static void putEmailInProperty(String newMail, String propertyPath) throws IOException {
        Properties prop = BaseActionsOfWebPages.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }

    private static void trashmailRegistration(WebDriver driver) throws InterruptedException, IOException {

        Properties prop = BaseActionsOfWebPages.getProperties(TRASHMAIL_PATH);
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@href, \"mob-register\")]");
        TimeUnit.SECONDS.sleep(3);
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[1]/input", prop.getProperty("LOGIN"));
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[2]/input", prop.getProperty("PASSWORD"));
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[3]/input", prop.getProperty("PASSWORD"));
        BaseActionsOfWebPages.findElementClick(driver, "//*[@id=\"tab-mob-register\"]/form/div[6]/button");
        TimeUnit.SECONDS.sleep(10);
        MailChecker.confirmLinkOnYahooMail("TrashMail Robot", driver);
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@href, \"trashmail\")]");
        TimeUnit.SECONDS.sleep(10);
    }
}
