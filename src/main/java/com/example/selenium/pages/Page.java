package com.example.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}
