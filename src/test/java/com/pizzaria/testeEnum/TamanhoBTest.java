package com.pizzaria.testeEnum;

import com.pizzaria.entity.TamanhoB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//    L_2, L_1, ML_500, ML_300;
class TamanhoBTest {


    @Test
    void testNomeTamanho2Litros() {
        assertEquals("L_2", TamanhoB.L_2.name());
    }

    @Test
    void testNomeTamanho1Litro() {
        assertEquals("L_1", TamanhoB.L_1.name());
    }

    @Test
    void testNomeTamanho500ML() {
        assertEquals("ML_500", TamanhoB.ML_500.name());
    }

    @Test
    void testNomeTamanho300ML() {
        assertEquals("ML_300", TamanhoB.ML_300.name());
    }

    @Test
    void testToStringTamanho2Litros() {
        assertEquals("L_2", TamanhoB.L_2.toString());
    }

    @Test
    void testToStringTamanho1Litro() {
        assertEquals("L_1", TamanhoB.L_1.toString());
    }

    @Test
    void testToStringTamanho500ML() {
        assertEquals("ML_500", TamanhoB.ML_500.toString());
    }

    @Test
    void testToStringTamanho300ML() {
        assertEquals("ML_300", TamanhoB.ML_300.toString());

    }
}
