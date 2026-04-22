package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import utilities.LogUtil;

public class WatchedVideoCleanerPage extends BasePage {
	
	
	//three dots more options
	 private By threeDotsMoreOptions = AppiumBy.accessibilityId("More options");
	 private By watchedVideoCleaner = AppiumBy.androidUIAutomator("new UiSelector().text(\"Watched Video Cleaner\")");
	 
	 //watched video Cleaner popup 
	 private By watchedVideoCleanerPopup = AppiumBy.id("free.rm.skytube.oss:id/md_title");
	 
	 private By cancelButton = AppiumBy.id("free.rm.skytube.oss:id/md_buttonDefaultNegative");
	 private By cleanButton = AppiumBy.id("free.rm.skytube.oss:id/md_buttonDefaultPositive");
	 
	 private By removeWatchedDownloadsCheckbox = AppiumBy.id("free.rm.skytube.oss:id/clean_watched_downloads");
	 private By removeWatchedBookmarksCheckbox = AppiumBy.id("free.rm.skytube.oss:id/clean_watched_bookmarks");
	
	public WatchedVideoCleanerPage() {
	        super();
	    }
	
	 public void clickThreeDotsMoreOptions() {
	        click(threeDotsMoreOptions);
	        LogUtil.info("Clicked three dots More options menu");
	    }

	    public boolean isWatchedVideoCleanerDisplayed() {
	        boolean status = isDisplayed(watchedVideoCleaner);
	        LogUtil.info("Watched Video Cleaner option displayed: " + status);
	        return status;
	    }
	    
	    public void clickWatchedVideoCleanerOption() {
	        click(watchedVideoCleaner);
	        LogUtil.info("Clicked Watched Video Cleaner option");
	    }
	    
	    public boolean isCancelButtonDisplayed() {
	        return isDisplayed(cancelButton);
	    }

	    public boolean isCleanButtonDisplayed() {
	        return isDisplayed(cleanButton);
	    }
	    

	    public boolean isWatchedVideoCleanerPopupDisplayed() {
	        boolean status = isCancelButtonDisplayed() && isCleanButtonDisplayed();
	        LogUtil.info("Watched Video Cleaner popup displayed: " + status);
	        return status;
	    }
	    

	    public boolean isRemoveWatchedDownloadsChecked() {
	        String checked = find(removeWatchedDownloadsCheckbox).getAttribute("checked");
	        return Boolean.parseBoolean(checked);
	    }

	    public boolean isRemoveWatchedBookmarksChecked() {
	        String checked = find(removeWatchedBookmarksCheckbox).getAttribute("checked");
	        return Boolean.parseBoolean(checked);
	    }

	    public void uncheckRemoveWatchedDownloads() {
	        if (isRemoveWatchedDownloadsChecked()) {
	            click(removeWatchedDownloadsCheckbox);
	            LogUtil.info("Unchecked Remove watched downloads");
	        } else {
	            LogUtil.info("Remove watched downloads is already unchecked");
	        }
	    }

	    public void uncheckRemoveWatchedBookmarks() {
	        if (isRemoveWatchedBookmarksChecked()) {
	            click(removeWatchedBookmarksCheckbox);
	            LogUtil.info("Unchecked Remove watched bookmarks");
	        } else {
	            LogUtil.info("Remove watched bookmarks is already unchecked");
	        }
	    }

	    public void clickCancel() {
	        click(cancelButton);
	        LogUtil.info("Clicked Cancel button");
	    }

	    public void clickClean() {
	        click(cleanButton);
	        LogUtil.info("Clicked Clean button");
	    }
	}
	

	


