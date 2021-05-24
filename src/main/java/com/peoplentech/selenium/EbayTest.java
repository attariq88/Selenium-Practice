package com.peoplentech.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EbayTest extends TestBase {
    private static Logger logger = Logger.getLogger(EbayTest.class);

    @Test
    public void validateUserCanSearchForItemsInEbay() {
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

    @Test
    public void validateUserBeingAbleToChooseOptionFromSearchDropdown() {
        setUpBrowser("chrome", "http://www.ebay.com");

        WebElement categoryDropdown = driver.findElement(By.id("gh-cat"));
        Select select = new Select(categoryDropdown);
        select.selectByVisibleText("Travel");
        clickOnId("gh-btn");

        //closeBrowser();

    }

    @Test
    public void validateUserBeingAbleToChooseOptionWithMouseHovering() {
        setUpBrowser("Chrome", "https://www.ebay.com");

        WebElement motors = driver.findElement(By.linkText("Motors"));
        Actions actions = new Actions(driver);
        actions.moveToElement(motors).build().perform();
        clickOnLinkText("Classics");
    }

    @Test
    public void userBeAbleToScrollDown(){
        setUpBrowser("chrome","http://www.ebay.com");

        waitFor(2);
        JavascriptExecutor jSE = (JavascriptExecutor) driver;
        jSE.executeScript("window.scrollBy(0,1000)");
    }

    @Test
    public void userBeAbleToScrollDownToElement(){
        setUpBrowser("Chrome","http://www.ebay.com");

        WebElement element = driver.findElement(By.linkText("Registration"));
        waitFor(2);
        JavascriptExecutor jSE = (JavascriptExecutor)driver;
        jSE.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    @Test
    public void userBeAbleTohandleScrollDownElements(){
        setUpBrowser("Chrome","https://www.ebay.com");

        List<WebElement> dropdownList = driver.findElements(By.xpath("//select[@id='gh-cat']/option"));
        System.out.println(dropdownList.size());

        for (int i = 0; i < dropdownList.size(); i++) {
//            System.out.println(dropdownList.get(i).getText());
            System.out.println(dropdownList.get(i).getAttribute("value"));
        }
    }
}
