package pages;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.LogUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class DownloadPage extends BasePage {
	

	    
	    private By threeDotMenu = By.xpath(
	    	    "(//android.widget.ImageButton[@resource-id='free.rm.skytube.oss:id/options_button'])[1]"
	    		);

	    
	    private By downloadOption = By.xpath(
	        "//android.widget.TextView[@text='Download']"
	    );
	    
	    private By downloadErrorMsg = By.xpath(
	    	    "//android.widget.TextView[contains(@text,'Could not find any video stream')]"
	    	);

	    	private By downloadErrorMsg2 = By.xpath(
	    	    "//android.widget.TextView[contains(@text,'RES_1080P')]"
	    	);

	        private By downloadErrorMsg3 = By.xpath(
	            "(//android.widget.ImageView[@resource-id=\"free.rm.skytube.oss:id/thumbnail_image_view\"])[3]"
	        );
	        
	        private By downloadvideo = AppiumBy.id("free.rm.skytube.oss:id/md_buttonDefaultPositive");
	        
	        
	        
	        public void clickThreeDots() {
	            try {
	                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	                List<WebElement> dots = driver.findElements(threeDotMenu);
	                if (!dots.isEmpty()) {
	                    shortWait.until(ExpectedConditions.elementToBeClickable(dots.get(0))).click();
	                    LogUtil.info("Three dot menu clicked successfully");
	                } else {
	                    LogUtil.info("Three dot menu not found on any video");
	                    throw new RuntimeException("Three dot menu not found on any video");
	                }
	            } catch (RuntimeException e) {
	                throw e;
	            } catch (Exception e) {
	                LogUtil.info("Failed to click three dot menu: " + e.getMessage());
	            }
	        }

	        public boolean isDownloadOptionVisible() {
	            try {
	                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	                boolean visible = shortWait.until(ExpectedConditions
	                    .visibilityOfElementLocated(downloadOption))
	                    .isDisplayed();
	                LogUtil.info("Download option is visible");
	                return visible;
	            } catch (Exception e) {
	                LogUtil.info("Download option not visible");
	                return false;
	            }
	        }

	        public void clickDownloadOption() {
	            try {
	                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	                shortWait.until(ExpectedConditions
	                    .elementToBeClickable(downloadOption))
	                    .click();
	                LogUtil.info("Download option clicked");
	            } catch (Exception e) {
	                LogUtil.info("Failed to click download option: " + e.getMessage());
	            }
	        }

	        public void clickDownloadVideo() {
	            try {
	                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	                shortWait.until(ExpectedConditions
	                    .elementToBeClickable(downloadvideo))
	                    .click();
	                LogUtil.info("Download video button clicked");
	            } catch (Exception e) {
	                LogUtil.info("Failed to click download video button: " + e.getMessage());
	            }
	        }

	        public boolean isDownloadErrorMessageDisplayed() {
	            By[] locators = {downloadErrorMsg, downloadErrorMsg2};
	            for (By locator : locators) {
	                try {
	                    WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	                    boolean found = shortWait.until(ExpectedConditions
	                        .visibilityOfElementLocated(locator))
	                        .isDisplayed();
	                    if (found) {
	                        LogUtil.info("Download error message displayed");
	                        return true;
	                    }
	                } catch (Exception e) {
	                    LogUtil.info("Error message not found for locator: " + locator);
	                }
	            }
	            return false;
	        }

	        public String getDownloadErrorMessageText() {
	            By[] locators = {downloadErrorMsg, downloadErrorMsg2};
	            for (By locator : locators) {
	                try {
	                    WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	                    String text = shortWait.until(ExpectedConditions
	                        .visibilityOfElementLocated(locator))
	                        .getText();
	                    if (text != null && !text.isEmpty()) {
	                        LogUtil.info("Download error message text: " + text);
	                        return text;
	                    }
	                } catch (Exception e) {
	                    LogUtil.info("Could not retrieve error message text for locator: " + locator);
	                }
	            }
	            LogUtil.info("No error message found");
	            return "No error message found";
	        }
	    }
	    

	