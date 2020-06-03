package web_driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    public static WebDriver getDriver(Config config) {
        switch (config) {
            case CHROME:
                return getChromeDriver();
            case FF:
                return getFirefoxDriver();
            default:
                throw null;
        }
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\valeria_zhilenkova_htp_at_project\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }
}
