package allTests.logInGoogle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class prostoGoogleTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        WebDriver driver= new ChromeDriver();


        driver.get("https://accounts.google.com/signin");


        WebElement user = driver.findElement(By.cssSelector("#identifierId"));
        user.sendKeys("valeria.zhil");


        WebElement next= driver.findElement(By.cssSelector("#identifierNext"));
        next.click();
    }
}
