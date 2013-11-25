package com.example.selenium.pages.wikipedia;

import com.example.selenium.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaMainPage extends Page {

    public WikipediaMainPage(WebDriver driver) {
        super(driver);
    }

    private String pageURL = "http://en.wikipedia.org/wiki/Main_Page";

    @FindBy(id = "p-logo")
    private WebElement wikiLogo;

    @FindBy(id = "mp-topbanner")
    private WebElement topBanner;

    @FindBy(xpath = "//*[@id='mp-topbanner']/tbody/tr/td[1]/table/tbody/tr/td/div[1]")
    private WebElement welcomeMessage;

    @FindBy(id = "searchInput")
    private WebElement searchField;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    public WebElement getWikiLogo() {
        return wikiLogo;
    }

    public WebElement getTopBanner() {
        return topBanner;
    }

    public WebElement getWelcomeMessage() {
        return welcomeMessage;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    @Override
    public void openPage() {
        driver.get(pageURL);
    }
}
