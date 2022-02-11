package com.github.jjfhj.tests;

import com.codeborne.pdftest.PDF;
import com.github.jjfhj.JiraIssue;
import com.github.jjfhj.JiraIssues;
import com.github.jjfhj.Layer;
import com.github.jjfhj.Microservice;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.github.jjfhj.config.WebDriverUtil.APP_FOR_BUSINESS_URL;
import static com.github.jjfhj.tests.TestData.pdfName;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

@Layer("web")
@Owner("kgordienko")
@Tags({@Tag("Web"), @Tag("UI")})
@JiraIssues({@JiraIssue("HOMEWORK-282")})
@DisplayName("Тестирование веб-приложения М.Видео")
public class MvideoForBusinessTest extends TestBase {

    @Test
    @DisplayName("Скачивание PDF-файла, проверка его свойств и содержимого")
    @Tags({@Tag("Major"), @Tag("Medium")})
    @Microservice("Documents for business")
    @Feature("Документы для бизнеса")
    @Story("PDF-файл 'Образец заполнения доверенности'")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "М.Видео для бизнеса", url = "https://www.mvideo.ru/mvideo-biznes-dokumenty")
    void test() throws IOException, ParseException {
        step("Открыть страницу М.Видео с документами для бизнеса", () -> {
            open(APP_FOR_BUSINESS_URL);
        });
        step("Скачать PDF-файл 'Образец заполнения доверенности', проверить его свойства и содержимое", () -> {
            File pdf = $(byText(pdfName)).download();
            PDF parsedPdf = new PDF(pdf);
            assertThat(parsedPdf.content, is(notNullValue()));
            assertThat(parsedPdf.title, is(nullValue()));
            assertThat(parsedPdf.author, is(nullValue()));
            assertThat(parsedPdf.subject, is(nullValue()));
            assertThat(parsedPdf.keywords, is(nullValue()));
            assertThat(parsedPdf.encrypted, is(false));
            assertThat(parsedPdf.numberOfPages, equalTo(1));
//        assertThat(parsedPdf.creationDate.getTime(),
//                equalTo(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("18.12.2020 07:15:16")));
        });
    }
}
