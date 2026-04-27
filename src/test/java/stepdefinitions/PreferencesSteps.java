package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
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

    // --- Global Menu & Preferences Steps ---

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
        if (optionName.equalsIgnoreCase("Preferences") || optionName.equalsIgnoreCase("Settings")) {
            preferencesPage.handlePermissionPopup();
        }
    }

    @Then("the following categories should be visible:")
    public void the_following_categories_should_be_visible(DataTable dataTable) {
        List<String> categories = dataTable.asList();
        for (String category : categories) {
            Assert.assertTrue(preferencesPage.isCategoryVisible(category), "Category '" + category + "' not found!");
        }
    }

    @Then("the user verifies and returns from each sub-page:")
    public void the_user_verifies_and_returns_from_each_sub_page(DataTable dataTable) {
        List<String> categories = dataTable.asList();
        for (String category : categories) {
            preferencesPage.clickCategory(category);
            Assert.assertTrue(preferencesPage.isSubPageHeaderDisplayed(category), "Failed to load sub-page: " + category);
            preferencesPage.clickBackButton();
        }
    }
}

