package otherStuff;

import webPages.BaseActionsOfWebPages;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailChecker {

    public static String MYREAL_PATH = "src\\test\\java\\properties\\myRealMail.properties";


    public static void confirmLinkOnMyRealMail(String sender, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://mail.yandex.ru/");
        Properties prop = BaseActionsOfWebPages.getProperties(MYREAL_PATH);
        TimeUnit.SECONDS.sleep(2);
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"HeadBanner-Button-Enter\")]");
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id= \"passp-field-login\"]", prop.getProperty("EMAIL"));
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(2);
        BaseActionsOfWebPages.findElementSendKeys(driver, "//*[@id= \"passp-field-passwd\"]", prop.getProperty("PASSWORD"));
        BaseActionsOfWebPages.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(5);
        BaseActionsOfWebPages.findElementClick(driver, String.format("//*[contains(text(), \"%s\")]", sender));
        TimeUnit.SECONDS.sleep(2);

    }

    public static void putEmailInProperty(String newMail, String propertyPath) throws IOException {
        Properties prop = BaseActionsOfWebPages.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
    }
