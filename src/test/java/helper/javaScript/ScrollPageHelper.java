package helper.javaScript;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollPageHelper {

    final static Logger log = LoggerHelper.getLogger(ScrollPageHelper.class);

    static WebDriver driver;

    public ScrollPageHelper(WebDriver driver) {
        ScrollPageHelper.driver = driver;
        log.info("ScrollPageHelper has been initialised");
    }

    public static void scrollToView(WebElement elementName) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementName);
        log.info("Scroll to the element :: " + elementName.getText());
        Thread.sleep(500);
    }


    public static void scrollToViewContinue(WebDriver driver) {
        try {
            scrollToView(driver.findElement(By.id("continue")));
        } catch (Exception e) {
            log.info(e);
        }
    }

    public static void scrollToViewElement(WebDriver driver, String elementID) {
        try {
            scrollToView(driver.findElement(By.id(elementID)));
        } catch (Exception e) {
            log.info(e);
        }
    }

    public static void scrollToBottomOfThePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }

}
