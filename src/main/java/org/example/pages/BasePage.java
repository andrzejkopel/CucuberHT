package org.example.pages;

import io.cucumber.java.AfterAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    public static WebDriver webDriver;
    public static WebDriverWait webDriverWait;

    protected BasePage() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            PageFactory.initElements(webDriver, this);
        }
    }

    @AfterAll
    public void closeBrowser() {
        webDriver.close();
        webDriver.quit();
    }
}
