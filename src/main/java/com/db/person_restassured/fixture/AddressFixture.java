package com.db.person_restassured.fixture;

import com.db.person_restassured.modal.Address;

public class AddressFixture {
    public static Address createAddressValid () {
        return Address.builder()
            .addressIdentifier(1L)
            .country("Brasil")
            .uf("SP")
            .city("Embu das Artes")
            .neighborhood("Jd. Tomé")
            .street("Jardimzinho")
            .number("1345")
            .zipCode("29313-250")
            .mainAddress(false)
            .build();
    }

    public static Address createAddressInvalid () {
        return Address.builder()
            .country("Brasil")
            .uf("SP")
            .neighborhood("Jd. Tomé")
            .street("Jardimzinho")
            .number("1345")
            .zipCode("29313-250")
            .mainAddress(false)
            .build();
    }
}
