package com.github.jjfhj.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.jjfhj.config.WebDriverUtil;
import com.github.jjfhj.helpers.Attach;
import com.github.jjfhj.steps.EmailSubscriptionSteps;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.qameta.allure.Allure.step;

public class TestBase {

    EmailSubscriptionSteps emailSubscriptionSteps = new EmailSubscriptionSteps();

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        WebDriverUtil.configure();
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        step("Закрыть браузер", () ->
                Selenide.closeWebDriver());
    }
}
