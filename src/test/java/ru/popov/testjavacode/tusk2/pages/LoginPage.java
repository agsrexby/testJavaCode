package ru.popov.testjavacode.tusk2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='loginusername']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='loginpassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginSubmitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ожидание видимости полей
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));

        usernameField.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton));
        loginSubmitButton.click();
    }
}