package com.github.jjfhj.data;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class TestData {

    public static Faker faker = new Faker();
    public static String email = faker.name().username() + "@test.com";
    public static String successMessage = "Данные успешно отправлены. Спасибо!";
    public static String pdfName = "Образец заполнения доверенности.pdf";

    public static Stream<Arguments> productCategories() {
        return Stream.of(
                Arguments.of("Смартфоны и гаджеты",
                        List.of("Смартфоны", "Камерофоны", "Флагманы", "Планшеты", "Смарт-часы", "Гаджеты", "Android")),
                Arguments.of("Фото и видео",
                        List.of("Камеры", "Экшн-камеры", "Видеотехника", "Фото", "Объективы"))
        );
    }
}
