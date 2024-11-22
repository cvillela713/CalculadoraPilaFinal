package com.CalculadoraDePilas.CalculadoraPilas.controllers;

import com.CalculadoraDePilas.CalculadoraPilas.EvaluadorExpresion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControllerOperaciones.class)
public class ControllerOperacionesTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCrearVariable() throws Exception {
        mockMvc.perform(post("/api/crearVariable")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"x\", \"valor\":\"10\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Variable creada correctamente."));
    }

    @Test
    public void testCrearVariableInvalida() throws Exception {
        mockMvc.perform(post("/api/crearVariable")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"1x\", \"valor\":\"10\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    public void testEvaluarExpresion() throws Exception {
        mockMvc.perform(post("/api/evaluar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"expresion\":\"3+5\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").value(8));
    }
}

