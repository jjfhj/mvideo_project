package com.github.jjfhj.tests;

import com.github.jjfhj.JiraIssue;
import com.github.jjfhj.JiraIssues;
import com.github.jjfhj.Layer;
import com.github.jjfhj.Microservice;
import com.github.jjfhj.helpers.Attach;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.github.jjfhj.config.WebDriverUtil.APP_CONFIG;
import static com.github.jjfhj.tests.TestData.email;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Layer("web")
@Owner("kgordienko")
@Tags({@Tag("Web"), @Tag("UI")})
@JiraIssues({@JiraIssue("HOMEWORK-282")})
@DisplayName("Тестирование веб-приложения М.Видео")
public class MvideoTest extends TestBase {

    @ValueSource(strings = {"Ирригатор B.Well WI-911 150мл (2 насадки)",
            "Ирригатор B.Well WI-912 (5 насадок)"})
    @DisplayName("Результаты поиска")
    @Tags({@Tag("Blocker"), @Tag("High")})
    @Microservice("Search Results")
    @ParameterizedTest(name = "Отображение товара {0} в результатах поиска")
    @Feature("Поиск")
    @Story("Страница результатов поиска")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "М.Видео", url = "https://www.mvideo.ru/")
    void searchResultsTest(String searchQuery) {
        step("Открыть главную страницу М.Видео", () -> {
            open(APP_CONFIG.appURL());
        });
        step("Найти ирригаторы торговой марки B.Well", () -> {
            $(".input__field").setValue("Ирригатор B.Well").pressEnter();
        });
        step("Найти отображение товара " + searchQuery + " в результатах поиска", () -> {
            $$("div.product-cards-layout--grid").shouldHave(texts(searchQuery));
        });
    }

    @CsvSource(value = {
            "Зеркало косметическое | Зеркала косметические",
            "Apple Magic Mouse | Беспроводные мыши Apple"
    }, delimiter = '|')
    @DisplayName("Фильтр 'Категория'")
    @Tags({@Tag("Minor"), @Tag("Low")})
    @Microservice("Filter Category")
    @ParameterizedTest(name = "Отображение категории {1} в фильтре 'Категория'")
    @Feature("Фильтры")
    @Story("Блок фильтров")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "М.Видео", url = "https://www.mvideo.ru/")
    void filterCategoryTest(String searchQuery, String categoryName) {
        step("Открыть главную страницу М.Видео", () -> {
            open(APP_CONFIG.appURL());
        });
        step("Найти товар " + searchQuery, () -> {
            $(".input__field").setValue(searchQuery).pressEnter();
        });
        step("Найти отображение категории " + categoryName + " в фильтре 'Категория'", () -> {
            $(".filter-checkbox-list").shouldHave(text(categoryName));
        });
    }

    @EnumSource(ProfileMenu.class)
    @DisplayName("Пункты меню неавторизованного пользователя")
    @Tags({@Tag("Blocker"), @Tag("High")})
    @Microservice("Menu Item")
    @ParameterizedTest(name = "Отображение пункта меню {0}")
    @Feature("Меню")
    @Story("Панель пунктов меню")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "М.Видео", url = "https://www.mvideo.ru/")
    void displayOfAnonymousMenuItemTest(ProfileMenu profileMenu) {
        step("Открыть главную страницу М.Видео", () -> {
            open(APP_CONFIG.appURL());
        });
        step("Найти отображение пункта меню " + profileMenu + " в навигационной панели", () -> {
            $(".nav-tabs").shouldHave(text(profileMenu.getProfileMenu()));
        });
    }

    @MethodSource("com.github.jjfhj.tests.ReviewsByCategory#productCategories")
    @DisplayName("Обзор по категориям на основной странице категории товара")
    @Tags({@Tag("Minor"), @Tag("Low")})
    @Microservice("Overview By Category")
    @ParameterizedTest(name = "Отображение категорий {1} в обзоре по категориям на странице {0}")
    @Feature("Категории")
    @Story("Блок обзора по категориям")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "М.Видео", url = "https://www.mvideo.ru/")
    void displayOfTheOverviewByCategoryTest(String reviewsByCategory, List<String> productCategories) {
        step("Открыть главную страницу М.Видео", () -> {
            open(APP_CONFIG.appURL());
        });
        step("Открыть каталог товаров", () -> {
            $("[class='button button--with-icon ng-star-inserted']").click();
        });
        step("Перейти в категорию " + reviewsByCategory, () -> {
            $$(".left-menu__item-text").find(text(reviewsByCategory)).click();
        });
        step("Найти отбражение категории " + productCategories + " в обзоре по категориям", () -> {
            $$("a.c-list-of-links__item").shouldHave(texts(productCategories));
        });
    }

    @Test
    @DisplayName("Отсутствие ошибки 'SEVERE' в консоли страницы")
    @Tags({@Tag("Critical"), @Tag("Highest")})
    @Microservice("Console")
    @Owner("user")
    @Feature("Консоль")
    @Story("Журнал консоли")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "М.Видео", url = "https://www.mvideo.ru/")
    void consoleShouldNotHaveErrorsTest() {
        step("Открыть главную страницу М.Видео", () -> {
            open(APP_CONFIG.appURL());
        });
        step("Проверить отсутствие текста 'SEVERE' в консоли", () -> {
            String consoleLogs = Attach.browserConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

    @Test
    @DisplayName("Email-подписка на получение предложений")
    @Tags({@Tag("Minor"), @Tag("Medium")})
    @Microservice("Email")
    @Feature("Email")
    @Story("Email-подписки")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "М.Видео", url = "https://www.mvideo.ru/")
    void emailSubscriptionToReceiveOffersTest() {
        step("Открыть главную страницу М.Видео", () -> {
            open(APP_CONFIG.appURL());
        });
        step("Проскроллить страницу до футера", () -> {
            $(".layout__footer .ng-star-inserted").scrollTo();
        });
        step("В поле блока 'Получайте самые интересные предложения первыми!' ввести валидный email", () -> {
            $("#mvideo-form-field-input-0").setValue(email);
        });
        step("Нажать на значок 'стрелка' (→)", () -> {
            $(".email-icon-wrap").click();
        });
        step("Проверить отображение сообщения об успешной отправке данных", () -> {
            $(".footer__subscribe-success-text").shouldHave(text("Данные успешно отправлены. Спасибо!"));
        });
    }
}
