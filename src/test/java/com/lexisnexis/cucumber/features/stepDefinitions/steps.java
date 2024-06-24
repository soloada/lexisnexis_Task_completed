package com.lexisnexis.cucumber.features.stepDefinitions;

import base.BasePage;
import base.BaseTest;
import helper.GenericMethods;
import helper.LoggerHelper;
import helper.javaScript.JavaScriptHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class steps extends BaseTest {
    final static Logger log = LoggerHelper.getLogger(steps.class);
    TopMenu_Page topMenuPage;
    AboutUs_Page aboutUsPage;
    Home_Page homePage;
    Careers_Page careersPage;
    Jobs_Page jobsPage;
    CookieHandling_Page cookieBannerPage;
    BasePage basePage;
    GenericMethods gm;

    @Given("I navigate to home page {string}")
    public void i_navigate_to_home_page(String url) throws IOException {
        topMenuPage = launchApplication(url);
        log.info("I am in home page");
    }



    @And("I am on the page {string} and can see title as {string}")
    public void i_am_on_the_page_and_can_see_title_as(String pageUrl, String pageTitle) {
        basePage = PageFactory.initElements(driver, BasePage.class);
        Assert.assertTrue(basePage.getThisPageUrl().contains(pageUrl));
        Assert.assertTrue(basePage.getThisPageTitle().contains(pageTitle));

    }

    @When("click on {string} link")
    public void click_careers_link(String linkText) {
        aboutUsPage = PageFactory.initElements(driver, AboutUs_Page.class);
        BasePage basePage = PageFactory.initElements(driver, BasePage.class);
        Assert.assertTrue(basePage.assertAnyLinkFromListOfElements(aboutUsPage.getAllAboutLinks(), linkText));

        careersPage = aboutUsPage.clickCareersLink();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindowId = it.next();
        String childWindow = it.next();
        driver.switchTo().window(childWindow);
        log.info("Parent Window Id: " + parentWindowId);
    }

    @Then("I tap on {string}")
    public void i_tap_on(String SearchJobsText) {
        careersPage = PageFactory.initElements(driver, Careers_Page.class);
        Assert.assertTrue(careersPage.getSearchJobsButtonText().contains(SearchJobsText));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", careersPage.getSearchJobsButton());
    }

    @When("I should be taken to Jobs page as title includes {string}")
    public void i_should_be_taken_to_jobs_page_as_title(String pageTitle) {
        basePage = PageFactory.initElements(driver,BasePage.class);
        Assert.assertTrue(basePage.getThisPageTitle().contains(pageTitle));

    }

    @And("I am in the job page and can see title as {string}")
    public void i_am_in_the_job_page_and_can_see_title_as(String pageTitle) {
        basePage = PageFactory.initElements(driver,BasePage.class);
        Assert.assertTrue(basePage.getThisPageTitle().contains(pageTitle));
    }



    @And("It navigate to new window and I can see in Careers page as follows:")
    public void it_navigate_to_new_window_and_i_can_see_in_careers_page_as_follows(DataTable dataTable) {
        careersPage = PageFactory.initElements(driver, Careers_Page.class);
        Assert.assertTrue(careersPage.getExploreMoreText().contains(dataTable.cell(0, 0)));
        Assert.assertTrue(careersPage.getCareersHeadingText().contains(dataTable.cell(1, 0)));
        Assert.assertTrue(careersPage.getSearchJobsButtonText().contains(dataTable.cell(2, 0)));
    }

    @When("I search for job with a job title {string}")
    public void i_search_for_job_with_a_job_title(String jobTitle) {
        jobsPage = PageFactory.initElements(driver, Jobs_Page.class);
        jobsPage.inputSearchBox(jobTitle);
        jobsPage.clickSearchButton();
    }



    @Then("I can see the search result count as {string}")
    public void i_can_see_the_search_result_count_as(String searchResult) {
        jobsPage = PageFactory.initElements(driver, Jobs_Page.class);
        Assert.assertTrue(jobsPage.getNoSearchResultsHeaderText().contains(searchResult));
    }

    @Then("I can see the job search result count displayed in the page is greater than {int}")
    public void i_can_see_the_job_search_result_count_displayed_in_the_page_is_greater_than(Integer jobCount) throws Exception {
        Assert.assertTrue(jobsPage.getDisplayedJobsTitlesCountGreaterThanZero() > jobCount);
    }

    @When("I should see Text for many items {string}")
    public void i_should_see_text_for_many_items(String jobTitle) {
        Assert.assertTrue(jobsPage.getTextForManySearchResultsItems().contains(jobTitle));

    }

    @When("I can see search results related to the {string}")
    public void i_can_see_search_results_having(String jobTitle) {
        Assert.assertTrue(jobsPage.assertSearchResultsForJobTitle(jobTitle));
    }

    @And("I can see search results not related to the {string}")
    public void i_can_see_search_results_not_relating_to_the(String jobTitle) {
        Assert.assertFalse(jobsPage.assertJobSearchResultsByJobTitle(jobTitle));
    }

    @And ("I  see cookie banner is displayed")
    public void i_see_cookie_banner_is_displayed() {
        cookieBannerPage = PageFactory.initElements(driver, CookieHandling_Page.class);
        Assert.assertTrue(cookieBannerPage.assertCookieBannerIsDisplayed(), "Cookie Banner is not displayed");
    }

    @Given("I tap on Accept all Cookies")
    public void i_tap_on_accept_all_cookies() {
        cookieBannerPage.clickAcceptAllCookiesButton();
    }

    @Given("I should not see the cookie banner")
    public void i_should_not_see_the_cookie_banner() {
        Assert.assertEquals(0, driver.findElements(By.id("cookie_banner")).size(), "Web element was present");
    }

    @And("logo is displayed")
    public void logo_is_displayed() {
        topMenuPage= PageFactory.initElements(driver, TopMenu_Page.class);
        Assert.assertTrue(topMenuPage.assertLogoIsDisplayed());
    }

    @When("I tap on the {string} link")
    public void i_tap_on_about_us_link(String text) {
        topMenuPage = PageFactory.initElements(driver, TopMenu_Page.class);
        basePage = PageFactory.initElements(driver, BasePage.class);
        basePage.clickAnyMatchingElementByText(topMenuPage.getTopMenuList(), text);

    }


    @And("I can see below links in About Us section:")
    public void i_can_see_below_links_in_about_us_section(DataTable dataTable) {
        aboutUsPage = PageFactory.initElements(driver, AboutUs_Page.class);
        Assert.assertTrue(aboutUsPage.getAboutUsTitlesList().contains(dataTable.cell(0, 0)));
        Assert.assertTrue(aboutUsPage.getAboutUsTitlesList().contains(dataTable.cell(1, 0)));
        Assert.assertTrue(aboutUsPage.getAboutUsTitlesList().contains(dataTable.cell(2, 0)));
        Assert.assertTrue(aboutUsPage.getAboutUsTitlesList().contains(dataTable.cell(3, 0)));
        Assert.assertTrue(aboutUsPage.getAboutUsTitlesList().contains(dataTable.cell(4, 0)));
        Assert.assertTrue(aboutUsPage.getAboutUsTitlesList().contains(dataTable.cell(5, 0)));
        Assert.assertTrue(aboutUsPage.getAboutUsTitlesList().contains(dataTable.cell(6, 0)));
        Assert.assertTrue(aboutUsPage.getAboutUsTitlesList().contains(dataTable.cell(7, 0)));
        gm = new GenericMethods(driver);
        boolean status = gm.isElementPresent("/html/body/header/div/div/div/nav/div/div/div[2]/div/div/ul/li[4]/ul/li/div/div/div/div/div/div/h4", "xpath");
        System.out.println(status);
        Assert.assertTrue(status);
    }


    @And("I can see {string} header and {int} other links")
    public void i_can_see_header_and_other_links(String aboutUsText, int linkCount) {
        AboutUs_Page aboutUsPage = PageFactory.initElements(driver, AboutUs_Page.class);
        Assert.assertTrue(aboutUsPage.getAboutUsText().contains(aboutUsText));
        Assert.assertEquals(aboutUsPage.getAllLinksCount(), linkCount);
    }

    @When("I tap {string} link in top menu page")
    public void i_tap_link_in_top_menu_page(String menuItemText) {
        topMenuPage = PageFactory.initElements(driver, TopMenu_Page.class);
        topMenuPage.clickAnItemMatchingTextFromTopMenuLinks(topMenuPage.getTopMenuList(), menuItemText);
    }

    @Then("I can see page heading as {string} and {int} other links")
    public void i_can_see_page_heading_as_industries(String industriesTitle, int linksCount) {
        homePage = PageFactory.initElements(driver, Home_Page.class);
        Assert.assertTrue(homePage.getIndustriesText().contains(industriesTitle));
        Assert.assertTrue(homePage.getIndustriesLinksCount() == linksCount);
    }


    @Then("I should see Industries header and other displayed links below:")
    public void i_should_see_industries_header_and_other_displayed_links_below(DataTable dataTable) {
        Assert.assertTrue(homePage.getIndustriesTitlesList().contains(dataTable.cell(0, 0)));
        Assert.assertTrue(homePage.getIndustriesTitlesList().contains(dataTable.cell(1, 0)));
        Assert.assertTrue(homePage.getIndustriesTitlesList().contains(dataTable.cell(2, 0)));
        Assert.assertTrue(homePage.getIndustriesTitlesList().contains(dataTable.cell(3, 0)));
    }

    @Then("Industries Sub links are present")
    public void industries_and_sub_links_are_present() {
        Assert.assertTrue(homePage.assertIndustriesTitlesArePresent("Financial Services"));
        Assert.assertTrue(homePage.assertIndustriesTitlesArePresent("Insurance"));
        Assert.assertTrue(homePage.assertIndustriesTitlesArePresent("Life and Pensions"));
        Assert.assertTrue(homePage.assertIndustriesTitlesArePresent("Corporations and Non-Profits"));
    }

    @Then("Financial Services Sub title links are present")
    public void financial_services_sub_title_links_are_present() {
        Assert.assertTrue(homePage.assertFinancialServicesItemsPresent().get(0).isDisplayed());

    }

    @When("I tap {string} link Industries")
    public void i_tap_on_financial_services_industries_links(String industryName) {
        Assert.assertTrue(homePage.getFinancialServicesLinkText().equalsIgnoreCase(industryName));
        homePage.clickElementInIndustriesLink(industryName);

    }

    @Then("I can see {string} and {int} red links are displayed")
    public void i_should_see_seven_links_are_displayed(String selectIndustry, int linkCount) {
        Assert.assertTrue(homePage.getSelectIndustryText().equalsIgnoreCase(selectIndustry));
        Assert.assertTrue(homePage.getFinancialServicesLinksCount() == linkCount);
    }


    @Then("I can see links displayed as follows:")
    public void i_can_see_links_displayed_as_follows(DataTable dataTable) {
        Assert.assertTrue(homePage.getFinancialServicesList().contains(dataTable.cell(0, 0)));
        Assert.assertTrue(homePage.getFinancialServicesList().contains(dataTable.cell(1, 0)));
        Assert.assertTrue(homePage.getFinancialServicesList().contains(dataTable.cell(2, 0)));
        Assert.assertTrue(homePage.getFinancialServicesList().contains(dataTable.cell(3, 0)));
        Assert.assertTrue(homePage.getFinancialServicesList().contains(dataTable.cell(4, 0)));
        Assert.assertTrue(homePage.getFinancialServicesList().contains(dataTable.cell(5, 0)));
        Assert.assertTrue(homePage.getFinancialServicesList().contains(dataTable.cell(6, 0)));
    }

    @Then("{string} is displayed")
    public void cookie_preference_center_is_displayed(String headingText) throws InterruptedException {
        Assert.assertTrue(cookieBannerPage.getCookiePreferenceCenterText().contains(headingText));
    }

    @When("I click on {string} in home page")
    public void i_click_on_cookies_settings_in_home_page(String cookieSettingsText) throws IOException {
        Assert.assertTrue(cookieBannerPage.getCookieSettingsButtonText().contains(cookieSettingsText));
        cookieBannerPage.clickCookieSettingsButton();
    }


    @When("I should see {string} is {string} by default")
    public void i_should_see_strictly_necessary_cookies_is_always_active_by_default(String categoryName, String cookieValue) {
        new JavaScriptHelper(driver).scrollIntoView(cookieBannerPage.getStrictlyNecessaryCookies());
        cookieBannerPage.assertItemDisplayedFromCategoryHeaderList(categoryName);
        Assert.assertTrue(cookieBannerPage.getAlwaysActiveText().contains(cookieValue));
    }

    @When("I click plus icon in {string} from Manage Consent Preferences")
    public void i_click_plus_icon_from_consent_preferences(String categoryName) {
        cookieBannerPage.clickAccordionPlusMinusList(categoryName);
    }

    @When("I can see minus icon and switch nob should not be displayed in {string}")
    public void i_can_see_minus_icon_and_switch_nob_should_not_be_displayed(String categoryName) {
        cookieBannerPage.assertPlusOrMinusButtonIsDisplayed(categoryName);
        cookieBannerPage.assertSwitchNobIsDisplayed(categoryName);
    }


    @And("I click on {string} button")
    public void i_click_on_button(String acceptAllCookies) {
        existsElement(cookieBannerPage.getCookieBanner());
        Assert.assertTrue(cookieBannerPage.getAcceptAllCookiesButtonText().contains(acceptAllCookies));
        cookieBannerPage.clickAcceptAllCookiesButton();
    }

    @Then("I tap on {string} button in cookies banner")
    public void i_tap_accept_all_cookies_button_in_cookies_banner(String cookiesName) {
        existsElement(cookieBannerPage.getCookieBanner());
        cookieBannerPage.clickAnyCookieBannerButton(cookieBannerPage.getCookieBannerButtons(), cookiesName);
    }



    @Then("I tap on {string} button")
    public void i_tap_on_confirm_my_choices_button(String confirmMyChoiceButtonText) throws InterruptedException {
        Assert.assertTrue(cookieBannerPage.getConfirmMyChoiceButtonText().contains(confirmMyChoiceButtonText));
        cookieBannerPage.clickConfirmMyChoicesButton();
    }

    @When("I click plus icon and switch nob from category {string}")
    public void i_click_plus_icon_and_switch_nob_from_Category(String categoryCookiesName) {
        cookieBannerPage.clickAccordionPlusMinusList(categoryCookiesName);
        cookieBannerPage.clickSwitchNobs(categoryCookiesName);
    }

    @When("I can see minus icon and switch nob turns green color in {string}")
    public void i_can_see_minus_icon_and_switch_nob_turns_green_color(String categoryName) {
        cookieBannerPage.assertPlusOrMinusButtonIsDisplayed(categoryName);
        cookieBannerPage.assertSwitchNobIsDisplayed(categoryName);
    }

    @When("I click {string} Cookies button")
    public void i_click_allow_all_cookies_button(String allowAllCookiesText) {
        Assert.assertTrue(cookieBannerPage.getAllowAllButtonText().contains(allowAllCookiesText));
        cookieBannerPage.clickAllowAllButton();
    }

    @When("I should see Showing {string} in Job page")
    public void i_should_see_showing_result_in_job_page(String resultText) {
        jobsPage = PageFactory.initElements(driver, Jobs_Page.class);
        Assert.assertTrue(jobsPage.getStatisticsResultText().contains(resultText));

    }

    @When("I should see Showing result count {string} in Job page")
    public void i_should_see_showing_result_count_in_job_page(String resultCount) {
        jobsPage = PageFactory.initElements(driver, Jobs_Page.class);
        //Assert.assertTrue(jobsPage.getSearchResultsTitles().contains(resultCount));
    }

    @When("I should see Showing 211 Results")
    public void i_should_see_showing_results() {
        jobsPage = PageFactory.initElements(driver, Jobs_Page.class);
       jobsPage.assertJobSearchResultsByCount(Integer.parseInt("211"));
    }

    @When("I click x button policy privacy bottom banner")
    public void i_click_close_policy_privacy_section() throws IOException {
        cookieBannerPage.clickCloseCookiePolicyButton();
    }

    @And("I close browser")
    public void iCloseBrowser() {
        log.info("Driver quitting.....");
        driver.quit();
    }


}
