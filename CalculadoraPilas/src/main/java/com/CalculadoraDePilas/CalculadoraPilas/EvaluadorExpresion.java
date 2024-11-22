package com.CalculadoraDePilas.CalculadoraPilas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class EvaluadorExpresion {
    private final PilaT<Integer> pilaOperandos = new PilaT<>();
    private final PilaT<Character> pilaOperadores = new PilaT<>();
    private final Map<String, Integer> variables = new HashMap<>();

    public Map<String, Integer> getVariables() {
        return variables;
    }

    public void crearVariable(String nombre, int valorInicial) {
        validarNombreVariable(nombre);
        variables.put(nombre, valorInicial);
    }

    public int evaluarExpresion(String expresion) {
        StringBuilder variableBuffer = new StringBuilder();
        boolean esperandoOperando = true;
        int parentesisBalanceados = 0;

        for (int i = 0; i < expresion.length(); i++) {
            char simbolo = expresion.charAt(i);

            if (Character.isDigit(simbolo)) {
                if (!esperandoOperando) throw new IllegalArgumentException("Expresión mal formada.");
                pilaOperandos.push(Character.getNumericValue(simbolo));
                esperandoOperando = false;
            } else if (Character.isLetter(simbolo) || simbolo == '_') {
                variableBuffer.append(simbolo);
            } else if (simbolo == '@') {
                mostrarPilas();
            } else if (simbolo == '(') {
                parentesisBalanceados++;
                pilaOperadores.push(simbolo);
                esperandoOperando = true;
            } else if (simbolo == ')') {
                parentesisBalanceados--;
                if (parentesisBalanceados < 0) throw new IllegalArgumentException("Paréntesis desbalanceados.");
                procesarCierreParentesis();
            } else if ("+-*/".indexOf(simbolo) >= 0) {
                procesarOperador(simbolo, variableBuffer);
                esperandoOperando = true;
            } else if (!Character.isWhitespace(simbolo)) {
                throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
            }
        }

        if (variableBuffer.length() > 0) pushVariable(variableBuffer.toString());
        procesarRestantes();

        if (parentesisBalanceados != 0) throw new IllegalArgumentException("Paréntesis desbalanceados.");
        return pilaOperandos.pop();
    }

    private void procesarCierreParentesis() {
        while (!pilaOperadores.estaVacia() && pilaOperadores.peek() != '(') {
            aplicarOperador();
        }
        pilaOperadores.pop(); // Remover '('
    }

    private void procesarOperador(char simbolo, StringBuilder variableBuffer) {
        if (variableBuffer.length() > 0) {
            pushVariable(variableBuffer.toString());
            variableBuffer.setLength(0);
        }
        while (!pilaOperadores.estaVacia() && precedencia(simbolo) <= precedencia(pilaOperadores.peek())) {
            aplicarOperador();
        }
        pilaOperadores.push(simbolo);
    }

    private void procesarRestantes() {
        while (!pilaOperadores.estaVacia()) {
            aplicarOperador();
        }
    }

    private void pushVariable(String nombre) {
        if (!variables.containsKey(nombre)) {
            throw new IllegalArgumentException("Variable no definida: " + nombre);
        }
        pilaOperandos.push(variables.get(nombre));
    }

    private void aplicarOperador() {
        char operador = pilaOperadores.pop();
        int b = pilaOperandos.pop();
        int a = pilaOperandos.pop();

        switch (operador) {
            case '+': pilaOperandos.push(a + b); break;
            case '-': pilaOperandos.push(a - b); break;
            case '*': pilaOperandos.push(a * b); break;
            case '/':
                if (b == 0) throw new ArithmeticException("División por cero.");
                pilaOperandos.push(a / b);
                break;
            default: throw new IllegalArgumentException("Operador desconocido: " + operador);
        }
    }

    private void mostrarPilas() {
        System.out.println("Pila de operandos: " + pilaOperandos);
        System.out.println("Pila de operadores: " + pilaOperadores);
    }

    private int precedencia(char operador) {
        if (operador == '+' || operador == '-') return 1;
        if (operador == '*' || operador == '/') return 2;
        return 0;
    }

    private void validarNombreVariable(String nombre) {
        if (nombre.length() > 12 || !nombre.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            throw new IllegalArgumentException("El nombre de la variable debe iniciar con una letra y tener máximo 12 caracteres alfanuméricos.");
        }
    }

    public List<Integer> getPilaOperandos() {
        return pilaOperandos.getElements(); // Retorna la pila como lista
    }

    public List<Character> getPilaOperadores() {
        return pilaOperadores.getElements(); // Retorna la pila como lista
    }
}
