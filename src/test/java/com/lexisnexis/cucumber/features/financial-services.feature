@FSTC
@ALL
Feature: Choose your Industry to Financial Services.
  As a user I should be able to choose my Industry to Financial Services


  Scenario: Financial Services Functionality
    Given I navigate to home page "https://risk.lexisnexis.co.uk/"
    And I am on the page "lexisnexis" and can see title as "LexisNexis Risk Solutions"
    And I  see cookie banner is displayed
    And I click on "Accept All Cookies" button
    And I should not see the cookie banner
    When I tap "Choose Your Industry" link in top menu page
    And I can see page heading as "Industries" and 4 other links
    And Industries Sub links are present
    Then I should see Industries header and other displayed links below:
      | Financial Services           |
      | Insurance                    |
      | Life and Pensions            |
      | Corporations and Non-Profits |
    And I tap "Financial Services" link Industries
    And I can see "Select an Industry" and 7 red links are displayed
    And Financial Services Sub title links are present
    And I can see links displayed as follows:
      | Financial Crime Compliance       |
      | Fraud and Identity Management    |
      | Customer Data Management         |
      | Credit Risk Assessment           |
      | Collections and Recovery         |
      | Investigations and Due Diligence |
      | Risk Orchestration               |
    And I close browser

