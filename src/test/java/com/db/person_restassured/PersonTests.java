package com.db.person_restassured;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.db.person_restassured.fixture.PersonFixture;
import com.db.person_restassured.fixture.UpdatePersonFixture;
import com.db.person_restassured.modal.Person;
import com.db.person_restassured.modal.UpdatePerson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class PersonTests {
    
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/person";
    }

    
    @Test
    public void allowPersonCreationAndDeletion () {
        Person validPerson = PersonFixture.createPersonValid();

        String cpf = validPerson.getCpf();

        given()
            .contentType(ContentType.JSON)
            .body(validPerson)
            .when()
            .post("/create")
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .when()
            .delete("/delete/"+cpf)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void allowGetSpecificPerson() {
        Person validPerson = PersonFixture.createPersonValid();
        String cpf = validPerson.getCpf();

        given()
            .contentType(ContentType.JSON)
            .body(validPerson)
            .when()
            .post("/create")
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .when()
            .get("/"+cpf)
            .then()
            .statusCode(HttpStatus.OK.value());

        given()
            .when()
            .delete("/delete/"+cpf)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void notAllowPersonCreation() {
        Person invalidPerson = PersonFixture.createPersonInvalid();

        given()
            .contentType(ContentType.JSON)
            .body(invalidPerson)
            .when()
            .post("/create")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void allowGetPerson() {
        Person validPerson = PersonFixture.createPersonValid();

        String cpf = validPerson.getCpf();

        given()
            .contentType(ContentType.JSON)
            .body(validPerson)
            .when()
            .post("/create")
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value());

        given()
            .when()
            .delete("/delete/"+cpf)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void allowPersonCreationUpdateAndDeletion() {
        Person validPerson = PersonFixture.createPersonValid();
        UpdatePerson validUpdate = UpdatePersonFixture.updatePersonValid();
        String cpf = validPerson.getCpf();

        given()
            .contentType(ContentType.JSON)
            .body(validPerson)
            .when()
            .post("/create")
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .contentType(ContentType.JSON)
            .body(validUpdate)
            .when()
            .patch("/update/"+cpf)
            .then()
            .statusCode(HttpStatus.OK.value());
            
        given()
            .when()
            .delete("/delete/"+cpf)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
