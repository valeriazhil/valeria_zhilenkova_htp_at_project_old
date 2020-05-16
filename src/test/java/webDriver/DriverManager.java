package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;


public class DriverManager {



    public static WebDriver getDriver(Config config) {

        switch (config) {
            case CHROME:
                return getChromeDriver();
            case FF:
                return getFFDriver();
            case OPERA:
                return getOperaDriver();
            default:
                throw null;
        }
    }

    private static WebDriver getOperaDriver() {
        System.setProperty("webdriver.opera.driver", "c://operadriver.exe");
        System.setProperty("webdriver.opera.silentOutput", "true");
        return new OperaDriver();
    }

    private static WebDriver getFFDriver() {
        System.setProperty("webdriver.gecko.driver", "c://geckodriver.exe");
        System.setProperty("webdriver.gecko.silentOutput", "true");
        return new FirefoxDriver();
    }

    private static WebDriver getChromeDriver() {

        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        return new ChromeDriver();

    }

}