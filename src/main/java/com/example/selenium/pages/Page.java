package com.example.selenium.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {

    protected WebDriver driver;
    protected String pageURL = "";

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(pageURL);
    }

}
