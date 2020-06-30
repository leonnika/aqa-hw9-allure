package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.data.RegistrationData;
import ru.netology.data.RegistrationSpecificName;
import ru.netology.data.RegistrationUser;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryTest {
RegistrationUser userValid = RegistrationData.registrationUserValidData("ru");
RegistrationUser userSpecialSymbols=RegistrationData.registrationUserDataSpecialSymbols("ru");
RegistrationUser userNotValid =RegistrationData.registrationUserNotValidData("ru");

    @BeforeAll
    static void setUpAllallure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @ AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

     @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldReplanMeetingDataValid() {
        $("[placeholder='Город']").setValue(userValid.getCity());
        $("[type='tel'][placeholder='Дата встречи']").click();
        $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
        $("[name='name']").setValue(userValid.getName());
        $("[name='phone']").setValue(userValid.getPhone());
        $("[role='presentation']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $("[type='tel'][placeholder='Дата встречи']").click();
        $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
        $$("button").find(exactText("Запланировать")).click();
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
        //ussue1
    void shouldNotReplanMeetingDatesEqual() {
        String datePlan = RegistrationData.registrationDate("ru").getDate();
        $("[placeholder='Город']").setValue(userValid.getCity());
        $("[type='tel'][placeholder='Дата встречи']").click();
        $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[type='tel'][placeholder='Дата встречи']").setValue(datePlan);
        $("[name='name']").setValue(userValid.getName());
        $("[name='phone']").setValue(userValid.getPhone());
        $("[role='presentation']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Встреча на эту дату уже запланирована!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotReplanMeetingDateValidCheckIsNot() {
        $("[placeholder='Город']").setValue(userValid.getCity());
        $("[type='tel'][placeholder='Дата встречи']").click();
        $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
        $("[name='name']").setValue(userValid.getName());
        $("[name='phone']").setValue(userValid.getPhone());
        $$("button").find(exactText("Запланировать")).click();
        assertEquals("rgba(255, 92, 92, 1)", $("[data-test-id=agreement]").getCssValue("color"));
    }

    @Nested
    class EmptyFieldsOtions {
        String expected = "Поле обязательно для заполнения";

        @Test
        void shouldNotReplanMeetingCityEmpty() {
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(userValid.getName());
            $("[name='phone']").setValue(userValid.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            $("[class='input__sub']").shouldHave(exactText(expected));
        }

        @Test
        void shouldNotReplanMeetingNameEmpty() {
            $("[placeholder='Город']").setValue(userValid.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='phone']").setValue(userValid.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement name = $("[data-test-id='name']");
            name.$("[class='input__sub']").shouldHave(exactText(expected));
        }

        @Test
        void shouldNotReplanMeetingPhoneEmpty() {
            $("[placeholder='Город']").setValue(userValid.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(userValid.getName());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement phone = $("[data-test-id='phone']");
            phone.$("[class='input__sub']").shouldHave(exactText(expected));
        }

        @Test
        void shouldNotReplanMeetingDateEmpty() {
            $("[placeholder='Город']").setValue(userValid.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[name='name']").setValue(userValid.getName());
            $("[name='phone']").setValue(userValid.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement date = $("[data-test-id='date']");
            date.$("[class='input__sub']").shouldHave(exactText("Неверно введена дата"));
        }
    }

    @Nested
    class SpecialSymbolsInFieldsOtions {

        @Test
        void shouldNotReplanMeetingCitySpecialSymbols() {
            $("[placeholder='Город']").setValue(userSpecialSymbols.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(userValid.getName());
            $("[name='phone']").setValue(userValid.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement city = $("[data-test-id='city']");
            city.$("[class='input__sub']").shouldHave(exactText("Доставка в выбранный город недоступна"));
        }

        @Test
        void shouldNotReplanMeetingNameSpecialSymbols() {
            $("[placeholder='Город']").setValue(userValid.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(userSpecialSymbols.getName());
            $("[name='phone']").setValue(userValid.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement name = $("[data-test-id='name']");
            name.$("[class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
            //ussue2
        void shouldNotReplanMeetingPhoneSpecialSymbols() {
            $("[placeholder='Город']").setValue(userValid.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(userValid.getName());
            $("[name='phone']").setValue(userSpecialSymbols.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement phone = $("[data-test-id='phone']");
            phone.$("[class='input__sub']").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void shouldNotReplanMeetingDateSpesialSymbol() {
            $("[placeholder='Город']").setValue(userValid.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationUserDataSpecialSymbols("ru").getPhone());
            $("[name='name']").setValue(userValid.getName());
            $("[name='phone']").setValue(userValid.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement date = $("[data-test-id='date']");
            date.$("[class='input__sub']").shouldHave(exactText("Неверно введена дата"));
        }
    }

    @Nested
    class NotValidDateInFieldsOtions {

        @Test
        void shouldNotReplanMeetingCityNotValid() {
            $("[placeholder='Город']").setValue(userNotValid.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(userValid.getName());
            $("[name='phone']").setValue(userValid.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement city = $("[data-test-id='city']");
            city.$("[class='input__sub']").shouldHave(exactText("Доставка в выбранный город недоступна"));
        }

        @Test
//issue 6
        void shouldNotReplanMeetingNameNotValid() {
            $("[placeholder='Город']").setValue(userValid.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(userNotValid.getName());
            $("[name='phone']").setValue(userValid.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement name = $("[data-test-id='name']");
            name.$("[class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
            //ussue3
        void shouldNotReplanMeetingPhoneNotValid() {
            $("[placeholder='Город']").setValue(userValid.getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='name']").setValue(userValid.getName());
            $("[name='phone']").setValue(userNotValid.getPhone());
            $("[role='presentation']").click();
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement phone = $("[data-test-id='phone']");
            phone.$("[class='input__sub']").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }
    }

    @Nested
    class NameOptions {
        @BeforeEach
        void init() {
            $("[placeholder='Город']").setValue(RegistrationData.registrationUserValidData("ru").getCity());
            $("[type='tel'][placeholder='Дата встречи']").click();
            $("[type='tel'][placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
            $("[type='tel'][placeholder='Дата встречи']").setValue(RegistrationData.registrationDate("ru").getDate());
            $("[name='phone']").setValue(RegistrationData.registrationUserValidData("ru").getPhone());
            $("[role='presentation']").click();
        }

        @Test
        void shouldNotReplanMeetingDubleName() {
            $("[name='name']").setValue(RegistrationData.registrationDubleName("ru").getName());
            $$("button").find(exactText("Запланировать")).click();
            $(withText("Успешно!")).waitUntil(visible, 15000);
        }

        @Test
        void shouldNotReplanMeetingShortName() {
            $("[name='name']").setValue(RegistrationData.registrationShortName("ru").getName());
            $$("button").find(exactText("Запланировать")).click();
            $(withText("Успешно!")).waitUntil(visible, 15000);
        }

        @Test
        void shouldNotReplanMeetingNameInEnglish() {
            $("[name='name']").setValue(RegistrationData.registrationNameInEnglish("en").getName());
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement name = $("[data-test-id='name']");
            name.$("[class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
            //ussue4
        void shouldNotReplanMeetingNameletterYO() {
            $("[name='name']").setValue(RegistrationData.registrationNameletterYO("ru").getName());
            $$("button").find(exactText("Запланировать")).click();
            $(withText("Успешно!")).waitUntil(visible, 15000);
        }

        @Test
            //ussue5
        void shouldNotReplanMeetingNameDifferentCaseLetters() {
            $("[name='name']").setValue(RegistrationData.registrationNameDifferentCaseLetters("ru").getName());
            $$("button").find(exactText("Запланировать")).click();
            SelenideElement name = $("[data-test-id='name']");
            name.$("[class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }
    }
}

