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

    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    public static void clickOnLinkText(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }

    public static void clickOnId(String id){
        driver.findElement(By.id(id)).click();
    }

    public static void sendkeys(String id,String keys){
        driver.findElement(By.id(id)).sendKeys(keys);
    }

    @Test
    public void ebayregisterNewAccount() throws InterruptedException {

        //beginning
        setUpBrowser("Chrome","http://www.ebay.com");

        //inside the test

        //driver.findElement(By.xpath("//a[@href='https://reg.ebay.com/reg/PartialReg?ru=https%3A%2F%2Fwww.ebay.com%2F']")).click();
        clickOnLinkText("register");
        Thread.sleep(2000);
        sendkeys("firstname","Mohammad");
//        driver.findElement(By.id("firstname")).sendKeys("Mohammad");
//        driver.findElement(By.id("lastname")).sendKeys("Nazmuddin");
//        driver.findElement(By.id("Email")).sendKeys("abc123@gmail.com");
//        driver.findElement(By.id("password")).sendKeys("abc123");
        Thread.sleep(2000);
        clickOnId("showpassword");

        //ending
        closeBrowser();
    }
}
