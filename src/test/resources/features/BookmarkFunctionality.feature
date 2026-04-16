Feature: SkyTube Bookmark Functionality 

  Background:
    Given The SkyTube app is launched
    When the user clicks the search icon

  Scenario Outline: Verify Bookmark option is visible after clicking three dots
    When the user enters "<keyword>" in the search field
    And the user presses the Enter key
    Then the search results should be displayed for "<keyword>"

    # Click 3 dots (options menu)
    When the user clicks the options menu for the first video

    # Validate Bookmark option
    Then the "Bookmark" option should be visible

    Examples:
      | keyword |
      | appium  |