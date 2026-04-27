Feature: SkyTube Watched Video Cleaner

  Background:
    Given The SkyTube app is launched

  Scenario: Verify Watched Video Cleaner option is displayed
    When the user clicks three dots options menu next to Shield icon
    Then the Watched Video Cleaner option should be displayed
    
  Scenario: Verify Watched Video Cleaner popup is displayed 
  	When the user clicks Watched Video Cleaner option 
  	Then Watched Video Cleaner popup with Cancel and Clean should be displayed
  	
  Scenario: Verify user can uncheck both options and click Clean
    When the user clicks Watched Video Cleaner option
    Then Watched Video Cleaner popup with Cancel and Clean should be displayed
    When the user unchecks Remove watched downloads
    And the user unchecks Remove watched bookmarks
    And the user clicks Clean button in Watched Video Cleaner popup
    
  Scenario: Verify user can close Watched Video Cleaner popup using Cancel
 	 When the user clicks Watched Video Cleaner option
  	 Then Watched Video Cleaner popup with Cancel and Clean should be displayed
 	 When the user clicks Cancel button in Watched Video Cleaner popup
  	
  	
  	
