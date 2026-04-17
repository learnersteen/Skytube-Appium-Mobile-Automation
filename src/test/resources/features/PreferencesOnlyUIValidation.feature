Feature: SkyTube Preferences Feature

  Background:
    Given The SkyTube app is launched

 Scenario: Verify all Preference Categories in one session
    When the user clicks the three dots menu icon
    And the user clicks on the "Preferences" option
    Then the following categories should be visible:
      | Video Player          |
      | Video Blocker         |
      | SponsorBlock          |
      | Import/Export         |
      | Privacy               |
      | Network and Downloads |
      | Security              |
      | Others                |
      | About                 |
      
   #2. check: Click each, verify the header and go back
    And the user verifies and returns from each sub-page:
      | Video Player          |
      | Video Blocker         |
      | SponsorBlock          |
      | Import/Export         |
      | Privacy               |
      | Network and Downloads |
      | Security              |
      | Others                |
      | About                 |