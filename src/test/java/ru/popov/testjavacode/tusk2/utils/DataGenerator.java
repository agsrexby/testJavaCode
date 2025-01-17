package ru.popov.testjavacode.tusk2.utils;

import com.github.javafaker.Faker;

public class DataGenerator {
    private static final Faker faker = new Faker();

    public static String generateUsername() {
        return faker.name().username() + faker.number().digits(4);
    }

    public static String generatePassword() {
        return faker.internet().password();
    }
}