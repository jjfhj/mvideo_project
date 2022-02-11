package com.github.jjfhj.tests;

import com.github.javafaker.Faker;

public class TestData {

    public static Faker faker = new Faker();
    public static String email = faker.name().username() + "@test.com";
    public static String successMessage = "Данные успешно отправлены. Спасибо!";
    public static String pdfName = "Образец заполнения доверенности.pdf";
}
