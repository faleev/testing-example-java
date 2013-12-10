package com.example.selenium.pages.wikipedia;

import com.example.selenium.BasicTestCase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class WikipediaArticlePageTest extends BasicTestCase{

    private WikipediaMainPage mainWikipage;
    private WikipediaArticlePage articleWikipage;

    @BeforeClass
    public void setUp() {
        mainWikipage = PageFactory.initElements(driver, WikipediaMainPage.class);
        articleWikipage = PageFactory.initElements(driver, WikipediaArticlePage.class);
    }

    @Test
    public void testArticleSearchWithSuggestions() throws InterruptedException {
        mainWikipage.openPage();
        mainWikipage.getSearchField().clear();
        mainWikipage.getSearchField().sendKeys("Selenium");

        mainWikipage.waitForElement(mainWikipage.getSuggestionsResults());

        for(WebElement e : mainWikipage.getSuggestionsResultsValues()) {
            if (e.getAttribute("title").equals("Selenium (software)")) {
                e.click();
                break;
            }
        }

        assertNotEquals(driver.getCurrentUrl(), mainWikipage.getPageURL(), "Article not found in suggestions.");

        String articleHeader = articleWikipage.getArticleHeader().getText();
        assertEquals(articleHeader, "Selenium (software)");

        String firstParagraph = articleWikipage.getArticleFirstParagraph().getText();
        assertTrue(firstParagraph.startsWith("Selenium is a portable software testing framework for web applications."));

        String chapterName = articleWikipage.getArticleContents().getText();
        assertTrue(chapterName.contains("2.4 Selenium WebDriver"));
    }
}
