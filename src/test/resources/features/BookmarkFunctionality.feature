Feature: SkyTube Bookmark Functionality

  Background:
    Given The SkyTube app is launched
    When the user clicks the search icon

  Scenario Outline: Verify Bookmark option is visible in video options

    And the user enters "<keyword>" in the search field
    And the user presses the Enter key
    Then the search results should be displayed for "<keyword>"

    When the user opens the options menu for the first video
    Then the user should see "Bookmark" option

  Examples:
    | keyword |
    | appium  |
    
      Scenario Outline: Verify user can bookmark a video

    And the user enters "<keyword>" in the search field
    And the user presses the Enter key
    Then the search results should be displayed for "<keyword>"
  
    When the user bookmarks the first video
    When the user clicks the Bookmarks tab
    Then the video should be present in the Bookmarks tab

  Examples:
    | keyword |
    | appium  |
  
  Scenario Outline: Verify user can remove a bookmarked video

  And the user enters "<keyword>" in the search field
  And the user presses the Enter key
  Then the search results should be displayed for "<keyword>"

  When the user bookmarks the first video
  And the user clicks the Bookmarks tab
  And the user removes the bookmarked video
  Then the video should not be present in the Bookmarks tab

Examples:
  | keyword |
  | appium  |