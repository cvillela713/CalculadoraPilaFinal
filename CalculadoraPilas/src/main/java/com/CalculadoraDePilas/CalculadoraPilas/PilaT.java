package com.CalculadoraDePilas.CalculadoraPilas;

import java.util.ArrayList;
import java.util.List;

public class PilaT<T> {
    private final List<T> elementos = new ArrayList<>();

    public void push(T elemento) {
        elementos.add(elemento);
    }

    public T pop() {
        if (!estaVacia()) {
            return elementos.remove(elementos.size() - 1);
        }
        throw new IllegalStateException("La pila está vacía");
    }

    public T peek() {
        if (!estaVacia()) {
            return elementos.get(elementos.size() - 1);
        }
        throw new IllegalStateException("La pila está vacía");
    }

    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    public List<T> getElements() {
        return new ArrayList<>(elementos);
    }

    @Override
    public String toString() {
        return elementos.toString();
    }
}
