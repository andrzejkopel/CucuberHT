package org.example.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.pages.AmazonMainPage;

public class HomePageSteps {

    private final AmazonMainPage page = new AmazonMainPage();

    @Given("User is on {string}")
    public void userIsOnPage(String pageName) {
        page.open();
    }

    @When("User choose {string}")
    public void clickTile(String tileLabel) {
        page.clickTile(tileLabel);
    }
}
