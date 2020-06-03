package web_pages.trashMail;

import org.openqa.selenium.WebDriver;
import steps.BaseSteps;
import steps.MailSteps;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    static String TRASHMAIL_PATH = "src/test/resources/trashMail.properties";

    public static void generateMail(WebDriver driver) {
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-fwd-nb']");
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-fwd-nb']/option[contains(text(),'1')]");
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-life-span']");
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-life-span']/option[contains(text(),'1 day')]");
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-submit']");
    }

    public static void trashmailRegistration(WebDriver driver) throws InterruptedException, IOException {
        Properties prop = BaseSteps.getProperties(TRASHMAIL_PATH);
        BaseSteps.findElementClick(driver, "//*[contains(@href,'mob-register')]");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id='tab-mob-register']/form/div[1]/input", prop.getProperty("LOGIN"));
        BaseSteps.findElementSendKeys(driver, "//*[@id='tab-mob-register']/form/div[2]/input", prop.getProperty("PASSWORD"));
        BaseSteps.findElementSendKeys(driver, "//*[@id='tab-mob-register']/form/div[3]/input", prop.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[@id='tab-mob-register']/form/div[6]/button");
        TimeUnit.SECONDS.sleep(7);
        MailSteps.confirmLinkOnYandexMail("TrashMail", driver);
        BaseSteps.findElementClick(driver, "//*[contains(@href,'trashmail')]");
        TimeUnit.SECONDS.sleep(7);
    }
}
