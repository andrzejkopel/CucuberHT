Feature: Amazon Filter By Brand

  Scenario: Filter By Razer

    Given User is on "Main Page"

    When User choose "Headsets"
    And User filters by Brand "Razer"

    Then "Razer" brand items are displayed on page