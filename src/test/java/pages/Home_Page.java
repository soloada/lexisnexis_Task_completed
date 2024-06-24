package pages;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.GlobalVars;

import java.time.Duration;
import java.util.List;


public class Home_Page {
    final static Logger log = LoggerHelper.getLogger(Home_Page.class);
    WebDriver driver;
    FinancialServices_Page financialServicesPage;

    @FindBy(xpath = "/html/body/header/div/div/div/nav/div/div/div[2]/div/div/ul/li[1]/ul/li/div/div[2]/ul/li/a/div[1]")
    private List<WebElement> industriesTitles;

    @FindBy(xpath = "//h3[normalize-space()='Industries']")
    private WebElement industriesTxt;

    @FindBy(xpath = "(//span[contains(text(),'Select an Industry')])[2]")
    private WebElement selectIndustryText;

    @FindBy(xpath = "//div[normalize-space()='Insurance']")
    private WebElement insuranceLink;

    @FindBy(xpath = "//div[normalize-space()='Financial Services']")
    private WebElement financialServicesLink;

    @FindBy(xpath = "/html/body/header/div/div/div/nav/div/div/div[2]/div/div/ul/li[1]/ul/li/div/div[1]/div/div[1]/ul/li")
    private List<WebElement> financialServicesItemList;


    public List<WebElement> getFinancialServicesItems() {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.explicitWait)).until(driver -> financialServicesItemList.size() > GlobalVars.THREE);
        return financialServicesItemList;

    }

    public List<WebElement> getIndustriesTitles() {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.explicitWait)).until(driver -> industriesTitles.size() > GlobalVars.THREE);
        log.info("Verified title is displayed");
        return industriesTitles;

    }

    public List<WebElement> assertIndustriesTitlesPresent(String title) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.explicitWait)).until(driver -> industriesTitles.size() > GlobalVars.THREE);
        try {
            for (int i = 0; i < industriesTitles.size(); i++) {
                if (industriesTitles.get(i).isDisplayed()) {
                    log.info("Index " + i + " :: " + "Element is Present: " + industriesTitles.get(i).getText());
                }
            }
        } catch (Exception e) {
            log.error("Fail: Element is Not Present");
        }
        return industriesTitles;
    }

    public boolean assertIndustriesTitlesArePresent(String title) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.explicitWait)).until(driver -> industriesTitles.size() > GlobalVars.THREE);
        try {
            for (int i = 0; i < industriesTitles.size(); i++) {
                if (industriesTitles.get(i).isDisplayed()) {
                    log.info("Index " + i + " :: " + "Element is Present: " + industriesTitles.get(i).getText());
                }
            }
        } catch (Exception e) {
            log.error("Fail: Element is Not Present");
        }
        return industriesTitles.stream().anyMatch(s -> s.isDisplayed() && s.getText().contains(title));
    }

    public List<WebElement> assertFinancialServicesItemsPresent() {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.explicitWait)).until((d) -> financialServicesItemList.size() > 6);
        try {
            for (int i = 0; i < financialServicesItemList.size(); i++) {
                if (financialServicesItemList.get(i).isDisplayed()) {
                    log.info("Index " + i + " :: " + "Element is Present: " + financialServicesItemList.get(i).getText());
                }
            }
        } catch (Exception e) {
            log.error("Fail: Element is Not Present");
        }
        return financialServicesItemList;
    }

    public String getFinancialServicesLinkText() {
        return financialServicesLink.getText();

    }

    public boolean assertFinancialServicesLinkIsDisplayed() {
        return financialServicesLink.isDisplayed();

    }

    public FinancialServices_Page clickFinancialServicesLink() {
        financialServicesLink.click();
        log.info("Financial Services link is clicked");
        financialServicesPage = new FinancialServices_Page();
        return financialServicesPage;
    }

    public FinancialServices_Page clickAndElementWithMatchingTextLink(List<WebElement> industriesTitles, String subMenuLinkText) {
        industriesTitles.stream().filter(s -> s.isDisplayed() && s.getText().contains(subMenuLinkText)).findFirst().ifPresent(s -> s.click());
        log.info("Matching link text is clicked");
        return new FinancialServices_Page();
    }

    public void clickElementInIndustriesMatchingTextLink(List<WebElement> industriesTitles, String subMenuLinkText) {
        industriesTitles.stream().filter(s -> s.isDisplayed() && s.getText().contains(subMenuLinkText)).findFirst().ifPresent(s -> s.click());
        log.info("Matching link text is clicked");
    }


    public boolean assertIndustriesTextIsDisplayed() {
        return industriesTxt.isDisplayed();

    }

    public String getIndustriesText() {
        log.info("Verified the text is displayed");
        return industriesTxt.getText();

    }


    public List<WebElement> getFinancialServicesItemList() {
        return financialServicesItemList;

    }

    public List<String> getIndustriesTitlesList() {
        log.info("Verified Industries List are present");
        return getIndustriesTitles().stream().parallel().filter(s -> s.isDisplayed() && s.isEnabled()).map(WebElement::getText).toList();
    }

    public List<String> getFinancialServicesList() {
        log.info("Verified Financial Services List are present");
        return getFinancialServicesItemList().stream().parallel().filter(s -> s.isDisplayed() && s.isEnabled()).map(WebElement::getText).toList();
    }

    public long getFinancialServicesLinksCount() {
        log.info("Verified the text is displayed");
        return getFinancialServicesItemList().size();

    }

    public String getSelectIndustryText() {
        log.info("Verified the text is displayed");
        return selectIndustryText.getText();

    }

    public long getIndustriesLinksCount() {
        log.info("Verified Industries links are displayed");
        return getIndustriesTitlesList().size();

    }


    public void clickElementInIndustriesLink(String industryName) {
        industriesTitles.stream().filter(s -> s.isDisplayed() && s.getText().contains(industryName)).findFirst().ifPresent(s -> s.click());
        log.info(industryName + " link text is clicked");
    }

}
