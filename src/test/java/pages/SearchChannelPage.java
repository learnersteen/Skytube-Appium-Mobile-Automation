package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
//import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import utilities.LogUtil;

public class SearchChannelPage extends BasePage  {
	
    private By okPopupButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"OK\")");
    private By searchIcon = AppiumBy.id("free.rm.skytube.oss:id/menu_search");
    private By searchField = AppiumBy.id("free.rm.skytube.oss:id/search_src_text");


    public SearchChannelPage() {
        super();
    }

    public void waitForHomeScreenToLoad() {
        waitForVisibility(searchIcon);
    }

    public void handlePopupIfPresent() {
        try {
        	WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        	WebElement okButton = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(okPopupButton)
                );
        	okButton.click();
        	LogUtil.info("Popup handled");
        } catch (Exception e) {
        	LogUtil.info("No popup displayed");
        }
    }
    
    public boolean isSearchIconDisplayed() {
        return isDisplayed(searchIcon);
    }
    
    public void clickSearchIcon() {
        click(searchIcon);
    }
    

    public void enterSearchText(String keyword) {
        click(searchField);
        type(searchField, keyword);
    }
    
    public void pressEnterKey() {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
//    
//    public int getMatchingResultsCount(String keyword) {
//        String xpath = "//android.widget.TextView[contains(@text,'" + keyword + "') or contains(@text,'" + keyword.toLowerCase() + "')]";
//        return driver.findElements(AppiumBy.xpath(xpath)).size();
//    }
    
    public boolean areSearchResultsDisplayed(String keyword) {
        try {
            String xpath = "//android.widget.TextView[contains(@text,'" + keyword + "') or contains(@text,'" + keyword.toLowerCase() + "')]";
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public boolean areSearchResultsDisplayed(String keyword) {
//        return getMatchingResultsCount(keyword) > 0;
//    }
    
       
    
}
















