package stepdefinitions;

import io.cucumber.datatable.DataTable; // Necessary for the category list
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.PreferencePage;
import java.util.List;

public class PreferencesSteps {

	private PreferencePage preferencesPage;

	public PreferencesSteps() {
		this.preferencesPage = new PreferencePage();
	}

	@Then("the three dots menu icon should be visible")
	public void the_three_dots_menu_icon_should_be_visible() {
		Assert.assertTrue(preferencesPage.isMenuIconDisplayed(), "Three dots menu is not visible!");
	}

	@When("the user clicks the three dots menu icon")
	public void the_user_clicks_the_three_dots_menu_icon() {
		preferencesPage.clickMenuIcon();
	}

	@Then("the {string} option should be displayed in the dropdown")
	public void the_option_should_be_displayed_in_the_dropdown(String optionName) {
		Assert.assertTrue(preferencesPage.isMenuOptionVisible(optionName), optionName + " was not found in dropdown!");
	}

	@When("the user clicks on the {string} option")
	public void the_user_clicks_on_the_option(String optionName) {
		preferencesPage.clickMenuOption(optionName);

		// Handle the SkyTube permission popup if "Preferences" was clicked
		if (optionName.equalsIgnoreCase("Preferences")) {
			preferencesPage.handlePermissionPopup();
		}
	}

	@Then("the following categories should be visible:")
	public void the_following_categories_should_be_visible(DataTable dataTable) {
		// Convert the Cucumber table into a Java List
		List<String> categories = dataTable.asList();

		for (String category : categories) {
			// Check visibility for each category
			boolean isVisible = preferencesPage.isCategoryVisible(category);
			Assert.assertTrue(isVisible, "Preference category '" + category + "' was not found!");
		}
	}

	// Keep this for individual category checks if needed
	@Then("the preference category {string} should be visible")
	public void the_preference_category_should_be_visible(String categoryName) {
		Assert.assertTrue(preferencesPage.isCategoryVisible(categoryName),
				"Category: " + categoryName + " is missing!");
	}

	@Then("the user verifies and returns from each sub-page:")
	public void the_user_verifies_and_returns_from_each_sub_page(io.cucumber.datatable.DataTable dataTable) {
		List<String> categories = dataTable.asList();
		for (String category : categories) {
			preferencesPage.clickCategory(category);
			boolean isLoaded = preferencesPage.isSubPageHeaderDisplayed(category);
			Assert.assertTrue(isLoaded, "Failed to load sub-page for: " + category);

			// 3. Navigate back to the Preferences list
			preferencesPage.clickBackButton();

			// 4. (Optional) Verify we are back on the main list
			Assert.assertTrue(preferencesPage.isCategoryVisible(category),
					"Did not return to Preferences list successfully after visiting " + category);
		}
	}

}