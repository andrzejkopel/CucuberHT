Feature: Amazon Sorting By Price

  Scenario: Sorting By Low to High

    Given User is on "Main Page"

    When User choose "Chairs"
    And User sorts by "Price: Low to High"

    Then items sorted by price are displayed on page