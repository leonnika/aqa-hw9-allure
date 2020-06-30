package ru.netology.data;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class RegistrationUser {
    private final String name;
    private final String city;
    private final String phone;
}
