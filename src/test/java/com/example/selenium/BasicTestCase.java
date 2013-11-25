package com.example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BasicTestCase {

    protected WebDriver driver;

    @BeforeClass
    public void initializeWebDriver() throws Exception {
        driver = new FirefoxDriver();
    }

    @AfterClass(alwaysRun=true)
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }
}
