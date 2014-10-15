package com.example.selenium.common;

import com.example.utils.ProjectConfiguration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Page {

    protected WebDriver driver;
    protected String pageURL = "";

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(pageURL);
    }

    public void openPage(String url){
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElement(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAjaxCompletion() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        final JavascriptExecutor executor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> ajaxCompletion = new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver){
                return (Boolean) executor.executeScript("return jQuery.active == 0");
            }
        };
        wait.until(ajaxCompletion);
    }

    public void waitForAjaxCompletion(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        final JavascriptExecutor executor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> ajaxCompletion = new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver){
                return (Boolean) executor.executeScript("return jQuery.active == 0");
            }
        };
        wait.until(ajaxCompletion);
    }

    public static void takeScreenshot(WebDriver driver) {
        // Known issue: ChromeDriver2 take screenshot is not full page
        // (https://code.google.com/p/chromedriver/issues/detail?id=294)
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
