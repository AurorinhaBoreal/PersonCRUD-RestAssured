package com.db.person_restassured.fixture;

import java.time.LocalDate;

import com.db.person_restassured.modal.Person;

public class PersonFixture {

    public static Person createPersonValid () {
        return Person.builder()
            .firstName("Aurora")
            .lastName("Kruschewsky")
            .cpf("49146529004")
            .birthDate(LocalDate.of(2004, 05, 14))
            .photoId(5)
            .build();
    }

    public static Person createPersonInvalid () {
        return Person.builder()
            .firstName("Aurora")
            .lastName("Rossi")
            .birthDate(LocalDate.of(2004, 05, 14))
            .photoId(5)
            .build();
    }
}
