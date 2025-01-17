package ru.popov.testjavacode.tusk2.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuccessOrderPage extends BasePage {

    public SuccessOrderPage(WebDriver driver) {
        super(driver);
    }

    public String getCurDate(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement date = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(., 'Date:')]")));

        String input = date.getText();

        // Регулярное выражение для поиска даты в формате dd/mm/yyyy
        String regex = "\\b\\d{1,2}/\\d{1,2}/\\d{4}\\b";

        // Компиляция регулярного выражения
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Поиск и вывод найденной даты
        if (matcher.find()) {
            String n = matcher.group();
            System.out.println(n);
            return n;
        }
        return "Date not found";
    }
}
