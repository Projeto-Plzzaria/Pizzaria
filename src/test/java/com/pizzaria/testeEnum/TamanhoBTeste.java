package com.pizzaria.testeEnum;

import com.pizzaria.entity.TamanhoB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//    L_2, L_1, ML_500, ML_300;
public class TamanhoBTeste {


    @Test
    public void testNomeTamanho2Litros() {
        assertEquals("L_2", TamanhoB.L_2.name());
    }

    @Test
    public void testNomeTamanho1Litro() {
        assertEquals("L_1", TamanhoB.L_1.name());
    }

    @Test
    public void testNomeTamanho500ML() {
        assertEquals("ML_500", TamanhoB.ML_500.name());
    }

    @Test
    public void testNomeTamanho300ML() {
        assertEquals("ML_300", TamanhoB.ML_300.name());
    }

    @Test
    public void testToStringTamanho2Litros() {
        assertEquals("L_2", TamanhoB.L_2.toString());
    }

    @Test
    public void testToStringTamanho1Litro() {
        assertEquals("L_1", TamanhoB.L_1.toString());
    }

    @Test
    public void testToStringTamanho500ML() {
        assertEquals("ML_500", TamanhoB.ML_500.toString());
    }

    @Test
    public void testToStringTamanho300ML() {
        assertEquals("ML_300", TamanhoB.ML_300.toString());

    }
}
