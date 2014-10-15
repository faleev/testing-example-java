package com.example.selenium.jira;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditIssueDialog extends IssuePage {

    public EditIssueDialog(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//*[@id='edit-issue-dialog']/div[1]/h2")
    private WebElement dialogTitle;

    @FindBy(xpath = ".//*[@href='#tab-0']")
    private WebElement firstTab;

    @FindBy(xpath = ".//*[@href='#tab-1']")
    private WebElement secondTab;

    @FindBy(xpath = ".//*[@href='#tab-2']")
    private WebElement thirdTab;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(id = "issuetype-field")
    private WebElement issueTypeField;

    @FindBy(id = "priority-field")
    private WebElement priorityField;

    @FindBy(id = "edit-issue-submit")
    private WebElement editIssueUpdateButton;

    public EditIssueDialog fillingEditIssueForm(String description, String type) {
        inputTextIntoField(descriptionField, description);
        inputTextIntoField(issueTypeField, type);
        // Add more fields/tabs here.
        return this;
    }

    public IssuePage submitEdition() {
        editIssueUpdateButton.click();
        waitForAjaxCompletion();
        return new IssuePage(driver);
    }
}
