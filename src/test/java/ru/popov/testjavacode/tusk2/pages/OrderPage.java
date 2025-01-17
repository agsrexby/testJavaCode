package ru.popov.testjavacode.tusk2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage extends BasePage{

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@id='country']")
    private WebElement countryField;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@id='card']")
    private WebElement cardField;

    @FindBy(xpath = "//input[@id='month']")
    private WebElement monthField;

    @FindBy(xpath = "//input[@id='year']")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement orderSubmitButton;

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void createOrder(String name, String country, String city, String card, String month, String year) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ожидание видимости полей
        wait.until(ExpectedConditions.visibilityOf(nameField));
        wait.until(ExpectedConditions.elementToBeClickable(nameField));
        nameField.sendKeys(name);

        wait.until(ExpectedConditions.visibilityOf(countryField));
        wait.until(ExpectedConditions.elementToBeClickable(countryField));
        countryField.sendKeys(country);

        wait.until(ExpectedConditions.visibilityOf(cityField));
        wait.until(ExpectedConditions.elementToBeClickable(cityField));
        cityField.sendKeys(city);

        wait.until(ExpectedConditions.visibilityOf(cardField));
        wait.until(ExpectedConditions.elementToBeClickable(cardField));
        cardField.sendKeys(card);

        wait.until(ExpectedConditions.visibilityOf(monthField));
        wait.until(ExpectedConditions.elementToBeClickable(monthField));
        monthField.sendKeys(month);

        wait.until(ExpectedConditions.visibilityOf(yearField));
        wait.until(ExpectedConditions.elementToBeClickable(yearField));
        yearField.sendKeys(year);

        wait.until(ExpectedConditions.elementToBeClickable(orderSubmitButton));
        orderSubmitButton.click();
    }
}
