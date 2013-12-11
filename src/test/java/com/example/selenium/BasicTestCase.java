package com.example.selenium;

import com.example.utils.ProjectConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BasicTestCase {

    protected WebDriver driver;

    private void startFirefoxDriver() {
        driver = new FirefoxDriver();
    }

    private void startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", ProjectConfiguration.getProperty("chromedriver.path"));
        driver = new ChromeDriver();
    }

    @BeforeClass
    @Parameters({"browser"})
    public void initializeWebDriver(@Optional("chrome") String browser) throws Exception {
        switch (browser.toLowerCase()) {
            case "firefox":
                startFirefoxDriver();
                break;

            case "chrome":
                startChromeDriver();
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
