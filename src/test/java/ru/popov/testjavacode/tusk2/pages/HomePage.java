package ru.popov.testjavacode.tusk2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()='Sign up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//a[text()='Log in']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@class='hrefch']")
    private WebElement itemLinkButton;

    @FindBy(xpath = "//a[text()='Phones']")
    private WebElement phonesButton;

    @FindBy(xpath = "//a[text()='Laptops']")
    private WebElement laptopsButton;

    @FindBy(xpath = "//a[text()='Monitors']")
    private WebElement monitorsButton;

    @FindBy(xpath = "//a[text()='Cart']")
    private WebElement cartButton;

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSignUp() {
        signUpButton.click();
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void goToCart() {
        cartButton.click();
    }
    public void clickPhones() {
        wait.until(ExpectedConditions.elementToBeClickable(phonesButton));
        phonesButton.click();
    }
    public void clickLaptops() {
        wait.until(ExpectedConditions.elementToBeClickable(laptopsButton));
        laptopsButton.click();
    }
    public void clickMonitors() {
        wait.until(ExpectedConditions.elementToBeClickable(monitorsButton));
        monitorsButton.click();
    }
    public void goToItem() {
        wait.until(ExpectedConditions.elementToBeClickable(itemLinkButton));
        itemLinkButton.click();
    }
    public String getItemPrice() {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(), '$')]")));
        return priceElement.getText().replaceAll("[^\\d]", "");
    }

}