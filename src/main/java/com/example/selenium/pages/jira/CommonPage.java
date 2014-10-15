package com.example.selenium.pages.jira;

import com.example.selenium.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPage extends Page {

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "quickSearchInput")
    private WebElement quickSearchInputField;

    @FindBy(linkText = "Log In")
    private WebElement loginButton;

    @FindBy(id = "create_link")
    private WebElement createIssueButton;

    @FindBy(css = ".aui-message.success.closeable")
    private WebElement successMessage;

    @FindBy(css = ".issue-created-key.issue-link")
    private WebElement createdIssueNumber;

    private String issueNumber;

    public CommonPage inputTextIntoField(WebElement field, String visibleText) {
        waitForAjaxCompletion();
        field.clear();
        waitForAjaxCompletion();
        field.clear(); // Field does not cleared properly sometime.
        field.sendKeys(visibleText);
        return this;
    }

    public CommonPage loginToJira(String user, String password) {
        loginButton.click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillingLoginFormAndSubmit(user, password);
        return this;
    }

    public CommonPage createIssue() {
        waitForElement(createIssueButton);
        createIssueButton.click();
        CreateIssueDialog issueDialog = new CreateIssueDialog(driver);
        issueDialog.fillingCreateIssueForm();
        issueDialog.submitIssue();
        waitForAjaxCompletion();
        this.issueNumber = createdIssueNumber.getAttribute("data-issue-key");
        return this;
    }

    public IssuePage searchByIssueNumber(String text) {
        waitForElement(quickSearchInputField);
        quickSearchInputField.sendKeys(text);
        quickSearchInputField.sendKeys(Keys.RETURN);
        waitForAjaxCompletion();
        return new IssuePage(driver);
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }

    public String getIssueNumber() {
        return issueNumber;
    }
}
