package runners;

import org.openqa.selenium.WebDriver;
import webDriver.Config;
import webDriver.Driver;
import otherStuff.MailChecker;
import java.io.IOException;

public class MailCheckerRunner {

    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver;
        driver = Driver.getWebDriver(Config.OPERA);
        MailChecker.confirmLinkOnMyRealMail("TrashMail Robot", driver);
    }
}