package com.example.selenium.pages.wikipedia;

import com.example.selenium.BasicTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WikipediaSearchTest extends BasicTestCase{

    @DataProvider
    public Object[][] searchTesting() {
        return new Object[][]{
                {"selenium", "Selenium (software)", "Selenium is a portable software testing framework", "2.4 Selenium WebDriver"},
                {"java", "Java (programming language)", "Java is a computer programming language", "4.1 Hello world"},
        };
    }

    @Test(dataProvider = "searchTesting")
    public void testArticleSearchWithSuggestions(String pattern, String title, String firstPhrase, String chapter) throws InterruptedException {
        WikipediaMainPage mainWikipage = new WikipediaMainPage(driver);
        mainWikipage.openPage();

        WikipediaArticlePage articleWikipage = mainWikipage.searchArticleInSuggestionsList(pattern, title);

        String articleHeader = articleWikipage.getArticleHeader();
        assertEquals(articleHeader, title);

        String firstParagraph = articleWikipage.getArticleFirstParagraph();
        assertTrue(firstParagraph.startsWith(firstPhrase));

        String chapterName = articleWikipage.getArticleContents();
        assertTrue(chapterName.contains(chapter));
    }
}
