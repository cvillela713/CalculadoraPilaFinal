package com.CalculadoraDePilas.CalculadoraPilas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PilaTTest {

    @Test
    public void testPushAndPop() {
        PilaT<Integer> pila = new PilaT<>();
        pila.push(10);
        pila.push(20);

        assertEquals(20, pila.pop(), "El último elemento no coincide.");
        assertEquals(10, pila.pop(), "El penúltimo elemento no coincide.");
    }

    @Test
    public void testPeek() {
        PilaT<String> pila = new PilaT<>();
        pila.push("Hola");
        pila.push("Mundo");

        assertEquals("Mundo", pila.peek(), "El elemento superior no coincide.");
        assertEquals("Mundo", pila.peek(), "El elemento superior debería seguir siendo el mismo.");
    }

    @Test
    public void testEstaVacia() {
        PilaT<Integer> pila = new PilaT<>();
        assertTrue(pila.estaVacia(), "La pila debería estar vacía al inicio.");

        pila.push(5);
        assertFalse(pila.estaVacia(), "La pila no debería estar vacía después de insertar un elemento.");
    }

    @Test
    public void testPopEnPilaVacia() {
        PilaT<Integer> pila = new PilaT<>();
        Exception exception = assertThrows(IllegalStateException.class, pila::pop);
        assertTrue(exception.getMessage().contains("La pila está vacía"));
    }
}
