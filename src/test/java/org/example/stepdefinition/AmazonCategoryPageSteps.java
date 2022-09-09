package org.example.stepdefinition;

import com.google.common.collect.Ordering;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.AmazonCategoryPage;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AmazonCategoryPageSteps {

    private final AmazonCategoryPage page = new AmazonCategoryPage();

    @When("User filters by Brand {string}")
    public void filterByBrand(String brandName) {
        page.clickLinkWithText("See more");
        page.filterByBrandName(brandName);
    }

    @Then("{string} brand items are displayed on page")
    public void validateByBrand(String brandName) {
        List<String> itemNames = page.getVisibleItemCaptions();
        assertEquals(itemNames.size(), itemNames.stream().map(String::toLowerCase)
                        .filter(item -> item.contains(brandName.toLowerCase())).count(),
                "Filter by brand returns unexpected items:\n"
                        + itemNames.stream().filter(item -> !item.contains(brandName.toLowerCase()))
                        .collect(joining("\n")));
    }

    @When("User filters by Price {string}")
    public void userFiltersByPrice(String priceFilter) {
        page.clickSpanWithText(priceFilter);
    }

    @Then("items with price in range {int} to {int} are displayed on page")
    public void itemsWithPriceInRangeAreDisplayedOnPage(int priceFrom, int priceTo) {
        assertTrue(page.getItemPrices().stream().allMatch(price -> price >= priceFrom && price <= priceTo),
                "Filter by price returns items with price out of boundary.");
    }

    @And("User sorts by {string}")
    public void userSortsByPrice(String ordering) throws InterruptedException {
        page.selectFromSortingDropdown(ordering);
    }

    @Then("items sorted by price are displayed on page")
    public void itemsSortedByPriceAreDisplayedOnPage() {
        List<Double> itemPrices = page.getItemPrices();
        assertTrue(Ordering.natural().isOrdered(itemPrices.subList(0, itemPrices.size() - 4)),//ignore sponsored items
                "Sorting by low to high price returns wrong sorting order.");
    }
}
