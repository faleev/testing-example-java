package com.example.selenium.wikipedia;

import com.example.selenium.common.Page;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(css = ".suggestions-results a div")
    private List<WebElement> suggestionsResultsValues;

    public boolean isWikiLogoDisplayed() {
        return wikiLogo.isDisplayed();
    }

    public WikipediaCommonPage inputInSearchField (String pattern) {
        searchField.clear();
        searchField.sendKeys(pattern);
        return this;
    }

    public WikipediaArticlePage searchArticleInSuggestionsList(String pattern, String title) {
        inputInSearchField(pattern);
        waitForElement(suggestionsResults);

        for(WebElement e : suggestionsResultsValues) {
            if (e.getText().equals(title)) {
                e.click();
                // In the Firefox browser, click on the element in the suggestions list does not lead to the redirection
                // to the target page, so additional click on the search button is used.
                //if (driver instanceof FirefoxDriver) {
                //    searchButton.click();
                //}
                // This behaviour is no longer relevant (Selenium 2.42.2), but this piece of code decided to leave here.
                return new WikipediaArticlePage(driver);
            }
        }
        throw new NoSuchElementException("Article title \"" + title + "\" was not found in the suggestions list.");
    }
}
