package pages;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResult_Page {
    final static Logger log = LoggerHelper.getLogger(Home_Page.class);
    WebDriver driver;
    @FindBy(xpath = "//h1[normalize-space()='Search Results']")
    private WebElement searchResultsHeadingText;

    public String getSearchResultsHeadingText() {
        return searchResultsHeadingText.getText();
    }

    public boolean assertSearchResultsHeadingTextIsDisplayed() {
        return searchResultsHeadingText.isDisplayed();
    }


}
