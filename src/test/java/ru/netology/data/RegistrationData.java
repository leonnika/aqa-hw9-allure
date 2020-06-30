package ru.netology.data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RegistrationData {


    private RegistrationData() {
    }

    public static RegistrationDate registrationDate(String locale) {
        int dayNumber = 10;
        int intervalDayDefault = 3;
        int randomIndexDay;
        LocalDate randomDate;
        LocalDate date = LocalDate.now();
        randomIndexDay = (int) (Math.random() * dayNumber);
        date = date.plusDays(intervalDayDefault);
        randomDate = date.plusDays(randomIndexDay);
        String dateStr = randomDate.format(DateTimeFormatter.ofPattern("dd MM yyyy", new Locale("ru")));
        return new RegistrationDate(dateStr);
    }

    public static RegistrationUser registrationUserValidData(String locale) {
        int cityNumber = 5;
        String[] city = {"Москва", "Санкт-Петербург", "Екатеринбург", "Волгоград", "Краснодар"};
        int randomIndexCity = (int) (Math.random() * cityNumber);
        String randomCity;
        Faker faker = new Faker(new Locale("ru"));
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("ru"), new RandomService());
        String phone = fakeValuesService.regexify("[1-9]{11}");
        phone = "+" + phone;
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return new RegistrationUser(
                name,
                city[randomIndexCity],
                phone);
    }

    public static RegistrationUser registrationUserDataSpecialSymbols(String locale) {
        String[] specialSymbols = {"!", "@", "#", "$", "%","^","&","(",")","'","_","+","?","<"};
        int numberSpecialSymbols=14;
        int randomIndexSpecialSymbols = (int) (Math.random() * numberSpecialSymbols);
        int i=0;
        String name="";
        String city="";
        String phone="";
        int leghtParam= (int) (Math.random() * numberSpecialSymbols);
        while (i < leghtParam) {
            name=name+specialSymbols[randomIndexSpecialSymbols];
            i++;
        }
        i=0;
        leghtParam= (int) (Math.random() * numberSpecialSymbols);
        while (i < leghtParam) {
            city=city+specialSymbols[randomIndexSpecialSymbols];
            i++;
        }
        i=0;
        leghtParam= (int) (Math.random() * numberSpecialSymbols);
        while (i < leghtParam) {
            phone=phone+specialSymbols[randomIndexSpecialSymbols];
            i++;
        }
        return new RegistrationUser(name,city,phone);
       }

    public static RegistrationUser registrationUserNotValidData(String locale) {
        int cityNumber = 5;
        String[] city = {"Сургут", "Нижневартовск", "Нефтеюганск", "Покачи", "Лянтор"};
        int randomIndexCity = (int) (Math.random() * cityNumber);
        Faker faker = new Faker(new Locale("ru"));
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("ru"), new RandomService());
        String phone = fakeValuesService.regexify("[0-9]{10}");
        phone = "+" + "0"+phone;
        return new RegistrationUser(
                faker.name().lastName(),
                city[randomIndexCity],
                phone);
    }

    public static RegistrationSpecificName registrationShortName(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String nameStr = faker.regexify("[А-Я]{1}") + " " + faker.regexify("[А-Я]{1}");
        return new RegistrationSpecificName(nameStr);
    }

    public static RegistrationSpecificName registrationNameInEnglish(String locale) {
        Faker faker = new Faker(new Locale("en"));
         return new RegistrationSpecificName(faker.name().fullName());
    }

    public static RegistrationSpecificName registrationDubleName(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String nameStr=faker.name().lastName()+" "+faker.name().firstName()+"-"+faker.name().firstName();
        return new RegistrationSpecificName(nameStr);
    }

    public static RegistrationSpecificName registrationNameletterYO(String locale) {
        int nameNumber = 5;
        String[] lastName = {"Кисилёв", "Фёдорова", "Ёлкин", "Ёжиков", "Королёва"};
        int randomIndexName = (int) (Math.random() * nameNumber);
        Faker faker = new Faker(new Locale("ru"));
        String nameStr=lastName[randomIndexName]+" "+faker.name().firstName();
        return new RegistrationSpecificName(nameStr);
    }

    public static RegistrationSpecificName registrationNameDifferentCaseLetters(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String nameStr = faker.regexify("[А-Я]{6}") + " " + faker.name().firstName();
        return new RegistrationSpecificName(nameStr);
    }


}
