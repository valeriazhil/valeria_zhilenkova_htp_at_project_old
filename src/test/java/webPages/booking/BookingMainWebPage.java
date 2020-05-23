package webPages.booking;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class BookingMainWebPage {

    @FindBy(xpath = "//*[@id='ss']")
    private WebElement searchField;

    private String searchFieldXpath = "//*[@id='ss']";
    private String checkInListXpath = "//*[@id='frm']//descendant::div[@class='xp__dates xp__group']";
    private String adultsListXpath = "//*[@id='xp__guests__toggle']";

}
