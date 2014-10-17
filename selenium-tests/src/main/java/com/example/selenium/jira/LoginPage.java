package com.example.selenium.jira;


import com.example.selenium.common.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "rememberMe")
    private WebElement rememberMeCheckbox;

    @FindBy(id = "login-submit")
    private WebElement loginButton;

    public CommonPage fillingLoginFormAndSubmit(String user, String password) {
        usernameField.sendKeys(user);
        passwordField.sendKeys(password);
        if (rememberMeCheckbox.isSelected()) rememberMeCheckbox.click();
        loginButton.click();
        return new CommonPage(driver);
    }
}
