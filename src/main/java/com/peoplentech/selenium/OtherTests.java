package com.peoplentech.selenium;

import ebay.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class OtherTests extends TestBaseOld {

    @Test
    public void userBeAbleToPerformDragNDrop() {

        setUpBrowser("Chrome", "https://www.ebay.com");
        WebElement source = driver.findElement(By.xpath("//li[@id='fourth'][2]"));
        WebElement destination = driver.findElement(By.id("amt8"));

        waitFor(2);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, destination).build().perform();

    }

    @Test
    public void userBeAbleToPerformIFrame() {

        setUpBrowser("Chrome", "https://www.ebay.com");
        JavascriptExecutor jSE = (JavascriptExecutor) driver;
        jSE.executeScript("window.scrollBy(0,1000);");
        // 3 ways name,id,index

        driver.switchTo().frame("frame2");
        waitFor(1);
        jSE.executeScript("window.scrollBy(0,1000);");
        jSE.executeScript("window.scrollBy(1,1000);");
        waitFor(2);

        driver.switchTo().defaultContent();
        waitFor(3);
    }

    @Test
    public void userBeAbleToHandlePopup() {

        setUpBrowser("Chrome", "http://demo.guru99.com/test/delete_customer.php");
        driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@name='submit']")).click();

        waitFor(2);
        //driver.switchTo().alert().dismiss();
        driver.switchTo().alert().accept();
        driver.switchTo().alert().accept();
        closeBrowser();
    }

    @Test
    public void validateUserCanHandleMultipleTabs() {
        setUpBrowser("chrome", "https://www.google.com/gmail/about/");
        Set<String> windowsBeforeOpeningNewTab = driver.getWindowHandles();
        System.out.println("Window1" + windowsBeforeOpeningNewTab);

        clickOnLinkText("Create an account");

        Set<String> windowsAfterOpeningNewTab = driver.getWindowHandles();
        System.out.println("Window2" + windowsAfterOpeningNewTab);
        String currentWindowHash = driver.getWindowHandle();

        for (String window : windowsAfterOpeningNewTab) {
            if (!window.equalsIgnoreCase(currentWindowHash)) {
                driver.switchTo().window(window);
            }
        }

        System.out.println(driver.getWindowHandle());

        driver.findElement(By.id("firstName")).sendKeys("Mohammad");
        driver.findElement(By.id("lastName")).sendKeys("Nazmuddin");

        clickOnXpath("(//div[@class='VfPpkd-RLmnJb'])[1]");

        WebElement element = driver.findElement(By.xpath("//div[@class='o6cuMc']"));
        String actual = element.getText();
        Assert.assertEquals(actual, "Choose Gmail address", "Expectation not full-filled.");


//        waitFor(3);
//        closeBrowser();
    }
}
