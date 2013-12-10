package com.example.selenium.pages.wikipedia;

import com.example.selenium.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class WikipediaCommonPage extends Page {

    public WikipediaCommonPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "p-logo")
    private WebElement wikiLogo;

    @FindBy(id = "searchInput")
    private WebElement searchField;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(css = ".suggestions-results")
    private WebElement suggestionsResults;

    public WebElement getWikiLogo() {
        return wikiLogo;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getSuggestionsResults() {
        return suggestionsResults;
    }
}
