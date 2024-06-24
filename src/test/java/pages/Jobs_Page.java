package pages;

import com.google.common.util.concurrent.Uninterruptibles;
import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.GlobalVars;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Jobs_Page {
    final static Logger log = LoggerHelper.getLogger(Jobs_Page.class);
    Jobs_Page jobsPage;
    WebDriver driver;
    @FindBy(xpath = "//h1[normalize-space()='Search jobs']")
    private WebElement searchAllJobsHeader;
    @FindBy(css = ".search-box-input.ais-search-box--input")
    private WebElement searchBox;
    @FindBy(css = "#search-box-button")
    private WebElement searchButton;

    @FindBy(css = "div.no-results")
    private WebElement noSearchResultsHeader;

    @FindBy(css = "#search-results-statistics-e0271e2d03ab43d1b65ffb7850e12e1d")
    private WebElement showingResultsStatistics;

    @FindBy(css = "a.search-results-item-title-url")
    private List<WebElement> searchResultsItemTitleList;
    @FindBy(css = ".social-btn-share")
    private WebElement socialShareButton;
    @FindBy(css = "#search-results-statistics-e0271e2d03ab43d1b65ffb7850e12e1d")
    private WebElement searchResultsHeader;

    @FindBy(css = "a.search-results-item-title-url")
    private List<WebElement> searchResultsJobList;

    @FindBy(css = "a[aria-label='Previous']")
    private WebElement previousArrows;

    @FindBy(css = "a[aria-label='Next']")
    private WebElement nextArrows;
    @FindBy(css = "h3.search-filter-header")
    private List<WebElement> searchFilterHeaders;

    @FindBy(xpath = "//div[contains(text(),'Clear all')]")
    private WebElement clearAllLink;
    @FindBy(xpath = "(//div[@class='ais-body ais-stats--body'])[1]")
    private WebElement textForManySearchResultsItems;
    @FindBy(css = "span.selected-placeholder.selected-item")
    private WebElement selectOneMoreText;
    @FindBy(xpath = "div[class='lnrs-filter-selected-options'] span[class='caret']")
    private List<WebElement> selectOneMoreDropDownList;


    public boolean assertSearchAllJobsHeaderIsDisplayed() {
        return searchAllJobsHeader.isDisplayed();
    }

    public void clickSocialShareButton() {
        socialShareButton.click();

    }

    public boolean assertSocialShareButtonIsDisplayed() {
        return socialShareButton.isDisplayed();
    }

    public WebElement getSearchResultsHeader() {
        return searchResultsHeader;

    }

    public List<String> getSearchFilterHeadersText() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((s) -> searchFilterHeaders.size() > 1);
        return searchFilterHeaders.stream().parallel().filter(WebElement::isDisplayed).map(WebElement::getText).toList();
    }

    public WebElement getSearchAllJobsHeader() {
        return searchAllJobsHeader;

    }

    public WebElement getSearchBox() {
        return searchBox;

    }

    public WebElement getSearchButton() {
        return searchButton;

    }

    public WebElement getShowingResultsStatistics() {
        return showingResultsStatistics;

    }

    public String getDisplayedCount() {
        return showingResultsStatistics.getText().split(" ")[1];

    }

    public WebElement getNoSearchResultsHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.explicitWait)).until((s) -> noSearchResultsHeader.isDisplayed());
        return noSearchResultsHeader;

    }

    public boolean assertSearchBoxIsDisplayed() {
        return searchBox.isDisplayed();

    }

    public boolean assertSearchBoxIsEnable() {
        return searchBox.isEnabled();

    }

    public String getShowingResultsStatisticsText() {
        return showingResultsStatistics.getText();
    }

    public boolean assertShowingResultsStatisticsIsDisplayed() {
        return showingResultsStatistics.isDisplayed();
    }

    public int getDisplayedJobsTitlesCountGreaterThanZero() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((s) -> searchResultsItemTitleList.size() > 0);
        log.info("Displayed jobs titles count: " + searchResultsItemTitleList.size());
        return searchResultsItemTitleList.size();

    }

    public boolean assertSearchResultsForJobTitle(String jobTitle) {
        log.info("Displayed jobs titles count: " + searchResultsItemTitleList.size());
        return searchResultsItemTitleList.stream().parallel().filter(WebElement::isDisplayed).anyMatch(s -> s.getText().contains(jobTitle));

    }

    public boolean assertJobSearchResultsByJobTitle(String jobTitle) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((s) -> searchResultsItemTitleList.size() > 0);
        for (int i = 0; i < searchResultsItemTitleList.size(); i++) {
            if (searchResultsItemTitleList.get(i).isDisplayed()) {
                return searchResultsItemTitleList.get(i).getText().contains(jobTitle);
            }
        }
        return true;
    }

    public boolean assertJobSearchResultsByCount(int count) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((s) -> searchResultsItemTitleList.size() > 0);
        for (int i = 0; i < searchResultsItemTitleList.size(); i++) {
            if (searchResultsItemTitleList.get(i).isDisplayed()) {
                    count--;
                    if (count == 0) {
                        return true;
                    }
            }
        }
        return false;
    }

    public void inputSearchBox(String jobTitle) {
        searchBox.clear();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((s) -> searchBox.isDisplayed());
        searchBox.sendKeys(jobTitle);
        log.info("Search box is entered with text: " + jobTitle);
    }

    public String getTextForManySearchResultsItems() {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.explicitWait)).until(driver -> textForManySearchResultsItems.isDisplayed());
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(GlobalVars.SHORT_IMPLICIT_WAIT_TIME));
        return textForManySearchResultsItems.getText();
    }

    public boolean assertTextForManySearchResultsItemsIsDisplayed() {
        new Actions(driver).moveToElement(textForManySearchResultsItems).build().perform();
        return textForManySearchResultsItems.isDisplayed();
    }

    public boolean assertSelectOneMoreTextIsDisplayed() {
        return selectOneMoreText.isDisplayed();

    }

    public List<WebElement> getSelectOneMoreDropDownList() {
        return selectOneMoreDropDownList;

    }

    public void enterSearchBox(String jobTitle) {
        searchBox.clear();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((s) -> searchBox.isDisplayed());
        searchBox.sendKeys(jobTitle);
        log.info("Search box is entered with text: " + jobTitle);
    }

    public SearchResult_Page clickSearchButton() {
        searchButton.click();
        log.info("Search button is clicked");
        return new SearchResult_Page();
    }

    public SearchResult_Page searchWithValidJobTitle(String jobTitle) {
        enterSearchBox(jobTitle);
        searchButton.click();
        return new SearchResult_Page();

    }

    public SearchResult_Page SearchWithInvalidJobTitle(String jobTitle) {
        enterSearchBox(jobTitle);
        searchButton.click();
        return new SearchResult_Page();
    }

    public String getSearchResultsCountText() {
        return searchResultsHeader.getText();

    }

    public boolean assertSearchResultsHeaderIsDisplayed() {
        return searchResultsHeader.isDisplayed();

    }

    public boolean assertNoSearchResultsHeaderIsDisplayed() {
        return noSearchResultsHeader.isDisplayed();

    }

    public String getNoSearchResultsHeaderText() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((s) -> noSearchResultsHeader.isDisplayed());
        log.info("Text is displayed");
        return noSearchResultsHeader.getText();

    }

    public boolean clickNextArrows() {
        nextArrows.click();
        return false;
    }

    public List<WebElement> getPageSearchResultsItemTitleList() {
        //new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.explicitWait)).until(driver -> searchResultsItemTitleList.size() == 20);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(GlobalVars.SHORT_IMPLICIT_WAIT_TIME));
        return searchResultsItemTitleList.stream().parallel().filter(WebElement::isDisplayed).toList();

    }


    public String getStatisticsResultText() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((s) -> showingResultsStatistics.isDisplayed());
        log.info("Verified the text is displayed");
        return showingResultsStatistics.getText();

    }

    public ArrayList<String> getSearchResultsTitles() {
        //ArrayList<String> names = new ArrayList<String>();

        ArrayList<String> names;
        List<String> namesOfJobTitles;
        do {
            names = new ArrayList<String>();
            namesOfJobTitles = searchResultsItemTitleList.stream().parallel().map(WebElement::getText).toList();
            names.add(String.valueOf(namesOfJobTitles));
            System.out.println("===============================================");
            names.forEach(System.out::println);
            clickNextArrows();
            System.out.println("===============================================");
        } while (names.isEmpty());

        clickNextArrows();
        return names;
    }

    public List<WebElement> getSearchResultsItemTitleList() {
        return searchResultsItemTitleList;
    }

    public void assertJobSearchResultsCount() throws InterruptedException {
        List<String> names = new ArrayList();
        List<WebElement> jobsTitleList = getSearchResultsItemTitleList();
        for(WebElement jobName:jobsTitleList){
            new WebDriverWait(driver, Duration.ofSeconds(60)).until((s) -> getSearchResultsItemTitleList().size() > 7);
            Thread.sleep(2000);
            names.add(jobName.getText());
            Thread.sleep(1000);
            System.out.println(jobName.getText());
        }
        System.out.println("===============================================");

        while(true){
            Thread.sleep(2000);
            clickNextArrows();
            Thread.sleep(2000);
            jobsTitleList = getSearchResultsItemTitleList();
            for(WebElement jobName:jobsTitleList){
                Thread.sleep(2000);
                names.add(jobName.getText());
                Thread.sleep(2000);
                System.out.println(jobName.getText());
            }
            System.out.println("===============================================");
            try {
                driver.findElement(By.cssSelector("a[aria-label='Next']"));
            } catch (Exception e) {
                System.out.println("No more Job Title");
                break;
            }

            System.out.println("===============================================");

            System.out.println("Total number of Job Title:: " +names.size());
            System.out.println("Job Title statistic Displayed:" + getDisplayedCount());

        }

    }

}