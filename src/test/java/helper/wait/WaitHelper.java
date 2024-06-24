package helper.wait;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static helper.browserConfiguration.config.ObjectReader.reader;

public class WaitHelper {
    final static Logger log = LoggerHelper.getLogger(WaitHelper.class);
    WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        log.info("WaitHelper object created..");
    }

    private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }

    public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
                                                     int pollingEveryInMiliSec) {
        log.info("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.visibilityOf(element));
        log.info("Element is visible now");
    }

    public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
        log.info("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));
        log.info("Element is clickable now");
    }

    public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
        log.info("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
        log.info("Element is invisibile now");
        return status;
    }

    public void waitForFrameToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
        log.info("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        log.info("Frame is available and switched");
    }

    private Wait<WebDriver> getfluentWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);
        return fWait;
    }

    public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
        Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
        fwait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void pageLoadTime() {
        log.info("Waiting for page to load for ...");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        log.info("BasePage is loaded");
    }

    public void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(element));
        log.info("Waited and element is now visible :: " + element.getText());
    }

    public void setImplicitWait(int implicitWait, TimeUnit seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void setPageLoadTimeout(int pageLoadTimeOut) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeOut));
    }

    public void waitForElementVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info(locator);
        setImplicitWait(1, TimeUnit.SECONDS);
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
    }

    public void hardWait(int timeOutInMiliSec) throws InterruptedException {
        log.info(timeOutInMiliSec);
        Thread.sleep(timeOutInMiliSec);
    }

    public WebElement handleStaleElement(By locator, int retryCount, int delayInSeconds) throws InterruptedException {
        log.info(locator);
        WebElement element = null;

        while (retryCount >= 0) {
            try {
                element = driver.findElement(locator);
                return element;
            } catch (StaleElementReferenceException e) {
                hardWait(delayInSeconds);
                retryCount--;
            }
        }
        throw new StaleElementReferenceException("Element cannot be recovered");
    }


}
