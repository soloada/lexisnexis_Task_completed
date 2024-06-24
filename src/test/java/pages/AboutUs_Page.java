package pages;

import base.BasePage;
import helper.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AboutUs_Page {
    final static Logger log = LoggerHelper.getLogger(AboutUs_Page.class);

    @FindBy(xpath = "/html/body/header/div/div/div/nav/div/div/div[2]/div/div/ul/li[4]/ul/li/div/div/div/div/div/div/h4")
    List<WebElement> allAboutLinks;

    public List<WebElement> getAllAboutLinks() {
        return allAboutLinks;
    }

    @FindBy(xpath = "//div[@class='score-content-spot']//p[contains(text(),'About Us')]")
    private WebElement aboutUsHeadingText;
    @FindBy(xpath = "//div[@class='score-style-box clearfix']//a[@class='score-button btn-clickable-area'][normalize-space()='Learn More']")
    private WebElement careersLink;


    public Careers_Page clickCareersLink() {
        if (careersLink.isDisplayed() && careersLink.isEnabled()) {
            log.info("Careers Link is displayed and enabled");
            careersLink.click();
            log.info("Clicked on Careers Link");
        }
        return new Careers_Page();
    }

    public boolean assertAnyLinksInAboutUsPage(String linkText) {
        return allAboutLinks.stream()
                .parallel()
                .anyMatch(s -> s.getText().equalsIgnoreCase(linkText));
    }

    public String getAboutUsText() {
        log.info("Getting About Us Text");
        return aboutUsHeadingText.getText().trim();
    }

    public int getAllLinksCount() {
        log.info("Asserting all links count");
        return allAboutLinks.size();
    }

    public List<String> getAboutUsTitlesList() {
        new BasePage().waitForElementsToAppear(allAboutLinks);
        log.info("Getting About Us Titles List");
        return allAboutLinks.stream().parallel().filter(link -> link.isDisplayed() && link.isEnabled()).map(WebElement::getText).toList();
    }


}


