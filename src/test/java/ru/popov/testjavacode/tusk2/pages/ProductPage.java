package ru.popov.testjavacode.tusk2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[text()='Home ']")
    private WebElement goHomeButton;

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPrice() {
        WebElement productPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='price-container']")));
        return productPrice.getText().replaceAll("[^\\d]", ""); // Удаляем символы, кроме цифр
    }

    public void goHome() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(goHomeButton));
        goHomeButton.click();
        Thread.sleep(50);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }
}