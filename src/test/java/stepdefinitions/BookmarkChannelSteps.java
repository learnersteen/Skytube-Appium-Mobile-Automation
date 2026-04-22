package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BookmarkPage;
import utilities.LogUtil;

public class BookmarkChannelSteps {

    private BookmarkPage bookmarkPage;

    public BookmarkChannelSteps() {
        bookmarkPage = new BookmarkPage();
    }

    // TC01
    @When("the user opens the options menu for the first video")
    public void the_user_opens_the_options_menu_for_the_first_video() {
        bookmarkPage.openFirstVideoOptions();
    }
    
    @Then("the user should see {string} option")
    public void the_user_should_see_option(String optionName) {
        Assert.assertTrue(bookmarkPage.isBookmarkOptionVisible(), optionName + " option is NOT visible");
        LogUtil.info(optionName + " option is visible");
    }


    @When("the user bookmarks the first video")
    public void the_user_bookmarks_the_first_video() {
        bookmarkPage.bookmarkFirstVideo();
        LogUtil.info("User bookmarked the first video");
    }


    //TC02 
    @When("the user clicks the Bookmarks tab")
    public void the_user_clicks_the_bookmarks_tab() {
        bookmarkPage.goBackAfterBookmark();
        bookmarkPage.clickBookmarksTab();
    }

    @Then("the video should be present in the Bookmarks tab")
    public void the_video_should_be_present_in_the_bookmarks_tab() {
        Assert.assertTrue(bookmarkPage.isSavedVideoPresentInBookmarks(), "Bookmarked video is not present in the Bookmarks tab");
        LogUtil.info("Bookmarked video is present in the Bookmarks tab");
    }
    
    
    //TC03
    @When("the user removes the bookmarked video")
    public void the_user_removes_the_bookmarked_video() {
        bookmarkPage.removeBookmarkedVideo();
        LogUtil.info("User removed the bookmarked video");
    }

    @Then("the video should not be present in the Bookmarks tab")
    public void the_video_should_not_be_present_in_the_bookmarks_tab() {
        Assert.assertTrue(
            bookmarkPage.isSavedVideoNotPresentInBookmarks(),
            "Bookmarked video is still present in the Bookmarks tab"
        );
        LogUtil.info("Bookmarked video is removed from the Bookmarks tab");
    }
}