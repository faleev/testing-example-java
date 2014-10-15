package com.example.selenium.pages.jira;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssuePage extends CommonPage {

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "key-val")
    private WebElement issueNumber;

    @FindBy(id = "summary-val")
    private WebElement issueSummary;

    @FindBy(id = "type-val")
    private WebElement issueType;

    @FindBy(id = "priority-val")
    private WebElement issuePriority;

    @FindBy(id = "resolution-val")
    private WebElement issueResolution;

    @FindBy(id = "edit-issue")
    private WebElement editIssueButton;

    @FindBy(id = "comment-issue")
    private WebElement commentIssueButton;

    @FindBy(id = "action_id_2")
    private WebElement resolveIssueButton;

    @FindBy(id = "action_id_2")
    private WebElement closeIssueButton;

    @FindBy(css = ".user-content-block > p")
    private WebElement issueDescription;

    @FindBy(css = ".aui-page-header-main>h1")
    private WebElement issueNotFoundMessage;

    public String getIssueNumber() {
        return issueNumber.getText();
    }

    public String getIssueSummary() {
        return issueSummary.getText();
    }

    public String getIssueType() {
        return issueType.getText();
    }

    public String getIssuePriority() {
        return issuePriority.getText();
    }

    public String getIssueResolution() {
        return issueResolution.getText();
    }

    public String getIssueDescription() {
        return issueDescription.getText();
    }

    public String getIssueNotFoundMessage() {
        return issueNotFoundMessage.getText();
    }

    public IssuePage editIssue(String description, String type) {
        waitForElement(editIssueButton);
        editIssueButton.click();
        EditIssueDialog editIssueDialog = new EditIssueDialog(driver);
        editIssueDialog.fillingEditIssueForm(description, type);
        editIssueDialog.submitEdition();
        waitForAjaxCompletion();
        return this;
    }
}
