package com.pizzaria.testeEnum;

import com.pizzaria.entity.Cargo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CargoTest {

    @Test
    void testCargo() {
        assertEquals("CARGOS", Cargo.CARGOS.name());
    }

    @Test
    void testToStringCargo() {assertEquals("CARGOS", Cargo.CARGOS.toString());}

}
