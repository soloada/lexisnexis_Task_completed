package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionClass {
    WebDriver driver;

    public void selectFirstWord(WebElement elementName) {
        Actions action = new Actions(driver);
        action.moveToElement(elementName, 0, 0).click().doubleClick().build().perform(); //selects first word

    }


}
