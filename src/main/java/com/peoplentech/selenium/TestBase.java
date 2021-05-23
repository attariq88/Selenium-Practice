package com.peoplentech.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestBase {

    public static WebDriver driver;
    private static Logger logger = Logger.getLogger(TestBase.class);

    public static void setUpBrowser(String browserName, String url) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    public static void closeBrowser() {
        driver.quit();
    }

    public static void clickOnXpath(String path) {
        driver.findElement(By.xpath(path)).click();
    }

    public static void clickOnLinkText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public static void clickOnId(String id) {
        driver.findElement(By.id(id)).click();
    }

    public static void sendKeys(String id, String keys) {
        driver.findElement(By.id(id)).sendKeys(keys);
    }

    public static void waitFor(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void typeOnXpath(String xpath, String data) {
        driver.findElement(By.xpath(xpath)).sendKeys(data);
    }

    public static void typeOnId(String id, String data) {
        driver.findElement(By.id(id)).sendKeys(data);
    }

    public static void navigateBack() {
        driver.navigate().back();
    }


    @Test
    public void validateEbayUrl() {
        setUpBrowser("Chrome", "http://www.ebay.com");
        String actualUrl = driver.getCurrentUrl();
        logger.info("Browser opend and ebay.com launched.");
        String expectedUrl = "https://www.ebay.com/";

        Assert.assertEquals(actualUrl, expectedUrl, "URL didn't match");
        logger.info("Ebay.com url validated.");

        WebElement ebayLogo = driver.findElement(By.id("gh-l-h1"));
        boolean result = ebayLogo.isDisplayed();
        //Assert.assertEquals(ebayLogo,true,"Not displayed");
        Assert.assertTrue(result, "Not displayed");
        logger.info("Displaying logo validated.");

        typeOnId("gh-ac", "Nike");
        logger.info("Typing works");

        clickOnId("gh-btn");
        logger.info("Button clicked");

        WebElement result1 = driver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']"));

        String log = result1.getText();
        Assert.assertTrue(log.contains("Nike"), "log didn't contain nike");
        logger.info(log + " has been displayed.");
    }
}
