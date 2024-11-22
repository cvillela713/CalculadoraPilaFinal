package com.CalculadoraDePilas.CalculadoraPilas;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluadorExpresionTest {

    @Test
    public void testEvaluarExpresionSimple() {
        EvaluadorExpresion evaluador = new EvaluadorExpresion();
        int resultado = evaluador.evaluarExpresion("3+5");
        assertEquals(8, resultado, "La suma básica falló.");
    }

    @Test
    public void testEvaluarExpresionCompleja() {
        EvaluadorExpresion evaluador = new EvaluadorExpresion();
        int resultado = evaluador.evaluarExpresion("(3+5)*2");
        assertEquals(16, resultado, "La expresión con paréntesis falló.");
    }

    @Test
    public void testEvaluarExpresionConDivision() {
        EvaluadorExpresion evaluador = new EvaluadorExpresion();
        int resultado = evaluador.evaluarExpresion("10/2");
        assertEquals(5, resultado, "La división falló.");
    }

    @Test
    public void testEvaluarExpresionConVariable() {
        EvaluadorExpresion evaluador = new EvaluadorExpresion();
        evaluador.crearVariable("x", 10);
        int resultado = evaluador.evaluarExpresion("x+5");
        assertEquals(15, resultado, "La evaluación con variable falló.");
    }

    @Test
    public void testCrearVariableNombreInvalido() {
        EvaluadorExpresion evaluador = new EvaluadorExpresion();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluador.crearVariable("1x", 10);
        });
        assertTrue(exception.getMessage().contains("El nombre de la variable"));
    }

    @Test
    public void testParentesisDesbalanceados() {
        EvaluadorExpresion evaluador = new EvaluadorExpresion();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluador.evaluarExpresion("(3+5");
        });
        assertTrue(exception.getMessage().contains("Paréntesis desbalanceados"));
    }

    @Test
    public void testDivisonPorCero() {
        EvaluadorExpresion evaluador = new EvaluadorExpresion();
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            evaluador.evaluarExpresion("10/0");
        });
        assertTrue(exception.getMessage().contains("División por cero"));
    }

    @Test
    public void testPilasDuranteEvaluacion() {
        EvaluadorExpresion evaluador = new EvaluadorExpresion();
        evaluador.evaluarExpresion("3+5");
        List<Integer> operandos = evaluador.getPilaOperandos();
        List<Character> operadores = evaluador.getPilaOperadores();

        assertTrue(operandos.isEmpty(), "La pila de operandos debería estar vacía después de evaluar.");
        assertTrue(operadores.isEmpty(), "La pila de operadores debería estar vacía después de evaluar.");
    }
}
