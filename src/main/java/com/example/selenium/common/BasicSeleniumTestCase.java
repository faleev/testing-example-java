package com.example.selenium.common;

import com.example.utils.ProjectConfiguration;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.URL;

@Listeners({TakeScreenshotOnFailureListener.class})
public class BasicSeleniumTestCase {

    protected static WebDriver driver;

    private void startFirefoxDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    private void startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", ProjectConfiguration.getProperty("chromedriver.path"));
        driver = new ChromeDriver();
    }

    private void startWebdriverOnSaucelabs(String browser, String version, Platform platform) throws Exception {
        String saucelabsUser = ProjectConfiguration.getProperty("saucelabs.user");
        String saucelabsKey = ProjectConfiguration.getProperty("saucelabs.key");
        String saucelabsURL = ProjectConfiguration.getProperty("saucelabs.url");
        URL saucelabsURI = new URL("http://" + saucelabsUser + ":" + saucelabsKey + "@" + saucelabsURL);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setVersion(version);
        capabilities.setPlatform(platform);
        //capabilities.setCapability("name", "my name"); // Pass custom options to SL report
        driver = new RemoteWebDriver(saucelabsURI, capabilities);
    }

    @BeforeClass
    @Parameters({"environment"})
    public void initializeWebDriver(@Optional("chrome-local") String environment) throws Exception {
        switch (environment.toLowerCase()) {
            case "firefox-local":
                startFirefoxDriver();
                break;

            case "chrome-local":
                startChromeDriver();
                break;

            case "saucelabs-firefox25-windows8":
                startWebdriverOnSaucelabs("firefox", "25", Platform.WIN8);
                break;

            case "saucelabs-safari6-mac-darvin":
                startWebdriverOnSaucelabs("safari", "5", Platform.MAC);
                break;

            default:
                startFirefoxDriver();
                break;
        }
    }

    @AfterClass(alwaysRun=true)
    public void quitWebDriver() throws Exception {
        if (driver != null) driver.quit();
    }
}
