@STC
@ALL
Feature: As a user I want to be able to search for jobs with the correct Job title, so that I can find the right job and
  If I enter wrong job title, I shouldn't get result

  Background: Common steps
    Given I navigate to home page "https://risk.lexisnexis.co.uk/"
    And I am on the page "lexisnexis" and can see title as "LexisNexis Risk Solutions"
    And I  see cookie banner is displayed
    And I tap on "Accept All Cookies" button in cookies banner
    And I should not see the cookie banner
    And logo is displayed
    When I tap on the "About Us" link
    And I can see "About Us" header and 9 other links
    And I can see below links in About Us section:
      | Business Overview                    |
      | Executive Leadership                 |
      | Our Technology                       |
      | Careers                              |
      | Press Room                           |
      | Experts                              |
      | Corporate Responsibility             |
      | Diversity & Inclusion                |
      | Corporate Alliances and Partnerships |
    And click on "Careers" link
    And I click x button policy privacy bottom banner
    And It navigate to new window and I can see in Careers page as follows:
      | Explore More |
      | Careers      |
      | Search jobs  |
    And I tap on "Search jobs"
    And I am in the job page and can see title as "Search Jobs"


  Scenario Outline: Search should show at least 1 results
    When I search for job with a job title "<Job Title>"
    Then I can see the job search result count displayed in the page is greater than <Job Count>
    And I should see Text for many items "<Job Title>"
    And I close browser
    Examples:
      | Job Title         | Job Count |
      | automation tester | 0         |

  Scenario Outline: Search results should be related to the job title
    When I search for job with a job title "<Job Title>"
    And I can see the job search result count displayed in the page is greater than <Job Count>
    And I should see Text for many items "<Job Title>"
    Then I can see search results related to the "<Job Title>"
    And I close browser
    Examples:
      | Job Title         | Job Count |
      | Software Engineer | 0         |

  Scenario Outline: Search results is not relevant to the job title
    When I search for job with a job title "<Job Title>"
    And I can see the job search result count displayed in the page is greater than <Job Count>
    And I should see Text for many items "<Job Title>"
    Then I can see search results not related to the "<Job Title>"
    And I close browser
    Examples:
      | Job Title  | Job Count |
      | Sales | 0         |

  Scenario:  Search Should display No Result
    When I search for job with a job title "auto-mania"
    Then I can see the search result count as "No Results"
    And I close browser