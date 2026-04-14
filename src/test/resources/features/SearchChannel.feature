Feature: SkyTube Search Feature

  Background:
    Given The SkyTube app is launched

  Scenario: Verify search icon is visible
    Then the search icon should be visible
    
    Scenario: Verify search icon is clickable
    When the user clicks the search icon
    Then the search input field should be displayed
    
    
   Scenario Outline: Verify user is able to search and see results
      When the user clicks the search icon
      And the user enters "<keyword>" in the search field
      And the user presses the Enter key
      Then the search results should be displayed for "<keyword>"
        
        Examples:
      | keyword  |
      | appium   |
      | selenium |
    
