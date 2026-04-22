package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.VideoBlockerPage;
import utilities.LogUtil;

public class VideoBlockerSteps {

	 private VideoBlockerPage videoBlockerPage;
	 
	    public VideoBlockerSteps() {
	        videoBlockerPage = new VideoBlockerPage();
	    }
	    

	    @Then("the video blocker shield icon should be visible")
	    public void the_video_blocker_shield_icon_should_be_visible() {
	        Assert.assertTrue(
	                videoBlockerPage.isShieldIconDisplayed(),
	                "Video blocker shield icon is not visible"
	        );
	        LogUtil.info("Verified video blocker shield icon is visible");
	    }
	    
	    
	    @When("the user clicks the video blocker shield icon")
	    public void the_user_clicks_the_video_blocker_shield_icon() {
	    	  videoBlockerPage.clickShieldIcon();
	          LogUtil.info("Clicked the video blocker shield icon");
	    }

	    @Then("the video blocker popup should be displayed")
	    public void the_video_blocker_popup_should_be_displayed() {
	       Assert.assertTrue(videoBlockerPage.isVideoBlockerPopupDisplayed(), "Video blocker popup is not displayed");
	       LogUtil.info("Verified video blocker popup is displayed");
	    }
	    
	    
	    @When("the user clicks Cancel on the video blocker popup")
	    public void the_user_clicks_cancel_on_the_video_blocker_popup() {
	    	 videoBlockerPage.clickCancel();
	         LogUtil.info("Clicked Cancel on video blocker popup");
	    }

	    @Then("the video blocker popup should be closed")
	    public void the_video_blocker_popup_should_be_closed() {
	    	 Assert.assertTrue(videoBlockerPage.isPopupClosed(), "Video blocker popup is still displayed");
	         LogUtil.info("Verified video blocker popup is closed");
	    }

	    
	    @When("the user clicks Set Up on the video blocker popup")
	    public void the_user_clicks_set_up_on_the_video_blocker_popup() {
	    	 videoBlockerPage.clickSetUp();
	         LogUtil.info("Clicked Set Up on video blocker popup");
	    }

	    @Then("the Video Blocker preferences page should be displayed")
	    public void the_video_blocker_preferences_page_should_be_displayed() {
	    	 Assert.assertTrue(videoBlockerPage.isPreferencesPageDisplayed(), "Video Blocker preferences page is not displayed");
	         LogUtil.info("Verified Video Blocker preferences page is displayed");
	    }
	    
	    @Then("the Video Blocker checkbox should be checked by default")
	    public void the_video_blocker_checkbox_should_be_checked_by_default() {
	        Assert.assertTrue(videoBlockerPage.isVideoBlockerChecked(), "Video Blocker checkbox is not checked by default");
	        LogUtil.info("Verified Video Blocker checkbox is checked by default");

}
	    
}
