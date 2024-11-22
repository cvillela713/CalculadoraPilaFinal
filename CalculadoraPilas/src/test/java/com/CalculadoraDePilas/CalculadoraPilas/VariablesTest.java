package com.CalculadoraDePilas.CalculadoraPilas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariablesTest {

    @Test
    void testConstructor() {
        Variables variable = new Variables("x", 10);

        assertEquals("x", variable.getNombre());
        assertEquals(10, variable.getValor());
    }

    @Test
    void testSetValor() {
        Variables variable = new Variables("y", 5);
        variable.setValor(15);

        assertEquals(15, variable.getValor());
    }

    @Test
    void testToString() {
        Variables variable = new Variables("z", 20);

        assertEquals("z=20", variable.toString());
    }
}
