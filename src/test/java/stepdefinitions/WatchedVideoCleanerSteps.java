package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.WatchedVideoCleanerPage;

public class WatchedVideoCleanerSteps {

	 private WatchedVideoCleanerPage watchedVideoCleanerPage;
	 
	    public WatchedVideoCleanerSteps() {
	    	watchedVideoCleanerPage = new WatchedVideoCleanerPage();
	    }
	    
	    @When("the user clicks three dots options menu next to Shield icon")
	    public void the_user_clicks_three_dots_options_menu_next_to_shield_icon() {
	    	 watchedVideoCleanerPage.clickThreeDotsMoreOptions();
	    }

	    @Then("the Watched Video Cleaner option should be displayed")
	    public void the_watched_video_cleaner_option_should_be_displayed() {
	    	  Assert.assertTrue(watchedVideoCleanerPage.isWatchedVideoCleanerDisplayed(), "Watched Video Cleaner option is not displayed");
	    }
	    
	    
	    @When("the user clicks Watched Video Cleaner option")
	    public void the_user_clicks_watched_video_cleaner_option() {
	    	  watchedVideoCleanerPage.clickThreeDotsMoreOptions();
	          watchedVideoCleanerPage.clickWatchedVideoCleanerOption();
	    }

	    @Then("Watched Video Cleaner popup with Cancel and Clean should be displayed")
	    public void watched_video_cleaner_popup_with_cancel_and_clean_should_be_displayed() {
	    	 Assert.assertTrue(watchedVideoCleanerPage.isWatchedVideoCleanerPopupDisplayed(), "Watched Video Cleaner popup is not displayed");
	    }
	    
	    @When("the user unchecks Remove watched downloads")
	    public void the_user_unchecks_remove_watched_downloads() {
	    	 watchedVideoCleanerPage.uncheckRemoveWatchedDownloads();
	    }

	    @When("the user unchecks Remove watched bookmarks")
	    public void the_user_unchecks_remove_watched_bookmarks() {
	    	 watchedVideoCleanerPage.uncheckRemoveWatchedBookmarks();
	    }

	    @When("the user clicks Clean button in Watched Video Cleaner popup")
	    public void the_user_clicks_clean_button_in_watched_video_cleaner_popup() {
	    	watchedVideoCleanerPage.clickClean();
	    }
	    
	    @When("the user clicks Cancel button in Watched Video Cleaner popup")
	    public void the_user_clicks_cancel_button_in_watched_video_cleaner_popup() {
	        watchedVideoCleanerPage.clickCancel();
	    }



}
