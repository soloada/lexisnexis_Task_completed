package base;

import helper.LoggerHelper;
import helper.WebElementOrderChecker;
import helper.WebElementOrderCheckerImpl;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.GlobalVars;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static base.BaseTest.driver;

public class BasePage {

    final static Logger log = LoggerHelper.getLogger(BasePage.class);

    public BasePage() {

    }
    public void getRectHeightAndWidthOfAnElement(WebElement element) {
        Point p = element.getLocation();
        log.info(p.getX());
        log.info(p.getY());
        Rectangle r = element.getRect();
        log.info(r.getX());
        log.info(r.getY());
    }

    public boolean checkForTitle(WebDriver driver, String title) {
        log.info(title);
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException(title);
        return driver.getTitle().trim().contains(title);
    }

    public String getThisPageTitle() {
        log.info("Title is: " + driver.getTitle());
        return driver.getTitle();
    }

    public String getThisPageUrl() {
        log.info("BasePage Url is: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public void assertOnOrderOfWebElementsByListOrder(List<String> elementIds) {
        String idOfParentElement = elementIds.get(0);
        WebElement parentWebElement = driver.findElement(By.id(idOfParentElement));
        List<WebElement> childWebElements = parentWebElement.findElements(By.xpath(".//*"));
        WebElementOrderChecker webElementOrderChecker = new WebElementOrderCheckerImpl(childWebElements);
        List<String> childIds = getIdListOfExpectedChildElements(elementIds);
    }
    public List<String> getArrayListOfStringWithCommaSeparated(String fieldNames) {
        List<String> errorIds = new ArrayList<>();
        if (fieldNames.contains(",")) {
            errorIds = Arrays.asList(fieldNames.split(","));
        } else {
            errorIds.add(fieldNames);
        }
        return errorIds;
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    private List<String> getIdListOfExpectedChildElements(List<String> elementIds) {
        List<String> ids = new ArrayList<>();
        for (String elementId : elementIds) {
            ids.add(elementId);
        }
        ids.remove(0);
        return ids;
    }
    public void navigateTo(String url) {
        driver.navigate().to(url);

    }

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.getExplicitWait()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }

    public void waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.getExplicitWait()));
        wait.until((d) -> element.isDisplayed());
        log.info("Waiting Element to appear....");
    }

    public void waitForElementToDisappear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.getExplicitWait()));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
        log.info("Waiting Element to disappear....");
    }

    public void waitForElementsToAppear(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.getExplicitWait()));
        wait.until((d) -> elements.size() > 1);
        log.info("Waiting Elements to appear....");

    }

    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.explicitWait));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }

    public void waitForElementToDisappear(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.getExplicitWait()));
        wait.until(ExpectedConditions.invisibilityOf(ele));
        log.info("Waiting Element to disappear....");

    }
    public void waitAndClick(WebElement findBy) {
        log.info("Waiting Element to be clicked....");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.getExplicitWait()));
        log.info("Successfully clicked ");
        wait.until(ExpectedConditions.elementToBeClickable(findBy)).click();

    }

    public void SwitchWindowToChild() {
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        driver.switchTo().window(childWindow);
    }

    public void clickAnyElementMatchingText(List<WebElement> elements, Predicate<WebElement> predicate) {
        WebElement element = elements
                .stream()
                .parallel()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element not present"));
        element.click();
    }

    public void clickAnyMatchingLinkText(List<WebElement> element, String linkText) {
        WebElement ele = element.stream()
                .parallel()
                .filter(s -> s.getText().equalsIgnoreCase(linkText))
                .findFirst()
                .orElse(null);
        if (ele != null) {
            ele.click();
            log.info("Clicked on the link");
        }
    }

    public void clickAnyMatchingElementByText(List<WebElement> elements, String text) {
        WebElement element = elements
                .stream()
                .parallel()
                .filter(s -> s.isDisplayed() && s.getText().contains(text))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element with text " + text + " not present"));
        element.click();
    }


    public List<String> getAnyMatchingElement_Text_Attribute_TagName(List<WebElement> elements, Function<WebElement, String> Function) {
        return elements
                .stream()
                .parallel()
                .filter(WebElement::isDisplayed)
                .map(Function)
                .toList();
    }

    public boolean assertAnyLinkFromListOfElements(List<WebElement> listOfElements, String linkText) {
        return listOfElements
                .stream()
                .parallel()
                .anyMatch(s -> s.getText().equalsIgnoreCase(linkText));
    }


}
