package ru.popov.testjavacode.tusk2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage extends BasePage {

    @FindBy(xpath = "//input[@id='sign-username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='sign-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signUpSubmitButton;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void register(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ожидание видимости полей
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));

        usernameField.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(signUpSubmitButton));
        signUpSubmitButton.click();
    }
}