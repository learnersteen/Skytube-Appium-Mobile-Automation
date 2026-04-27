package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class PreferencePage extends BasePage {

    // Locators based on SkyTube UI
    private By moreOptionsBtn = AppiumBy.accessibilityId("More options");
    private By allowButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Allow\")");
    private By backButton = AppiumBy.accessibilityId("Navigate up"); 

    public PreferencePage() {
        super();
    }

    /**
     * Dynamic Locator Helper
     * Targets TextViews by their exact display text.
     */
    private By getByText(String text) {
        return By.xpath("//android.widget.TextView[@text='" + text + "']");
    } 

    // --- Global Menu Actions ---

    public boolean isMenuIconDisplayed() {
        return isDisplayed(moreOptionsBtn);
    }

    public void clickMenuIcon() {
        click(moreOptionsBtn);
    }

    public boolean isMenuOptionVisible(String optionName) {
        return isDisplayed(getByText(optionName));
    }

    public void clickMenuOption(String optionName) {
        click(getByText(optionName));
    }

    // --- Preference & Category Actions ---

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
            // Fallback for hardware back if the UI button isn't found
            driver.navigate().back();
        }
    }

}