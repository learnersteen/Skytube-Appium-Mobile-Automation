package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumBy;

public class SubscribeAndUnsubscribePage extends BasePage {

	private final By optionsButton = AppiumBy
			.androidUIAutomator("new UiSelector().resourceId(\"free.rm.skytube.oss:id/options_button\").instance(0)");

	private final By channelMenuItem = AppiumBy.androidUIAutomator("new UiSelector().text(\"Channel...\")");

	private final By subscribeMenuItem = AppiumBy.androidUIAutomator("new UiSelector().text(\"Subscribe\")");

	private final By unsubscribeMenuItem = AppiumBy.androidUIAutomator("new UiSelector().text(\"Unsubscribe\")");

	private final By subscribedText = By.xpath("//*[@text='Subscribed']");

	private final By subDrawerTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Subscriptions\")");

	private final By hamburgerMenuToolbar = By
			.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer'"
					+ " or @content-desc='Navigate up'" + " or @content-desc='Open drawer']");
	private final By subscribeBtn = AppiumBy.androidUIAutomator("new UiSelector().text(\"Subscribe\")");

	private By subscribeToggle = AppiumBy.id("free.rm.skytube.extra:id/channel_subscribe_button");

	private By unsubscribeOption = AppiumBy.androidUIAutomator("new UiSelector().text(\"Unsubscribe\")");

	public SubscribeAndUnsubscribePage() {
		super();
	}

	private By getByText(String text) {
		return By.xpath("//android.widget.TextView[@text='" + text + "']");
	}

	private By getByAnyText(String text) {
		return By.xpath("//*[@text='" + text + "']");
	}

	public boolean isCategoryVisible(String text) {
		return isDisplayed(getByText(text));
	}

	public boolean isTextVisible(String text) {
		return isDisplayed(getByAnyText(text));
	}

	public boolean isDrawerOpen() {
		return isDisplayed(subDrawerTitle);
	}

	public void clickMoreOptionsForVideo(String videoTitle) {
		click(optionsButton);
	}

	public void clickChannelMenuOption() {
		click(channelMenuItem);
	}

	public void clickChannelSubscribeButton() {
		click(subscribeBtn);
	}

	public void clickChannelUnSubscribeButton() {
		click(unsubscribeOption);
	}

	public void clickSubscribeFromPopup() {
		click(subscribeMenuItem);
	}

	public void clickUnsubscribeFromPopup() {
		click(unsubscribeMenuItem);
	}

	public void openSideDrawer() {
		try {
			click(hamburgerMenuToolbar);
			System.out.println("[Page] Drawer opened via toolbar ✅");
		} catch (Exception e) {
			System.out.println("[Page] Toolbar failed, trying first ImageButton");
			click(By.xpath("(//android.widget.ImageButton)[1]"));
		}
	}

	public void closeSideDrawer() {
		driver.navigate().back();
		System.out.println("[Page] Drawer closed via back button ✅");
	}

	public boolean isSubscribedConfirmationVisible() {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions.presenceOfElementLocated(subscribedText));
			System.out.println("[Page] 'Subscribed' confirmed ✅");
			return true;
		} catch (Exception e) {
			System.out.println("[Page] 'Subscribed' not found: " + e.getMessage());
			return false;
		}
	}

	public boolean isChannelInSubscriptionList(String channelName) {
		// printAllTextOnScreen();

		// Direct check — any widget type
		if (isDisplayed(By.xpath("//*[@text='" + channelName + "']"))) {
			System.out.println("[Page] '" + channelName + "' found directly ✅");
			return true;
		}

		// Scroll to find if below fold
		try {
			By scrollToChannel = AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
					+ ".scrollIntoView(new UiSelector().text(\"" + channelName + "\"))");
			driver.findElement(scrollToChannel);
			System.out.println("[Page] '" + channelName + "' found after scroll ✅");
			return true;
		} catch (Exception e) {
			System.out.println("[Page] '" + channelName + "' not found in list");
			return false;
		}
	}

	public boolean isChannelRemovedFromSubscriptionList(String channelName) {

		openSideDrawer();

		By channelLocator = AppiumBy.androidUIAutomator(
				"new UiSelector()" + ".resourceId(\"free.rm.skytube.oss:id/sub_channel_name_text_view\")" + ".text(\""
						+ channelName + "\")");

		int count = driver.findElements(channelLocator).size();

		if (count == 0) {
			System.out.println("[Page] Channel removed successfully ✅");
			return true;
		} else {
			System.out.println("[Page] Channel STILL EXISTS ❌");
			return false;
		}
	}

}