package webDrivers;

import org.openqa.selenium.WebDriver;

public class Driver {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    public static WebDriver getWebDriver(Config config) {
        if (webDriver.get() == null)
            webDriver.set(DriverManager.getDriver(config));
        return webDriver.get();
    }

}
