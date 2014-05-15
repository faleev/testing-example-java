package com.example.selenium.pages.wikipedia;

import com.example.selenium.BasicTestCase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WikipediaMainPageTest extends BasicTestCase {

    private WikipediaMainPage mainWikipage;

    @BeforeClass
    public void setUp() throws Exception {
        mainWikipage = new WikipediaMainPage(driver);
        mainWikipage.openPage();
    }

    @Test
    public void testThatWikiIsOpened() throws Exception {
        String title = mainWikipage.getPageTitle();
        assertEquals(title, "Wikipedia, the free encyclopedia");
    }

    @Test
    public void testThatUrlIsCorrect() throws Exception {
        String url = mainWikipage.getPageUrl();
        assertEquals(url, "http://en.wikipedia.org/wiki/Main_Page");
    }

    @Test
    public void testThatWikiLogoDisplayed() throws Exception {
        boolean state = mainWikipage.isWikiLogoDisplayed();
        assertTrue(state);
    }

    @Test
    public void testThatWikiWelcomeMessageIsPresent() throws Exception {
        String message = mainWikipage.getWelcomeMessageText();
        assertEquals(message, "Welcome to Wikipedia,");
    }

    @Test
    public void testThatTopBannerIsPresent() throws Exception {
        boolean state = mainWikipage.isTopBannerDisplayed();
        assertTrue(state);
    }
}
