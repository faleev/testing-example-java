package com.example.selenium.pages.wikipedia;

import com.example.selenium.BasicTestCase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WikipediaMainPageTest extends BasicTestCase {

    private WikipediaMainPage mainWikipage;

    @BeforeClass
    public void setUp() throws Exception {
        mainWikipage = PageFactory.initElements(driver, WikipediaMainPage.class);
        mainWikipage.openPage();
    }

    @Test
    public void testThatWikiIsOpened() throws Exception {
        String title = driver.getTitle();
        assertEquals(title, "Wikipedia, the free encyclopedia");
    }

    @Test
    public void testThatUrlIsCorrect() throws Exception {
        String url = driver.getCurrentUrl();
        assertEquals(url, "http://en.wikipedia.org/wiki/Main_Page");
    }

    @Test
    public void testThatWikiLogoDisplayed() throws Exception {
        boolean state = mainWikipage.getWikiLogo().isDisplayed();
        assertTrue(state);
    }

    @Test
    public void testThatWikiWelcomeMessageIsPresent() throws Exception {
        String message = mainWikipage.getWelcomeMessage().getText();
        assertEquals(message, "Welcome to Wikipedia,");
    }

    @Test
    public void testThatTopBannerIsPresent() throws Exception {
        boolean state = mainWikipage.getTopBanner().isDisplayed();
        assertTrue(state);
    }
}
