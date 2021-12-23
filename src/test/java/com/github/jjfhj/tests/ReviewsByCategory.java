package com.github.jjfhj.tests;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class ReviewsByCategory {

    public static Stream<Arguments> productCategories() {
        return Stream.of(
                Arguments.of("Смартфоны и гаджеты",
                        List.of("Смартфоны", "Камерофоны", "Флагманы", "Планшеты", "Смарт-часы", "Гаджеты", "Android")),
                Arguments.of("Фото и видео",
                        List.of("Камеры", "Экшн-камеры", "Видеотехника", "Фото", "Объективы"))
        );
    }
}
