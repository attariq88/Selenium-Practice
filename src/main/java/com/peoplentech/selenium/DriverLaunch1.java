package com.peoplentech.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverLaunch1 {
    public static void main(String[] args) throws InterruptedException {

        //setup the environment property
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        // create object of chrome drive
        WebDriver driver = new ChromeDriver();

        // open the browser
        driver.get("http://www.google.com");

        //search for selenium books
        driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("Selenium books");

        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@aria-label='Google Search']")).click();

        Thread.sleep(5000);

        driver.quit();
    }
}
