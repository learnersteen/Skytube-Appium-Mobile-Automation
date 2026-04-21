package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchChannelPage;
import utilities.LogUtil;

public class SearchChannelSteps {


    private SearchChannelPage searchChannelPage;
    
	 @Given("The SkyTube app is launched")
	    public void the_sky_tube_app_is_launched() {
		 
	     searchChannelPage = new SearchChannelPage();
		 
		 searchChannelPage.handlePopupIfPresent();
	        searchChannelPage.waitForHomeScreenToLoad();

	        LogUtil.info("SkyTube app launched successfully");      
	    
	 }
	 
	 	//TC01
	    @Then("the search icon should be visible")
	    public void the_search_icon_should_be_visible() {
	    	
	    	// Assert.assertTrue(searchChannelPage.isSearchIconDisplayed(), "Search icon is not visible"   );
	    	Assert.assertTrue(searchChannelPage.isSearchIconDisplayed(), "Search icon is not visible"   );
	    }

	    //TC02

	    @When("the user clicks the search icon")
	    public void userClicksSearchIcon() {
	        searchChannelPage.clickSearchIcon();
	    }

	    @Then("the search input field should be displayed")
	    public void verifySearchFieldDisplayed() {
	        Assert.assertTrue(searchChannelPage.isSearchIconDisplayed(),
	            "Search field is not displayed after clicking search icon");
	    }
	    
	    //TC03
	    
	    @When("the user enters {string} in the search field")
	    public void the_user_enters_in_the_search_field(String keyword) {
	        searchChannelPage.enterSearchText(keyword);
	    }

	    @When("the user presses the Enter key")
	    public void the_user_presses_the_enter_key() {
	        searchChannelPage.pressEnterKey();
	    }

	    @Then("the search results should be displayed for {string}")
	    public void the_search_results_should_be_displayed_for(String keyword) {
	        Assert.assertTrue(
	            searchChannelPage.areSearchResultsDisplayed(keyword),
	            "Search results were not displayed for keyword: " + keyword
	        );
	    }
	}
	 
	 
	 