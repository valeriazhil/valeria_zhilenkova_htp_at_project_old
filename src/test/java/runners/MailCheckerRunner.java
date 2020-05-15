package runners;

import org.openqa.selenium.WebDriver;
import webDrivers.Config;
import webDrivers.Driver;
import otherStuff.mailChecher.MailChecker;
import java.io.IOException;

public class MailCheckerRunner {

    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver;
        driver = Driver.getWebDriver(Config.OPERA);
        MailChecker.confirmLinkOnYahooMail("TrashMail Robot", driver);
    }
}