package com.example.selenium.pages.wikipedia;

import com.example.selenium.BasicTestCase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WikipediaArticlePageTest extends BasicTestCase{

    private WikipediaMainPage mainWikipage;
    private WikipediaArticlePage articleWikipage;

    @BeforeClass
    public void setUp() {
        mainWikipage = PageFactory.initElements(driver, WikipediaMainPage.class);
        articleWikipage = PageFactory.initElements(driver, WikipediaArticlePage.class);
    }

    @Test
    public void testArticleSearch() throws InterruptedException {

    }
}
