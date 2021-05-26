package ebay;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class TestBase {

    public static WebDriver driver;
    private static final Logger logger = Logger.getLogger(TestBase.class);

    private static WebDriver setupCloudBrowser() throws MalformedURLException {
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
        return driver;
    }

    private static WebDriver setupLocalDriver(String os, String browserName){
        if (os.equalsIgnoreCase("windows")) {
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        } else {
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver();
            } else {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
            }
        }
        return driver;
    }

    @BeforeMethod
    public static void setUpBrowser() throws MalformedURLException {
        String browserName = "Chrome";
        String url = "https://www.ebay.com";
        String os = "windows";
        String platform = "local";

        if (platform.equalsIgnoreCase("local")) {
            driver = setupLocalDriver(os, browserName);
        } else {
            driver = setupCloudBrowser();
        }

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterMethod
    public static void closeBrowser() {
        waitFor(3);
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

        String actualUrl = driver.getCurrentUrl();
        logger.info("Browser open and ebay.com launched.");
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
