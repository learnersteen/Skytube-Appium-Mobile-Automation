package pages;

import org.openqa.selenium.By;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import io.appium.java_client.AppiumBy;
import utilities.LogUtil;

public class BookmarkPage extends BasePage {

    private By firstVideoOptions = AppiumBy.id("free.rm.skytube.oss:id/options_button");
    private By bookmarkOption = AppiumBy.xpath("//android.widget.TextView[@text='Bookmark']");
    
    private By bookmarksTab = AppiumBy.xpath("//*[contains(@text,'BOOKMARKS') or contains(@text,'Bookmarks')]");
     
    private By bookmarkedToast = AppiumBy.xpath("//android.widget.Toast[contains(@text,'Bookmarked')] | //*[contains(@text,'Bookmarked')]");

    // first video title from search results / bookmarks page
  
    private By firstVideoTitle = AppiumBy.xpath("(//android.widget.TextView[contains(@text,'Appium')])[2]");
    private By removeBookmarkOption = AppiumBy.xpath("//*[contains(@text,'Remove Bookmark') or contains(@text,'Unbookmark') or contains(@text,'Remove')]");
    private By allTextViews = AppiumBy.xpath("//android.widget.TextView");
    
    private String savedVideoTitle;

    public BookmarkPage() {
        super();
    }

  
    public void openFirstVideoOptions() {

        boolean isVisible = waitForVisibility(firstVideoOptions, 15);

        if (!isVisible) {
            throw new RuntimeException("First video options button not visible");
        }

        click(firstVideoOptions);
        LogUtil.info("Clicked on first video options menu");
    }

    
    public boolean isBookmarkOptionVisible() {
        return waitForVisibility(bookmarkOption, 5);
    }


    
    public String captureFirstVideoTitle() {
        boolean isVisible = waitForVisibility(firstVideoTitle, 10);

        if (!isVisible) {
            throw new RuntimeException("First video title is not visible");
        }

        savedVideoTitle = find(firstVideoTitle).getText();
        LogUtil.info("Captured first video title: " + savedVideoTitle);
        return savedVideoTitle;
    }
    
  
    public void clickBookmarkOption() {

        boolean isVisible = waitForVisibility(bookmarkOption, 10);

        if (!isVisible) {
            throw new RuntimeException("Bookmark option is not visible");
        }

        click(bookmarkOption);
        LogUtil.info("Clicked Bookmark option");
    }

    public void bookmarkFirstVideo() {
        captureFirstVideoTitle();
        openFirstVideoOptions();
        clickBookmarkOption();
        isBookmarkedToastDisplayed();
        LogUtil.info("User bookmarked first video");
    }


    public boolean isBookmarkedToastDisplayed() {
        try {
            return waitForVisibility(bookmarkedToast, 8);
        } catch (Exception e) {
            LogUtil.error("Bookmarked toast not displayed: " + e.getMessage());
            return false;
        }
    }


    
    public void clickBookmarksTab() {

        boolean isVisible = waitForVisibility(bookmarksTab, 10);

        if (!isVisible) {
            throw new RuntimeException("BOOKMARKS tab is not visible");
        }

        click(bookmarksTab);

        // WAIT for bookmarks page to load
        waitForVisibility(AppiumBy.xpath("//android.widget.TextView"), 10);

        LogUtil.info("Clicked BOOKMARKS tab");
    }

    
    
    public boolean isSavedVideoPresentInBookmarks() {

        if (savedVideoTitle == null || savedVideoTitle.trim().isEmpty()) {
            throw new RuntimeException("Saved video title is null or empty");
        }

        // Wait for bookmarks screen
        waitForVisibility(AppiumBy.xpath("//android.widget.TextView"), 10);

        for (int i = 0; i < driver.findElements(AppiumBy.xpath("//android.widget.TextView")).size(); i++) {

            String text = driver.findElements(AppiumBy.xpath("//android.widget.TextView")).get(i).getText();

            if (text != null && text.contains(savedVideoTitle.substring(0, 10))) {
                LogUtil.info("Matched bookmarked video: " + text);
                return true;
            }
        }

        LogUtil.error("Bookmarked video NOT found in list");
        return false;
    }
    
    public void goBackAfterBookmark() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        LogUtil.info("Pressed Back after bookmarking");
    }
    
    
    
    public void openBookmarkedVideoOptions() {

        boolean isVisible = waitForVisibility(firstVideoOptions, 10);

        if (!isVisible) {
            throw new RuntimeException("Bookmarked video options button is not visible");
        }

        click(firstVideoOptions);
        LogUtil.info("Clicked options menu for bookmarked video");
    }

    public void clickRemoveBookmarkOption() {

        boolean isVisible = waitForVisibility(removeBookmarkOption, 10);

        if (!isVisible) {
            throw new RuntimeException("Remove Bookmark / Unbookmark option is not visible");
        }

        click(removeBookmarkOption);
        LogUtil.info("Clicked Remove Bookmark option");
    }

    public void removeBookmarkedVideo() {
        openBookmarkedVideoOptions();
        clickRemoveBookmarkOption();
        LogUtil.info("Removed bookmarked video");
    }

    public boolean isSavedVideoNotPresentInBookmarks() {

        if (savedVideoTitle == null || savedVideoTitle.trim().isEmpty()) {
            throw new RuntimeException("Saved video title is null or empty");
        }

        waitForVisibility(allTextViews, 10);

        for (int i = 0; i < driver.findElements(allTextViews).size(); i++) {
            String text = driver.findElements(allTextViews).get(i).getText();

            if (text != null && text.contains(savedVideoTitle.substring(0, Math.min(10, savedVideoTitle.length())))) {
                LogUtil.error("Video still present in bookmarks: " + text);
                return false;
            }
        }

        LogUtil.info("Video is not present in bookmarks");
        return true;
    }
}


