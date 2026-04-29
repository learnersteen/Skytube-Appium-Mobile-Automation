package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.PreferencePage;
import utilities.ExcelReader; // Ensure this matches your package name
import java.util.List;

public class PreferencesSteps {

	private PreferencePage preferencesPage;
	private ExcelReader excelReader;

	public PreferencesSteps() {
		this.preferencesPage = new PreferencePage();
		this.excelReader = new ExcelReader();
	}

	@When("the user clicks the three dots menu icon")
	public void the_user_clicks_the_three_dots_menu_icon() {
		preferencesPage.clickMenuIcon();
	}

	@When("the user clicks on the {string} option")
	public void the_user_clicks_on_the_option(String optionName) {
		preferencesPage.clickMenuOption(optionName);
		if (optionName.equalsIgnoreCase("Preferences")) {
			preferencesPage.handlePermissionPopup();
		}
	}

	@Then("the user verifies all categories from Excel sheet {string}")
	public void verify_categories_from_excel(String sheetName) throws Exception {
		List<String> categories = excelReader.getColumnData(sheetName);

		for (String category : categories) {
			Assert.assertTrue(preferencesPage.isCategoryVisible(category),
					"Category '" + category + "' not found on screen!");

			preferencesPage.clickCategory(category);

			Assert.assertTrue(preferencesPage.isSubPageHeaderDisplayed(category),
					"Failed to load sub-page header for: " + category);

			preferencesPage.clickBackButton();

			System.out.println("Successfully verified: " + category);
		}
	}
}