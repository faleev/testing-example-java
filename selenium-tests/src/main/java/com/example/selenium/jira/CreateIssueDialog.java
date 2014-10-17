package com.example.selenium.jira;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class CreateIssueDialog extends CommonPage {


    public CreateIssueDialog(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "project-field")
    private WebElement projectDropdownField;

    @FindBy(id = "issuetype-field")
    private WebElement issueTypeDropdownField;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(id = "security")
    private WebElement securityLevelDropdown;

    @FindBy(id = "priority-field")
    private WebElement priorityDropdownField;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(id = "qf-create-another")
    private WebElement createAnotherIssueCheckbox;

    @FindBy(id = "create-issue-submit")
    private WebElement createIssueSubmitButton;

    @FindBy(linkText = "Cancel")
    private WebElement createIssueCancelButton;

    private static Map<String, String> issueFields = new HashMap<>(); // Handling of the content of the issue's fields.

    public CreateIssueDialog fillingCreateIssueForm() {
        inputTextIntoField(projectDropdownField, issueFields.get("Project"));
        inputTextIntoField(issueTypeDropdownField, issueFields.get("Issue Type"));
        inputTextIntoField(summaryField, issueFields.get("Summary"));
        inputTextIntoField(priorityDropdownField, issueFields.get("Priority"));
        inputTextIntoField(descriptionField, issueFields.get("Description"));
        return this;
    }

    public CommonPage submitIssue() {
        createIssueSubmitButton.click();
        waitForAjaxCompletion();
        return new CommonPage(driver);
    }

    // TODO: Reorganize issue's fields population to avoid implicit access in different methods.
    public static void setIssueFields(String project, String type, String priority, String summary, String description) {
        issueFields.put("Project", project);
        issueFields.put("Issue Type", type);
        issueFields.put("Priority", priority);
        issueFields.put("Summary", summary);
        issueFields.put("Description", description);
    }
}
