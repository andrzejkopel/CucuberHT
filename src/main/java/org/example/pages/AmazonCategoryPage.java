package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AmazonCategoryPage extends BasePage {

    private static final String ITEM_CAPTIONS_XPATH
            = "//div[contains(@class, 's-main-slot s-result-list')]" +
            "//h2//a//span[@class='a-size-medium a-color-base a-text-normal']";
    private static final String RESULTS_TABLE_XPATH = "//span[@data-component-type='s-search-results']";
    private static final String ITEM_PRICES_XPATH =
            "//div[@data-component-type='s-search-result']//span[@class='a-price']/span[@class='a-offscreen']";

    public void clickLinkWithText(String linkText) {
        webDriver.findElement(By.linkText(linkText)).click();
    }

    public void filterByBrandName(String brandName) {
        webDriver.findElement(By.xpath(String.format("//li[contains(@id, '%s')]//a", brandName))).click();
    }

    public List<String> getVisibleItemCaptions() {
        return webDriverWait.until(visibilityOfAllElementsLocatedBy(By.xpath(ITEM_CAPTIONS_XPATH)))
                .stream().map(WebElement::getText).collect(toList());
    }

    public void clickSpanWithText(String spanText) {
        webDriver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]", spanText))).click();
    }

    public List<Double> getItemPrices() {
        return webDriverWait.until(visibilityOfElementLocated(
                By.xpath(RESULTS_TABLE_XPATH)))
                .findElements(By.xpath(ITEM_PRICES_XPATH))
                .stream().map(priceElement -> priceElement.getAttribute("textContent")).map(price -> price.substring(1))
                .mapToDouble(Double::valueOf).boxed().collect(toList());
    }

    public void selectFromSortingDropdown(String ordering) throws InterruptedException {
        WebElement dropDownButton = webDriverWait.until(
                visibilityOfElementLocated(By.xpath("//span[@class='a-dropdown-prompt']")));
        dropDownButton.click();
        WebElement lowToHighPriceDropdownLink = webDriverWait.until(visibilityOfElementLocated(By.linkText(ordering)));
        lowToHighPriceDropdownLink.click();
        Thread.sleep(1000);
    }
}
