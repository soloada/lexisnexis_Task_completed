package pages;

import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Careers_Page {
    final static Logger log = LoggerHelper.getLogger(Careers_Page.class);
    WebDriver driver;

    @FindBy(css = ".horizontal > div:nth-child(3) > a:nth-child(1)")
    private WebElement searchJobsButton;

    @FindBy(xpath = "//strong[normalize-space()='Explore More']")
    private WebElement exploreMoreText;

    @FindBy(xpath = "//h2[normalize-space()='Careers']")
    private WebElement careersHeadingText;

    public WebElement getSearchJobsButton() {
        return searchJobsButton;

    }

    public Jobs_Page clickSearchJobsButton() {
        searchJobsButton.click();
        log.info("Search jobs button is clicked");
        return new Jobs_Page();
    }

    public String getSearchJobsButtonText() {
        log.info("Verified the text is displayed");
        return searchJobsButton.getText();

    }

    public String getExploreMoreText() {
        log.info("Verified the text is displayed");
        return exploreMoreText.getText();

    }

    public String getCareersHeadingText() {
        log.info("Verified the text is displayed");
        return careersHeadingText.getText();

    }


}


