package com.CalculadoraDePilas.CalculadoraPilas.controllers;

import com.CalculadoraDePilas.CalculadoraPilas.EvaluadorExpresion;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ControllerOperaciones {
    private EvaluadorExpresion evaluador = new EvaluadorExpresion();

    @PostMapping("/crearVariable")
    public Map<String, String> crearVariable(@RequestBody Map<String, String> payload) {
        Map<String, String> response = new HashMap<>();
        try {
            String nombre = payload.get("nombre");
            int valor = Integer.parseInt(payload.get("valor"));

            evaluador.crearVariable(nombre, valor);
            response.put("mensaje", "Variable creada correctamente.");
        } catch (Exception e) {
            response.put("error", "Error al crear variable: " + e.getMessage());
        }
        return response;
    }

    @PostMapping("/evaluar")
    public Map<String, Object> evaluar(@RequestBody Map<String, String> payload) {
        Map<String, Object> response = new HashMap<>();
        try {
            String expresion = payload.get("expresion");
            int resultado = evaluador.evaluarExpresion(expresion);

            response.put("resultado", resultado);
            response.put("pilaOperandos", evaluador.getPilaOperandos()); // Revisión de operandos
            response.put("pilaOperadores", evaluador.getPilaOperadores());
        } catch (Exception e) {
            response.put("error", "Error al evaluar la expresión: " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/variables")
    public Map<String, Integer> obtenerVariables() {
        return evaluador.getVariables();
    }
}
