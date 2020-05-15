package runners;


import org.junit.After;
import org.openqa.selenium.WebDriver;
import otherStuff.trashMail.CreateTempEmailOnTrashMail;
import webDrivers.Config;
import webDrivers.Driver;
import java.io.IOException;

public class CreateTempEmailRunner {


    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver;
        driver = Driver.getWebDriver(Config.CHROME);
        CreateTempEmailOnTrashMail.trashmailGetNewMail(driver);
    }

}
