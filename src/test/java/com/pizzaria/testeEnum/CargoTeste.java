package com.pizzaria.testeEnum;

import com.pizzaria.entity.Cargo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CargoTeste {

    @Test
    public void testCargo() {
        assertEquals("cargos", Cargo.cargos.name());
    }

    @Test
    public void testToStringCargo() {assertEquals("cargos", Cargo.cargos.toString());}

}
