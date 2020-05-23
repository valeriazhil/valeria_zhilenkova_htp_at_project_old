package runners;


import org.openqa.selenium.WebDriver;
import otherStuff.CreateTempEmailOnTrashMail;
import webDriver.Config;
import webDriver.Driver;
import java.io.IOException;

public class CreateTempEmailRunner {


    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver;
        driver = Driver.getWebDriver(Config.CHROME);
        CreateTempEmailOnTrashMail.trashmailGetNewMail(driver);
    }

}
