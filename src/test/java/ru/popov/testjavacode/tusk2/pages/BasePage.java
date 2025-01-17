package ru.popov.testjavacode.tusk2.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void handleAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent()); // Ожидание появления алерта
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText()); // Логирование текста алерта
            alert.accept(); // Принятие алерта
        } catch (TimeoutException e) {
            System.out.println("No alert present within the timeout.");
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present.");
        }

        // Проверка, что окно браузера доступно
        if (driver.getWindowHandles().isEmpty()) {
            throw new IllegalStateException("No browser windows are available after handling alert.");
        }
    }
}