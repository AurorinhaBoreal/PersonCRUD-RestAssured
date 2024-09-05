package com.db.person_restassured;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.db.person_restassured.fixture.AddressFixture;
import com.db.person_restassured.fixture.PersonFixture;
import com.db.person_restassured.modal.Address;
import com.db.person_restassured.modal.Person;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class AddressTests {

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:8080";
    }

    @Test
    public void allowAddressCreationAndDeletion() {
        Person validPerson = PersonFixture.createPersonValid();
        String personCpf = PersonFixture.createPersonValid().getCpf();
        Address invalidAddress = AddressFixture.createAddressInvalid();

        given()
            .contentType(ContentType.JSON)
            .body(validPerson)
            .when()
            .post("/person/create")
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .contentType(ContentType.JSON)
            .body(invalidAddress)
            .when()
            .post("/address/create/"+personCpf)
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());

        given()
            .when()
            .delete("/person/delete/"+personCpf)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void notAllowAddressCreation() {
        Person validPerson = PersonFixture.createPersonValid();
        String personCpf = PersonFixture.createPersonValid().getCpf();
        Address validAddress = AddressFixture.createAddressValid();

        given()
            .contentType(ContentType.JSON)
            .body(validPerson)
            .when()
            .post("/person/create")
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .contentType(ContentType.JSON)
            .body(validAddress)
            .when()
            .post("/address/create/"+personCpf)
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .when()
            .delete("/person/delete/"+personCpf)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void allowListAddress() {
        Person validPerson = PersonFixture.createPersonValid();
        String personCpf = PersonFixture.createPersonValid().getCpf();
        Address validAddress = AddressFixture.createAddressValid();
        Long addressID = validAddress.getAddressIdentifier();

        given()
            .contentType(ContentType.JSON)
            .body(validPerson)
            .when()
            .post("/person/create")
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .contentType(ContentType.JSON)
            .body(validAddress)
            .when()
            .post("/address/create/"+personCpf)
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .contentType(ContentType.JSON)
            .body(validAddress)
            .when()
            .patch("/address/update/"+addressID)
            .then()
            .statusCode(HttpStatus.OK.value());

        given()
            .when()
            .get("/address")
            .then()
            .statusCode(HttpStatus.OK.value());

        given()
            .when()
            .delete("/person/delete/"+personCpf)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void allowAddressUpdate() {
        Person validPerson = PersonFixture.createPersonValid();
        String personCpf = PersonFixture.createPersonValid().getCpf();
        Address validAddress = AddressFixture.createAddressValid();

        given()
            .contentType(ContentType.JSON)
            .body(validPerson)
            .when()
            .post("/person/create")
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .contentType(ContentType.JSON)
            .body(validAddress)
            .when()
            .post("/address/create/"+personCpf)
            .then()
            .statusCode(HttpStatus.CREATED.value());

        given()
            .when()
            .get("/address")
            .then()
            .statusCode(HttpStatus.OK.value());

        given()
            .when()
            .delete("/person/delete/"+personCpf)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
