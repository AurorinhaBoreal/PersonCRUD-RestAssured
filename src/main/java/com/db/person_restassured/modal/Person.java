package com.db.person_restassured.modal;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String cpf;
    private LocalDate birthDate;
    private Integer photoId;
}
