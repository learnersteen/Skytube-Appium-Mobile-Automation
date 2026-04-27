Feature: Subscribe and Unsubscribe Channel

  Background:
    Given The SkyTube app is launched

  Scenario: User can subscribe and then unsubscribe from a channel

    # ---------- PART 1: SUBSCRIBE ----------
    Given the user is on the "TRENDING (US)" tab
    When the user taps the "More Options" icon for "first video"
    And the user selects "Channel..." from the popup menu
    And the user taps the "Subscribe" button
    Then the button text should change to "Subscribed"

    # ---------- PART 2: VERIFY ----------
    When the user taps the "Hamburger Menu" icon
    Then the "Subscriptions" drawer should open
    And the channel "previously opened channel" should be present in the subscription list
    When the user closes the drawer

    # ---------- PART 3: UNSUBSCRIBE ----------
    When the user taps the "More Options" icon for "first video"
    And the user selects "Channel..." from the popup menu
    And the user taps the "Unsubscribe" button
    When the user taps the "Hamburger Menu" icon
    Then the "Subscriptions" drawer should open
    Then the channel "previously opened channel" should be removed in the subscription list