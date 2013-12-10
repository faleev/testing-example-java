package com.example.selenium.pages.wikipedia;

import com.example.selenium.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaArticlePage extends Page {

    public WikipediaArticlePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstHeading")
    private WebElement articleHeader;

    @FindBy(id = "toc")
    private WebElement articleContents;

    @FindBy(xpath = ".//*[@id='mw-content-text']/p[1]")
    private WebElement articleFirstParagraph;

    public WebElement getArticleHeader() {
        return articleHeader;
    }

    public WebElement getArticleContents() {
        return articleContents;
    }

    public WebElement getArticleFirstParagraph() {
        return articleFirstParagraph;
    }

}
