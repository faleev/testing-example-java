package com.example.selenium.pages.wikipedia;

import com.example.selenium.BasicTestCase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class WikipediaSearchTest extends BasicTestCase{

    private WikipediaMainPage mainWikipage;
    private WikipediaArticlePage articleWikipage;

    @BeforeClass
    public void setUp() {
        mainWikipage = PageFactory.initElements(driver, WikipediaMainPage.class);
        articleWikipage = PageFactory.initElements(driver, WikipediaArticlePage.class);
    }

    @DataProvider
    public Object[][] searchTesting() {
        return new Object[][]{
                {"selenium", "Selenium (software)", "Selenium is a portable software testing framework", "2.4 Selenium WebDriver"},
                {"java", "Java (programming language)", "Java is a computer programming language", "4.1 Hello world"},
        };
    }

    @Test(dataProvider = "searchTesting")
    public void testArticleSearchWithSuggestions(String pattern, String header, String firstPhrase, String chapter) throws InterruptedException {
        mainWikipage.openPage();
        mainWikipage.getSearchField().clear();
        mainWikipage.getSearchField().sendKeys(pattern);

        mainWikipage.waitForElement(mainWikipage.getSuggestionsResults());

        for(WebElement e : mainWikipage.getSuggestionsResultsValues()) {
            if (e.getAttribute("title").equals(header)) {
                e.click();
                break;
            }
        }

        assertNotEquals(driver.getCurrentUrl(), mainWikipage.getPageURL(), "Article not found in suggestions.");

        String articleHeader = articleWikipage.getArticleHeader().getText();
        assertEquals(articleHeader, header);

        String firstParagraph = articleWikipage.getArticleFirstParagraph().getText();
        assertTrue(firstParagraph.startsWith(firstPhrase));

        String chapterName = articleWikipage.getArticleContents().getText();
        assertTrue(chapterName.contains(chapter));
    }
}
