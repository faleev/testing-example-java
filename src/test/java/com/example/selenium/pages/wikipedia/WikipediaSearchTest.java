package com.example.selenium.pages.wikipedia;

import com.example.selenium.BasicTestCase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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

        int i = 0;
        for(WebElement e : mainWikipage.getSuggestionsResultsValues()) {
            if (e.getText().equals(header)) {
                e.click();
                // In the Firefox browser click on the element in the suggestions list does not lead to the redirection
                // of the target page, so additional click on the search button is used.
                if (driver instanceof FirefoxDriver) {
                    mainWikipage.getSearchButton().click();
                }
                break;
            }
            // In case when article title not present in the suggestions list ('if' statement does not 'brake' the 'for'
            // loop), exception will be raised.
            i++;
            if (i == mainWikipage.getSuggestionsResultsValues().size()) {
                throw new AssertionError("Article title \"" + header + "\" was not found in the suggestions list.");
            }
        }

        String articleHeader = articleWikipage.getArticleHeader().getText();
        assertEquals(articleHeader, header);

        String firstParagraph = articleWikipage.getArticleFirstParagraph().getText();
        assertTrue(firstParagraph.startsWith(firstPhrase));

        String chapterName = articleWikipage.getArticleContents().getText();
        assertTrue(chapterName.contains(chapter));
    }
}
