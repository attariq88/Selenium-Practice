package com.peoplentech.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.WeakHashMap;

public class OtherTests extends TestBase{

    @Test
    public void userBeAbleToPerformDragNDrop(){
        setUpBrowser("Chrome","http://demo.guru99.com/test/drag_drop.html");

        WebElement source = driver.findElement(By.xpath("//li[@id='fourth'][2]"));
        WebElement destination = driver.findElement(By.id("amt8"));

        waitFor(2);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source,destination).build().perform();


    }

    @Test
    public void userBeAbleToPerformIFrame(){
        setUpBrowser("Chrome","https://demoqa.com/frames");

        JavascriptExecutor jSE = (JavascriptExecutor)driver;
        jSE.executeScript("window.scrollBy(0,1000);");
        // 3 ways name,id,index

        driver.switchTo().frame("frame2");
        waitFor(1);
        jSE.executeScript("window.scrollBy(0,1000);");
        jSE.executeScript("window.scrollBy(1,1000);");
        waitFor(2);

        driver.switchTo().defaultContent();
        waitFor(3);
        closeBrowser();
    }
}
