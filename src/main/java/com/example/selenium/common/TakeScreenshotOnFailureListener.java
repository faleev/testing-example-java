package com.example.selenium.common;

import com.example.utils.ProjectConfiguration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.selenium.common.BasicSeleniumTestCase.driver;

public class TakeScreenshotOnFailureListener extends TestListenerAdapter {

    public void onTestFailure(ITestResult tr) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String screenshotsDirictory = ProjectConfiguration.getProperty("screenshots.directory.location") + "/screenshots";
        new File(screenshotsDirictory).mkdirs();
        String screenshotFilename = dateFormat.format(new Date()) + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(screenshotsDirictory + "/" + screenshotFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reporter.log("<a href=\"../screenshots/" + screenshotFilename + "\"><img src=\"../screenshots/" + screenshotFilename + "\" alt=\"Screenshot\" width=\"25%\" height=\"25%\">");
    }
}
