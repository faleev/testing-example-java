package com.example.selenium.jira;

import com.example.selenium.common.BasicSeleniumTestCase;
import com.example.utils.ProjectConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;

public class JiraIssueCRUDTest extends BasicSeleniumTestCase {

    private CommonPage commonPage;
    private String issueNumber;

    private String jiraBasicUrl = ProjectConfiguration.getProperty("jira.basic.url");
    private String username = ProjectConfiguration.getProperty("jira.user.name");
    private String password = ProjectConfiguration.getProperty("jira.user.password");

    // TODO: Could be moved into the DataProvider or another data source.
    private String uniq = new Date().toString();
    private String project = "A Test Project";
    private String type = "Bug";
    private String priority = "Minor";
    private String summary = "Test Issue Summary " + uniq;
    private String description = "This ticket has been created for testing purposes.";

    @BeforeClass
    public void setUp() throws Exception {
        commonPage = new CommonPage(driver);
        commonPage.openPage(jiraBasicUrl + "/secure/Dashboard.jspa");
        commonPage.loginToJira(username, password);
    }

    @Test
    public void testCreateIssue() throws Exception {
        CreateIssueDialog.setIssueFields(project, type, priority, summary, description);
        commonPage.createIssue();
        issueNumber = commonPage.getIssueNumber();

        assertEquals(commonPage.getSuccessMessageText(), "Issue " + issueNumber + " - " + summary + " has been successfully created.");
    }

    @Test(dependsOnMethods = { "testCreateIssue" })
    public void testUpdateIssue() throws Exception {
        String newDescription = "Updated description";
        String newType = "Improvement";

        IssuePage issuePage = new IssuePage(driver);

        issuePage.openPage(jiraBasicUrl + "/browse/" + issueNumber);
        issuePage.editIssue(newDescription, newType);

        assertEquals(issuePage.getIssueNumber(), issueNumber, "Issue number has been changed after update.");
        assertEquals(issuePage.getIssueDescription(), newDescription, "Description does not updated.");
        assertEquals(issuePage.getIssueType(), newType, "Issue Type does not updated.");
    }

    @Test(dependsOnMethods = { "testCreateIssue" })
    public void testSearchIssueByNumber() throws Exception {
        IssuePage issuePage = commonPage.searchByIssueNumber(issueNumber);
        assertEquals(issuePage.getIssueSummary(), summary);
    }
}
