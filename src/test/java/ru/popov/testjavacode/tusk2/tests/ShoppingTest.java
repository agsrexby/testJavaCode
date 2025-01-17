package ru.popov.testjavacode.tusk2.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.popov.testjavacode.tusk2.pages.*;
import ru.popov.testjavacode.tusk2.utils.DataGenerator;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingTest {
    protected ChromeOptions options = new ChromeOptions();
    private WebDriver driver;
    private String username;
    private String password;
    private String name;
    private String country;
    private String city;
    private String card;
    private String month;
    private String year;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver((ChromeOptions) options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        username = DataGenerator.generateUsername();
        password = DataGenerator.generatePassword();

        name = "Alex";
        country = "US";
        city = "San Francisco";
        card = "165632637848959";
        month = "01";
        year = "2020";

    }


    @Test
    public void testShoppingFlow() throws InterruptedException {
        // Шаг 1: Регистрация
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUp();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register(username, password);
        signUpPage.handleAlert();

        // Шаг 2: Логин
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        loginPage.handleAlert();
//        System.out.println(username);
//        System.out.println(password);

        // Шаг 3: Добавление товаров в корзину
        homePage.clickPhones();
        homePage.goToItem();
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        productPage.handleAlert();
        productPage.goHome();
        homePage.clickLaptops();
        homePage.goToItem();
        productPage.addToCart();
        productPage.handleAlert();
        productPage.goHome();
        homePage.clickMonitors();
        homePage.goToItem();
        productPage.addToCart();
        productPage.handleAlert();

        // Шаг 4: Сравнение цен
        // Сравнить цену на странице со списком и на карточке товара.
        productPage.goHome();
        String priceOnList = homePage.getItemPrice();
        homePage.goToItem();
        String priceOnCard = productPage.getProductPrice();
        int price1 = Integer.parseInt(priceOnList);
        int price2 = Integer.parseInt(priceOnCard);
        Assertions.assertEquals(price1, price2, "Wrong price on list");

        // Шаг 5: Проверка корзины
        homePage.goToCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.getTotalSum();

        // Шаг 6: Оформление заказа
        cartPage.clikOnOrder();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.createOrder(name, country, city, card, month, year);

        // Шаг 7: Проверка даты
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String formattedDate = currentDate.format(formatter);
        System.out.println(formattedDate);
        SuccessOrderPage successOrderPage = new SuccessOrderPage(driver);
        String orderDate = successOrderPage.getCurDate();
        successOrderPage.handleAlert();
        Assertions.assertEquals(orderDate, formattedDate, "Wrong order date");

    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}