package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class PreferencePage extends BasePage {

	private By moreOptionsBtn = AppiumBy.accessibilityId("More options");
	private By allowButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Allow\")");
	private By backButton = AppiumBy.accessibilityId("Navigate up"); // Standard Android action bar ID

	public PreferencePage() {
		super();
	}

	private By getByText(String text) {
		return By.xpath("//android.widget.TextView[@text='" + text + "']");
	}

	// Fixes the "isMenuIconDisplayed() is undefined" error
	public boolean isMenuIconDisplayed() {
		return isDisplayed(moreOptionsBtn);
	}

	public void clickMenuIcon() {
		click(moreOptionsBtn);
	}

	// Fixes the "isMenuOptionVisible(String) is undefined" error
	public boolean isMenuOptionVisible(String optionName) {
		return isDisplayed(getByText(optionName));
	}

	public void clickMenuOption(String optionName) {
		click(getByText(optionName));
	}

	public void handlePermissionPopup() {
		if (isDisplayed(allowButton)) {
			click(allowButton);
		}
	}

	public boolean isCategoryVisible(String categoryName) {
		return isDisplayed(getByText(categoryName));
	}

	public void clickCategory(String categoryName) {
		click(getByText(categoryName));
	}

	public boolean isSubPageHeaderDisplayed(String expectedTitle) {
		return isDisplayed(getByText(expectedTitle));
	}

	public void clickBackButton() {
		if (isDisplayed(backButton)) {
			click(backButton);
		} else {

			driver.navigate().back();
		}
	}
}