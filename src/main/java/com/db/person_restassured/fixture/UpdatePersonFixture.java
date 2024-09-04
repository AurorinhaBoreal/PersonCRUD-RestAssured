package com.db.person_restassured.fixture;

import java.time.LocalDate;

import com.db.person_restassured.modal.UpdatePerson;

public class UpdatePersonFixture {
    public static UpdatePerson updatePersonValid () {
        return UpdatePerson.builder()
            .firstName("Aurora")
            .lastName("Rossi")
            .birthDate(LocalDate.of(2000, 04, 14))
            .photoId(2)
            .build();
    }

    public static UpdatePerson updatePersonInvalid () {
        return UpdatePerson.builder()
            .firstName("Aurora")
            .lastName("Inválida")
            .birthDate(null)
            .photoId(2)
            .build();
    }
}
