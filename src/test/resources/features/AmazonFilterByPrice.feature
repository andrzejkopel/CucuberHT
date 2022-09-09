Feature: Amazon Filter By Price

  Scenario: Filter By $10 to $15

    Given User is on "Main Page"

    When User choose "Keyboards"
    And User filters by Price "$10 to $15"

    Then items with price in range 10 to 15 are displayed on page