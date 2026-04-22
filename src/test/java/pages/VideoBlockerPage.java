package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import utilities.LogUtil;

public class VideoBlockerPage extends BasePage {
	
	//shield icon 
	 private By shieldIcon = AppiumBy.id("free.rm.skytube.oss:id/menu_badge_icon");
	
	 //popup	
	 private By videoBlockerPopupTitle = AppiumBy.id("free.rm.skytube.oss:id/md_title");
	 private By videoBlockerPopupMessage = AppiumBy.id("free.rm.skytube.oss:id/md_content");
	    
	 //buttons 
	 private By cancelBtn = AppiumBy.id("free.rm.skytube.oss:id/md_buttonDefaultNegative");
	 private By setupBtn = AppiumBy.id("free.rm.skytube.oss:id/md_buttonDefaultPositive");

	//preferences page 
	private By preferencesPageTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Video Blocker\")");	
	private By videoBlockerCheckbox = AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"])[1]");
	
	
	// notification popup
	private By dontAllowButton = AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button");
	
	 public VideoBlockerPage() {
	        super();
	    }
	 
	    public boolean isShieldIconDisplayed() {
	        boolean displayed = waitForVisibility(shieldIcon, 10);
	        LogUtil.info("Video blocker shield icon visible: " + displayed);
	        return displayed;
	    }

	    public void clickShieldIcon() {
	        click(shieldIcon);
	        LogUtil.info("Clicked video blocker shield icon");
	    }
	    
	    public void clickCancel() {
	        click(cancelBtn);
	        LogUtil.info("Clicked CANCEL on Video Blocker popup");
	    }


	    public void clickSetUp() {
	        click(setupBtn);
	        LogUtil.info("Clicked SET UP on Video Blocker popup");
	        handleNotificationPopupIfPresent();
	    }
	    
	    public void handleNotificationPopupIfPresent() {
	        try {
	            if (waitForVisibility(dontAllowButton, 5)) {
	                click(dontAllowButton);
	                LogUtil.info("Clicked Don't allow on notification popup");
	            }
	        } catch (Exception e) {
	            LogUtil.info("Notification popup not displayed");
	        }
	    }

	    public boolean isVideoBlockerPopupDisplayed() {
	        boolean titleVisible = waitForVisibility(videoBlockerPopupTitle, 10);
	        boolean messageVisible = waitForVisibility(videoBlockerPopupMessage, 10);
	        LogUtil.info("Video blocker popup displayed");
	        return titleVisible && messageVisible;
	    }
	    
	    public boolean isPopupClosed() {
	        boolean closed = !isDisplayed(videoBlockerPopupTitle);
	        LogUtil.info("Video blocker popup closed: " + closed);
	        return closed;
	    }

  
	    public boolean isPreferencesPageDisplayed() {
	        boolean displayed = waitForVisibility(preferencesPageTitle, 15);
	        LogUtil.info("Preferences page displayed: " + displayed);
	        return displayed;
	    }
	    
	    public boolean isVideoBlockerChecked() {
	        waitForVisibility(videoBlockerCheckbox, 10);
	        WebElement checkbox = driver.findElement(videoBlockerCheckbox);
	        boolean checked = checkbox.getAttribute("checked").equalsIgnoreCase("true");
	        LogUtil.info("Video Blocker checkbox checked state: " + checked);
	        return checked;
	    }   
	       

}
