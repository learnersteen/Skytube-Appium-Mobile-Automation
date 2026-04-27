package pages;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class DownloadPage extends BasePage {
	

	    // Three dots (more options) button on any video
	    private By threeDotMenu = By.xpath(
	    	    "(//android.widget.ImageButton[@resource-id='free.rm.skytube.oss:id/options_button'])[1]"
	    		);

	    // Download option in the context menu
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
	        List<WebElement> dots = driver.findElements(threeDotMenu);
	        if (!dots.isEmpty()) {
	            wait.until(ExpectedConditions.elementToBeClickable(dots.get(0))).click();
	        } else {
	            throw new RuntimeException("Three dot menu not found on any video");
	        }
	    }

	   
	    public boolean isDownloadOptionVisible() {
	        try {
	            return wait.until(ExpectedConditions
	                .visibilityOfElementLocated(downloadOption))
	                .isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	   
	    public void clickDownloadOption() {
	        wait.until(ExpectedConditions
	            .elementToBeClickable(downloadOption))
	            .click();
	        
	    }
	    public void clickDownloadVdie() {
	        wait.until(ExpectedConditions
		            .elementToBeClickable(downloadvideo))
		            .click();
	    }
	

	    // Get the actual error message text
	    public boolean isDownloadErrorMessageDisplayed() {
	        By[] locators = { downloadErrorMsg, downloadErrorMsg2 };
	        for (By locator : locators) {
	            try {
	                boolean found = wait.until(ExpectedConditions
	                    .visibilityOfElementLocated(locator))
	                    .isDisplayed();
	                if (found) return true;
	            } catch (Exception ignored) {}
	        }
	        return false;
	    }

	    public String getDownloadErrorMessageText() {
	        By[] locators = { downloadErrorMsg, downloadErrorMsg2 };
	        for (By locator : locators) {
	            try {
	                String text = wait.until(ExpectedConditions
	                    .visibilityOfElementLocated(locator))
	                    .getText();
	                if (text != null && !text.isEmpty()) return text;
	            } catch (Exception ignored) {}
	        }
	        return "No error message found";
	    }	}

