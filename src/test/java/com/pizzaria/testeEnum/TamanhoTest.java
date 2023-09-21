package com.pizzaria.testeEnum;

import com.pizzaria.entity.Tamanho;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TamanhoTest {


    @Test
    void testNomeTamanhoPequena() {
        assertEquals("PEQUENA", Tamanho.PEQUENA.name());
    }

    @Test
    void testNomeTamanhoMedia() {
        assertEquals("MEDIA", Tamanho.MEDIA.name());
    }

    @Test
    void testNomeTamanhoGrande() {
        assertEquals("GRANDE", Tamanho.GRANDE.name());
    }

    @Test
    void testNomeTamanhoGigante() {
        assertEquals("GIGANTE", Tamanho.GIGANTE.name());
    }

    @Test
    void testToStringTamanhoPequena() {
        assertEquals("PEQUENA", Tamanho.PEQUENA.toString());
    }

    @Test
    void testToStringTamanhoMedia() {
        assertEquals("MEDIA", Tamanho.MEDIA.toString());
    }

    @Test
    void testToStringTamanhoGrande() {
        assertEquals("GRANDE", Tamanho.GRANDE.toString());
    }

    @Test
    void testToStringTamanhoGigante() {
        assertEquals("GIGANTE", Tamanho.GIGANTE.toString());
    }


}
