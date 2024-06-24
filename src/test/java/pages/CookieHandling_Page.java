package pages;

import base.BasePage;
import com.google.common.util.concurrent.Uninterruptibles;
import helper.LoggerHelper;
import helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.GlobalVars;

import java.time.Duration;
import java.util.List;


public class CookieHandling_Page {
    final static Logger log = LoggerHelper.getLogger(CookieHandling_Page.class);
    WebDriver driver;
    BasePage basePage;

    @FindBy(id = "onetrust-banner-sdk")
    private WebElement cookieBanner;
    @FindBy(xpath = "//*[@id='onetrust-button-group']//button")
    private List<WebElement> cookieBannerButtons;

    @FindBy(id = "onetrust-policy-text")
    private WebElement cookiesPolicyText;
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptAllCookiesButton;
    @FindBy(xpath = "//button[@id='onetrust-pc-btn-handler']")
    private WebElement cookiesSettingsButton;

    @FindBy(css = "#ot-category-title")
    private WebElement manageConsentPreferencesHeader;
    @FindBy(css = "button[ot-accordion='true']")
    private List<WebElement> categoriesAccordionList;
    @FindBy(css = "#ot-pc-title")
    private WebElement cookiePreferenceCenterHeadingText;
    @FindBy(css = "#ot-header-id-1")
    private WebElement strictlyNecessaryCookies;
    @FindBy(css = ".ot-acc-hdr.ot-always-active-group")
    private WebElement alwaysActive;
    @FindBy(css = "h4.ot-cat-header")
    private List<WebElement> categoryHeaderList;
    @FindBy(css = "#ot-pc-desc")
    private WebElement descriptionText;
    @FindBy(xpath = "//div[@id='ot-pc-desc']//a[normalize-space()='Cookie Policy']")
    private WebElement cookiePolicyLink;
    @FindBy(xpath = "//a[normalize-space()='Your Privacy Choices']")
    private WebElement yourPrivacyChoicesLink;
    @FindBy(css = "a[href='https://risk.lexisnexis.com/corporate/privacy-policy']")
    private WebElement privacyPolicyLink;
    @FindBy(css = "#accept-recommended-btn-handler")
    private WebElement allowAllButton;
    @FindBy(css = "div.ot-plus-minus")
    private List<WebElement> accordionPlusMinusList;
    @FindBy(css = "span.ot-switch-nob")
    private List<WebElement> switchNobsList;
    @FindBy(css = "button.save-preference-btn-handler")
    private WebElement confirmMyChoicesButton;

    @FindBy(css = "img[title='Powered by OneTrust Opens in a new Tab']")
    private WebElement poweredByOneTrust;


    @FindBy(xpath = "/html/body/div[3]/div[4]/div/div[2]/div/div[2]/div/div/div/h3/a")
    private List<WebElement> perPageJobsDisplayedList;

    @FindBy(css = "button.onetrust-close-btn-handler.onetrust-close-btn-ui")
    private WebElement closeCookiePolicyButton;

    public void acceptCookie() {
        log.info("Accepting all cookies");
        acceptAllCookiesButton.click();

    }

    public void assertCookieIsNotPresent(String cookieName) {
        Assert.assertNull(driver.manage().getCookieNamed(cookieName));

    }

    public boolean assertCookieBannerIsDisplayed() {
        basePage = PageFactory.initElements(driver, BasePage.class);
        basePage.waitForWebElementToAppear(cookieBanner);
        log.info("Verified the text is displayed");
        return cookieBanner.isDisplayed();

    }

    public boolean assertAcceptAllCookiesButtonIsDisplayed() {
        log.info("Verified the text is displayed");
        return acceptAllCookiesButton.isDisplayed();

    }

    public boolean assertCookieSettingsButtonIsDisplayed() {
        log.info("Verified the text is displayed");
        return cookiesSettingsButton.isDisplayed();

    }

    public String getAcceptAllCookiesButtonText() {
        log.info("Verified the text is displayed");
        return acceptAllCookiesButton.getText();

    }

    public Boolean assertCookiesButtons() {
        log.info("Verified the text is displayed");
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until((s) -> cookieBannerButtons.size() == 2);

    }

    public String getCookieSettingsButtonText() {
        log.info("Verified the text is displayed");
        return cookiesSettingsButton.getText();

    }

    public WebElement getCookieBanner() {
        return cookieBanner;

    }

    public void clickAcceptAllCookiesButton() {
        acceptAllCookiesButton.click();
        log.info("Accept all cookies button clicked");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(GlobalVars.THREE));

    }

    public void clickAllowAllButton() {
        allowAllButton.click();
        log.info("Allow all cookies button clicked");
    }

    public void clickCookieSettingsButton() {
        cookiesSettingsButton.click();
        log.info("Cookie settings button clicked");

    }

    public List<WebElement> getCookieBannerButtons() {
        log.info("Size of cookies Banner button :" + cookieBannerButtons.size());
        return cookieBannerButtons;

    }

    public void clickAnyCookieBannerButton(List<WebElement> cookieBannerButtons, String cookieName) {
        WebElement element = cookieBannerButtons
                .stream()
                .parallel()
                .filter(s -> s.getText().contains(cookieName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element with text" + cookieBannerButtons + "not present"));
        element.click();
    }

    public String getManageConsentPreferencesHeaderText() {
        log.info("Verified the text is displayed");
        return manageConsentPreferencesHeader.getText();

    }

    public void clickPlusMinusAndSwitchNobInCategoriesAccordionList(String cookies) {
        for (int i = 0; i < accordionPlusMinusList.size(); i++) {
            if (accordionPlusMinusList.get(i).isDisplayed() && accordionPlusMinusList.get(i).getText().contains(cookies)) {
                accordionPlusMinusList.get(i).click();
                switchNobsList.get(i).click();
            }
        }
    }

    public String getCookiePreferenceCenterText() throws InterruptedException {
        new WaitHelper(driver).waitForElement(cookiePreferenceCenterHeadingText);
        log.info("Verified the text is displayed");
        return cookiePreferenceCenterHeadingText.getText().trim();

    }

    public boolean assertCookiePreferenceCenterTextIsDisplayed() {
        log.info("Verified the text is displayed");
        return cookiePreferenceCenterHeadingText.isDisplayed();

    }

    public String getDescriptionText() {
        log.info("Verified the text is displayed");
        return descriptionText.getText();

    }

    public boolean assertDescriptionTextIsDisplayed() {
        log.info("Verified the text is displayed");
        return descriptionText.isDisplayed();

    }

    public String getCookiePolicyLinkText() {
        log.info("Verified the text is displayed");
        return cookiePolicyLink.getText();

    }

    public boolean assertCookiePolicyLinkIsDisplayed() {
        log.info("Verified the text is displayed");
        return cookiePolicyLink.isDisplayed();

    }

    public void clickCookiePolicyLink() {
        yourPrivacyChoicesLink.click();

    }

    public String getYourPrivacyChoicesLinkText() {
        return yourPrivacyChoicesLink.getText();

    }

    public boolean assertYourPrivacyChoicesLinkIsDisplayed() {
        return yourPrivacyChoicesLink.isDisplayed();

    }

    public String getPrivacyPolicyLinkText() {
        return privacyPolicyLink.getText();

    }

    public boolean assertPrivacyPolicyLinkIsDisplayed() {
        return privacyPolicyLink.isDisplayed();

    }

    public boolean assertAllowAllButtonIsDisplayed() {
        return allowAllButton.isDisplayed();

    }

    public void clickAccordionPlusMinusList() {
        for (WebElement accordionPlusMinus : accordionPlusMinusList) {
            if (accordionPlusMinus.isDisplayed()) {
                accordionPlusMinus.click();
            }
        }

    }

    public List<WebElement> clickAccordionPlusMinusList(String categoriesAccordionName) {
        for (int i = 0; i < accordionPlusMinusList.size(); i++) {
            if (accordionPlusMinusList.get(i).isDisplayed() && categoriesAccordionList.get(i).getText().contains(categoriesAccordionName)) {
                accordionPlusMinusList.get(i).click();
            }
        }

        return null;
    }

    public List<WebElement> clickSwitchNobsList(String categoriesAccordionName) {
        for (int i = 0; i < switchNobsList.size(); i++) {
            if (switchNobsList.get(i).isDisplayed() && categoriesAccordionList.get(i).getText().contains(categoriesAccordionName)) {
                switchNobsList.get(i).click();

            }
        }

        return switchNobsList;
    }

    public void clickSwitchNobs(String categoriesAccordionName) {
        for (int i = 0; i < switchNobsList.size(); i++) {
            if (switchNobsList.get(i).isDisplayed() && categoriesAccordionList.get(i).getText().contains(categoriesAccordionName)) {
                switchNobsList.get(i).click();
                log.info("Clicked on Nobs button");

            }

        }
    }

    public boolean assertConfirmMyChoicesButtonIsEnabled() {
        return confirmMyChoicesButton.isEnabled();

    }

    public boolean assertConfirmMyChoicesButtonIsDisplayed() {
        return confirmMyChoicesButton.isDisplayed();

    }

    public void clickConfirmMyChoicesButton() {
        confirmMyChoicesButton.click();

    }



    public String getPoweredByOneTrustText() {
        return poweredByOneTrust.getText();

    }

    public boolean assertPoweredByOneTrustIsDisplayed() {
        return poweredByOneTrust.isDisplayed();

    }

    public WebElement getStrictlyNecessaryCookies() {
        return strictlyNecessaryCookies;

    }

    public String getStrictlyNecessaryCookiesText() {
        return strictlyNecessaryCookies.getText();
    }

    public boolean assertStrictlyNecessaryCookiesIsDisplayed() {
        return strictlyNecessaryCookies.isDisplayed();
    }


    public WebElement getAlwaysActive() {
        return alwaysActive;

    }

    public boolean assertAlwaysActiveIsDisplayed() {
        return alwaysActive.isDisplayed();

    }

    public String getAlwaysActiveText() {
        log.info("Verified the text is displayed");
        return alwaysActive.getText();

    }

    public boolean assertAlwaysActiveIsEnabled() {
        return alwaysActive.isEnabled();

    }

    public void clickAlwaysActive() {
        alwaysActive.click();

    }

    public List<WebElement> getCategoryHeaderList() {
        return categoryHeaderList;

    }

    public boolean assertCategoryHeaderListIsDisplayed(String categoryName) {
        return categoriesAccordionList.stream().parallel().filter(WebElement::isDisplayed).anyMatch(s -> s.getText().contains(categoryName));
    }

    public boolean assertItemDisplayedFromCategoryHeaderList(String categoryName) {
        return categoriesAccordionList.stream().parallel().anyMatch(s -> s.getText().contains(categoryName));
    }


    public boolean assertPlusOrMinusButtonIsDisplayed(String categoryName) {
        for (int i = 0; i < accordionPlusMinusList.size(); i++) {
            if (categoriesAccordionList.get(i).getText().contains(categoryName)) {
                categoriesAccordionList.get(i).getText();
                log.info("Verified the text is displayed");
            }
        }

        return true;
    }

    public boolean assertSwitchNobIsDisplayed(String categoryName) {
        for (int i = 0; i < switchNobsList.size(); i++) {
            if (categoriesAccordionList.get(i).getText().contains(categoryName)) {
                categoriesAccordionList.get(i).getText();
                log.info("Verified the text is displayed");
            }
        }

        return true;
    }

    public String getConfirmMyChoiceButtonText() throws InterruptedException {
        basePage = PageFactory.initElements(driver,BasePage.class);
        basePage.waitForElementToAppear(confirmMyChoicesButton);
        log.info("Verified the text is displayed");
        return confirmMyChoicesButton.getText();
    }


    public String getAllowAllButtonText() {
        log.info("Verified the text is displayed");
       return allowAllButton.getText();
    }

    public boolean assertCloseCookiePolicyButton() {
        return closeCookiePolicyButton.isDisplayed();

    }

    public void clickCloseCookiePolicyButton() {
        closeCookiePolicyButton.click();
        log.info("Close cookie policy button is clicked");
    }
}
