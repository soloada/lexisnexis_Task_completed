@CTC
@ALL
Feature: As a user I should see cookie preferences banner so that I can accept or decline the use of cookies

  Background:
    Given I navigate to home page "https://risk.lexisnexis.co.uk/"
    And I am on the page "lexisnexis" and can see title as "LexisNexis Risk Solutions"
    And I  see cookie banner is displayed


  Scenario: When Cookies is accept, banner should disappear
    When I click on "Accept All Cookies" button
    Then I should not see the cookie banner
    And I close browser

  Scenario: If cookie settings is changed, Privacy Preference Centre should be displayed
    When I click on "Cookie Settings" in home page
    Then "Privacy Preference Centre" is displayed
    And I should not see the cookie banner
    And I close browser

  Scenario: User tap Cookies setting, strictly Necessary Cookies is always Active
    When I click on "Cookie Settings" in home page
    And "Privacy Preference Centre" is displayed
    And I should see "Strictly Necessary Cookies" is "Always Active" by default
    And I click plus icon in "Strictly Necessary Cookies" from Manage Consent Preferences
    And I can see minus icon and switch nob should not be displayed in "Strictly Necessary Cookies"
    And I tap on "Confirm My Choices" button
    Then I should not see the cookie banner
    And I close browser

  Scenario: When Cookies setting is tap, minus icon is displayed and switch nob should be green color
    When I click on "Cookie Settings" in home page
    And "Privacy Preference Centre" is displayed
    And I should see "Strictly Necessary Cookies" is "Always Active" by default
    And I click plus icon and switch nob from category "Privacy Preference Centre"
    And I can see minus icon and switch nob turns green color in "Privacy Preference Centre"
    And I tap on "Confirm My Choices" button
    Then I should not see the cookie banner
    And I close browser

  Scenario: When user tap Cookies setting, should Allow All button
    When I click on "Cookie Settings" in home page
    And "Privacy Preference Centre" is displayed
    And I should see "Strictly Necessary Cookies" is "Always Active" by default
    And I click plus icon and switch nob from category "Privacy Preference Centre"
    And I can see minus icon and switch nob turns green color in "Privacy Preference Centre"
    And I click "Allow All" Cookies button
    Then I should not see the cookie banner
    And I close browser