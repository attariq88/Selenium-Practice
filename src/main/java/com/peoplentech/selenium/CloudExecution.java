package com.peoplentech.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CloudExecution {

    @Test
    public void validateUserCanExecuteTestsInBrowserstack() throws MalformedURLException, InterruptedException {
        String userName = "mohammadnazmuddi_TQSaDb";
        String accessKey = "Z6HWzYLEdPym9byxhjRr";

        String urlOfBrowserstack = "https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
        URL url = new URL(urlOfBrowserstack);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "Mojave");
        caps.setCapability("resolution", "1600x1200");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "89.0");
        caps.setCapability("os", "OS X");
        caps.setCapability("name", "cloud execution test : 01");

        WebDriver driver = new RemoteWebDriver(url, caps);

        driver.get("https://www.google.com/gmail/about/");
        Thread.sleep(5000);
        driver.quit();
    }
}
