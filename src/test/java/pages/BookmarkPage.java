package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import utilities.LogUtil;

public class BookmarkPage extends BasePage {

    private By threeDotsMenu = AppiumBy.id("free.rm.skytube.oss:id/options_button");

    private By bookmarkOption = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Bookmark\")");

    private By unbookmarkOption = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Unbookmark\")");

    private By bookmarksTab = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Bookmarks\")");

    private By bookmarkMessage = AppiumBy.xpath("//*[contains(@text,'Bookmark')]");
    private By bookmarkRemovedMessage = AppiumBy.xpath("//*[contains(@text,'removed')]");

    public BookmarkPage() {
        super();
    }

    public void clickThreeDots() {
        click(threeDotsMenu);
        LogUtil.info("Clicked three dots menu");
    }

    public boolean isBookmarkOptionDisplayed() {
        return isDisplayed(bookmarkOption);
    }

    public void clickBookmarkOption() {
        click(bookmarkOption);
        LogUtil.info("Clicked Bookmark option");
    }

    public boolean isBookmarkMessageDisplayed() {
        return waitForVisibility(bookmarkMessage, 3);
    }

    public void clickBookmarksTab() {
        click(bookmarksTab);
        LogUtil.info("Clicked Bookmarks tab");
    }

    public boolean isVideoDisplayedInBookmarks(String videoTitle) {
        By videoTitleLocator = AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"" + videoTitle + "\")");
        return isDisplayed(videoTitleLocator);
    }

    public boolean isUnbookmarkOptionDisplayed() {
        return isDisplayed(unbookmarkOption);
    }

    public void clickUnbookmarkOption() {
        click(unbookmarkOption);
        LogUtil.info("Clicked Unbookmark option");
    }

    public boolean isBookmarkRemovedMessageDisplayed() {
        return waitForVisibility(bookmarkRemovedMessage, 3);
    }
}