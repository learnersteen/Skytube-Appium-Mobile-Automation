Feature: SkyTube Video Blocker Setup

  Background:
    Given The SkyTube app is launched

    Scenario: Verify video blocker shield icon is visible
    Then the video blocker shield icon should be visible
    
    Scenario: Verify video blocker popup is displayed
    When the user clicks the video blocker shield icon
    Then the video blocker popup should be displayed

     Scenario: Verify user can cancel video blocker setup
    When the user clicks the video blocker shield icon
    Then the video blocker popup should be displayed
    When the user clicks Cancel on the video blocker popup
    Then the video blocker popup should be closed
    
      Scenario: Verify user can open video blocker setup page
    When the user clicks the video blocker shield icon
    Then the video blocker popup should be displayed
    When the user clicks Set Up on the video blocker popup
    Then the Video Blocker preferences page should be displayed

 Scenario: Verify Video Blocker checkbox is checked by default
    When the user clicks the video blocker shield icon
    Then the video blocker popup should be displayed
    When the user clicks Set Up on the video blocker popup
    Then the Video Blocker checkbox should be checked by default