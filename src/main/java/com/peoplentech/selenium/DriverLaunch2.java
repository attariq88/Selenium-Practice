package com.peoplentech.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class DriverLaunch2 {

    private static WebDriver driver;

    public static void setUpBrowser(String browserName,String url) {
        if (browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }else {
            System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.get(url);
    }

    public static void closeBrowser(){
        driver.quit();
    }

    public static void clickOnXpath(String path){
        driver.findElement(By.xpath(path)).click();
    }

    public static void clickOnLinkText(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }

    public static void clickOnId(String id){
        driver.findElement(By.id(id)).click();
    }

    public static void sendKeys(String id,String keys){
        driver.findElement(By.id(id)).sendKeys(keys);
    }

    public static void waitFor(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void navigateBack(){
        driver.navigate().back();
    }
    @Test
    public void ebayRegisterWithChrome() {
        setUpBrowser("Chrome","http://www.ebay.com");
        waitFor(1);
        sendKeys("gh-ac","Nike");
        navigateBack();
        waitFor(2);
        closeBrowser();

    }

    @Test
    public void ebayRegisterWithFirefox(){
        setUpBrowser("Firefox","http://www.ebay.com");
        closeBrowser();
    }
}
