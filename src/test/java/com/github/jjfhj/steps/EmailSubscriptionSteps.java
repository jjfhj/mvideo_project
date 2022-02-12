package com.github.jjfhj.steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.github.jjfhj.config.WebDriverUtil.APP_URL;
import static com.github.jjfhj.tests.TestData.email;
import static com.github.jjfhj.tests.TestData.successMessage;

public class EmailSubscriptionSteps {

    @Step("Открыть главную страницу М.Видео")
    public EmailSubscriptionSteps openHomepage() {
        open(APP_URL);
        return this;
    }

    @Step("Проскроллить страницу до футера")
    public EmailSubscriptionSteps scrollPageToFooter() {
        $(".layout__footer .ng-star-inserted").scrollTo();
        return this;
    }

    @Step("В поле блока 'Получайте самые интересные предложения первыми!' ввести валидный email")
    public EmailSubscriptionSteps enterAValidEmail() {
        $("#mvideo-form-field-input-0").setValue(email);
        return this;
    }

    @Step("Нажать на значок 'стрелка' (→)")
    public EmailSubscriptionSteps clickOnTheArrowIcon() {
        $(".email-icon-wrap").click();
        return this;
    }

    @Step("Проверить отображение сообщения об успешной отправке данных")
    public EmailSubscriptionSteps checkTheDisplayOfTheSuccessMessage() {
        $(".footer__subscribe-success-text").shouldHave(text(successMessage));
        return this;
    }
}
