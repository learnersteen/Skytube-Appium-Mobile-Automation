package stepdefinitions;

import hooks.Hooks;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.DownloadPage;
import pages.SearchChannelPage;
import utilities.LogUtil;

public class DownloadSteps {
	 private DownloadPage dwldPage;
	public DownloadSteps() {
        dwldPage = new DownloadPage(); 
    }


	    @When("the user clicks the three dots symbol in any video")
	    public void user_clicks_three_dots() {
	    	dwldPage.clickThreeDots();
	    }

	    @Then("the user should see download option and should be clickable")
	    public void user_sees_download_option() {
	       
	        Assert.assertTrue(
	        		dwldPage.isDownloadOptionVisible(),
	            "Download option is NOT visible in the menu"
	        );
	        
	        dwldPage.clickDownloadOption();
	        LogUtil.info("Download option was visible and clickable");
	    }
	    @And("the user clicks the download option")
	    public void user_clicks_download_option() {
	        Assert.assertTrue(
	            dwldPage.isDownloadOptionVisible(),
	            "Download option is not visible in the menu"
	        );
	        dwldPage.clickDownloadOption();
	        dwldPage.clickDownloadVideo();
	        LogUtil.info("Download option clicked successfully");
	    }

	    @Then("the download error message should be displayed")
	    public void download_error_should_display() {
	        boolean errorShown = dwldPage.isDownloadErrorMessageDisplayed();
	        String msg = dwldPage.getDownloadErrorMessageText();

	        LogUtil.info("Message on screen: " + msg);

	        Assert.assertTrue(
	            errorShown,
	            "Download error message was NOT displayed. Got: " + msg
	        );

	        Assert.assertTrue(
	            msg.contains("Could not find any video stream") ||
	            msg.contains("resolution"),
	            "Unexpected message text: " + msg
	        );

	        LogUtil.info("PASS: Download triggered. Error message verified: " + msg);
	    }
	}