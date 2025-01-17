package ru.popov.testjavacode.tusk2.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement createOrderButton;

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void getTotalSum() {
        List<WebElement> curTotalSum = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[(@class='success')]//td[3]")));
        int total = 0;
        for (WebElement li : curTotalSum) {
            total += Integer.parseInt(li.getText());
            System.out.println(li.getText());
        }
        System.out.println(total);
        WebElement totalSum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[(@id='totalp')]")));
        int result = Integer.parseInt(totalSum.getText());
        Assertions.assertEquals(total, result, "total sum should be " + result);
    }

    public void clikOnOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(createOrderButton));
        createOrderButton.click();
    }
}
