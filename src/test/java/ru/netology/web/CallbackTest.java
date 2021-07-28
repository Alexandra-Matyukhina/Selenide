package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallbackTest {

    @BeforeEach
    void setUp(){
        open("http://localhost:7777");
    }
    @Test
    void shouldRequestTest() {
        $("[data-test-id=name] input").setValue("Матюхина Александра");
        $("[data-test-id=phone] input").setValue("+79689111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldRequestWithoutName(){
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79689111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(" Поле обязательно для заполнения"));


    }
//    @Test
////    void shouldRequestIncorrectName(){
////        $("[data-test-id=name] input").setValue("Александра");
////        $("[data-test-id=phone] input").setValue("+79689111111");
////        $("[data-test-id=agreement]").click();
////        $("button").click();
////        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(""));
////    }
    @Test
    void shouldRequestIncorrectPhone(){
        $("[data-test-id=name] input").setValue("Матюхина Александра");
        $("[data-test-id=phone] input").setValue("8910976о7");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldRequestNoAgreement(){
        $("[data-test-id=name] input").setValue("Матюхина Александра");
        $("[data-test-id=phone] input").setValue("+79689111111");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

