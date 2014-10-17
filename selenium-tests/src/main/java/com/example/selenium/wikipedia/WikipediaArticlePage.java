package com.example.selenium.wikipedia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaArticlePage extends WikipediaCommonPage {

    public WikipediaArticlePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstHeading")
    private WebElement articleHeader;

    @FindBy(id = "toc")
    private WebElement articleContents;

    @FindBy(xpath = ".//*[@id='mw-content-text']/p[*]")
    private WebElement articleFirstParagraph;

    public String getArticleHeader() {
        return articleHeader.getText();
    }

    public String getArticleContents() {
        return articleContents.getText();
    }

    public String getArticleFirstParagraph() {
        return articleFirstParagraph.getText();
    }

}
