package com.example.selenium.common;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static com.example.selenium.common.BasicSeleniumTestCase.driver;

public class TakeScreenshotOnFailureListener extends TestListenerAdapter {

    public void onTestFailure(ITestResult tr) {
        Page.takeScreenshot(driver);
    }
}
