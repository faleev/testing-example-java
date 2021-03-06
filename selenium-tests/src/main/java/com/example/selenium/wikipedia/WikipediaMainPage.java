package com.example.selenium.wikipedia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaMainPage extends WikipediaCommonPage {

    public WikipediaMainPage(WebDriver driver) {
        super(driver);
        super.pageURL = "http://en.wikipedia.org/wiki/Main_Page";
    }

    @FindBy(id = "mp-topbanner")
    private WebElement topBanner;

    @FindBy(xpath = "//*[@id='mp-topbanner']/tbody/tr/td[1]/table/tbody/tr/td/div[1]")
    private WebElement welcomeMessage;

    public String getWelcomeMessageText() {
        return welcomeMessage.getText();
    }

    public boolean isTopBannerDisplayed() {
        return topBanner.isDisplayed();
    }

}
