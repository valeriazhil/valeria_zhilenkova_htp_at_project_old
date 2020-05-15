package runners;


import org.junit.After;
import org.openqa.selenium.WebDriver;
import settings.Config;
import webDrivers.Driver;
import otherStuff.trashMail.CreateTempEmailOnTrashMail;
import webDrivers.GetDriver;
import java.io.IOException;

public class CreateTempEmailRunner {


    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver;
        driver = GetDriver.getWebDriver(Config.CHROME);
        CreateTempEmailOnTrashMail.trashmailGetNewMail(driver);
    }

 @After
    public void stopBrowser() {
        Driver.closeDriver();
    }
}
