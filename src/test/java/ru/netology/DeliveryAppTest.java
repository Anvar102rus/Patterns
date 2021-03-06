package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class DeliveryAppTest {
    @Test
    void shouldCheckDeliveryDate() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(3));
        $("[data-test-id=name] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id=phone] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("div.notification__content").shouldBe(visible, exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(5));
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification']").shouldBe(visible);
        $$("button").find(exactText("Перепланировать")).click();
        $("div.notification__content").shouldBe(visible, exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(5)));
    }
}

