package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonMainPage extends BasePage {

    public void open() {
        webDriver.get("http://amazon.com");
        webDriver.manage().window().maximize();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//h2[text()='Gaming accessories']")));
    }

    public void clickTile(String tileLabel) {
        webDriver.findElement(By.xpath(String.format("//a[@aria-label='%s']", tileLabel))).click();
    }
}
