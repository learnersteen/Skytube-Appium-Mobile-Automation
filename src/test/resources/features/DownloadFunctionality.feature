Feature: SkyTube Download Feature

  Background:
    Given The SkyTube app is launched
    
    Scenario: Verify download option is clickable
     When the user clicks the three dots symbol in any video 
     Then the user should see download option and should be clickable
    
    Scenario: Verify download option triggers download attempt
      When the user clicks the three dots symbol in any video
      And the user clicks the download option
      Then the download error message should be displayed
    