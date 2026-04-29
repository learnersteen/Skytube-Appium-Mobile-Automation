Feature: SkyTube Preferences Feature

  Background:
    Given The SkyTube app is launched

  Scenario: Verify all Preference Categories from Excel
    When the user clicks the three dots menu icon
    And the user clicks on the "Preferences" option
    Then the user verifies all categories from Excel sheet "Categories"