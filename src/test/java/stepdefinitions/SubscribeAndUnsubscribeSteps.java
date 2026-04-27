package stepdefinitions;

import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SubscribeAndUnsubscribePage;

public class SubscribeAndUnsubscribeSteps {

	private SubscribeAndUnsubscribePage subscribePage;

	// Holds the channel name captured when we open the channel page for the first video
	private String currentChannelName;

	public SubscribeAndUnsubscribeSteps() {
		this.subscribePage = new SubscribeAndUnsubscribePage();
	}

	// ── Given ────────────────────────────────────────────────────────────────

	@Given("the user is on the {string} tab")
	public void the_user_is_on_the_tab(String tabName) {
		Assert.assertTrue(subscribePage.isCategoryVisible(tabName), "User is not on the '" + tabName + "' tab!");
	}

	// ── When ─────────────────────────────────────────────────────────────────

	@When("the user taps the {string} icon for {string}")
	public void the_user_taps_the_icon_for(String iconType, String videoTitle) {

		if (videoTitle.equalsIgnoreCase("first video")) {
			// capture the first visible video's channel/title
			currentChannelName = subscribePage.getFirstVideoTitle();
			videoTitle = currentChannelName;
		}

		if (iconType.equalsIgnoreCase("More Options")) {
			subscribePage.clickMoreOptionsForVideo(videoTitle);
		} else {
			Assert.fail("Unknown icon type: '" + iconType + "'");
		}
	}

	@When("the user selects {string} from the popup menu")
	public void the_user_selects_from_the_popup_menu(String option) {
		if (option.equalsIgnoreCase("Channel...")) {
			subscribePage.clickChannelMenuOption();
		} else if (option.equalsIgnoreCase("Subscribe")) {
			subscribePage.clickSubscribeFromPopup();
		} else if (option.equalsIgnoreCase("Unsubscribe")) {
			subscribePage.clickUnsubscribeFromPopup();
		} else {
			Assert.fail("Unknown popup option: '" + option + "'");
		}
	}

	@When("the user taps the {string} button")
	public void the_user_taps_the_button(String buttonText) {
		if (buttonText.equalsIgnoreCase("Subscribe")) {
			subscribePage.clickChannelSubscribeButton();
		} else if (buttonText.equalsIgnoreCase("Unsubscribe")) {
			subscribePage.clickChannelUnSubscribeButton();
		} else {
			Assert.fail("Unknown button: '" + buttonText + "'");
		}
	}

	@When("the user taps the {string} icon")
	public void the_user_taps_the_icon(String iconDescription) {
		if (iconDescription.equalsIgnoreCase("Hamburger Menu")) {
			subscribePage.openSideDrawer();
		} else {
			Assert.fail("Unknown icon: '" + iconDescription + "'");
		}
	}

	@When("the user closes the drawer")
	public void the_user_closes_the_drawer() {
		subscribePage.closeSideDrawer();
	}

	// ── Then ─────────────────────────────────────────────────────────────────

	@Then("the button text should change to {string}")
	public void the_button_text_should_change_to(String expectedText) {
		Assert.assertTrue(subscribePage.isSubscribedConfirmationVisible(),
				"Expected 'Subscribed' text not found after tapping Subscribe!");
	}

	@Then("the user is subscribed successfully")
	public void the_user_is_subscribed_successfully() {
		Assert.assertTrue(subscribePage.isSubscribedConfirmationVisible(),
				"Subscription confirmation 'Subscribed' not found on screen!");
	}

	@Then("the {string} drawer should open")
	public void the_drawer_should_open(String drawerName) {
		Assert.assertTrue(subscribePage.isDrawerOpen(), "'" + drawerName + "' drawer did not open!");
	}

	@Then("the channel {string} should be present in the subscription list")
	public void the_channel_should_be_present_in_the_subscription_list(String channelName) {
		if (channelName.equalsIgnoreCase("previously opened channel")) {
			channelName = currentChannelName;
		}
		Assert.assertTrue(subscribePage.isChannelInSubscriptionList(channelName),
				"Channel '" + channelName + "' not found in subscription list!");
	}

	@Then("the channel {string} should be removed in the subscription list")
	public void the_channel_should_be_removed_in_the_subscription_list(String channelName) {
		if (channelName.equalsIgnoreCase("previously opened channel")) {
			channelName = currentChannelName;
		}
		Assert.assertTrue(subscribePage.isChannelRemovedFromSubscriptionList(channelName),
				"Channel '" + channelName + "' was still found in the subscription list after unsubscribing!");
	}

	@Then("the user should see the {string} option in the popup for {string}")
	public void the_user_should_see_the_option_in_the_popup(String expectedOption, String videoTitle) {
		if (videoTitle.equalsIgnoreCase("first video")) {
			videoTitle = currentChannelName;
		}
		subscribePage.clickMoreOptionsForVideo(videoTitle);
		Assert.assertTrue(subscribePage.isTextVisible(expectedOption),
				"Popup did not show '" + expectedOption + "' — unsubscribe may not have worked!");
	}

	@Then("the channel {string} should NOT be present in the subscription list")
	public void the_channel_should_not_be_present(String channelName) {
		Assert.assertFalse(subscribePage.isChannelInSubscriptionList(channelName),
				"Channel '" + channelName + "' was still found in the list after unsubscribing!");
	}
}