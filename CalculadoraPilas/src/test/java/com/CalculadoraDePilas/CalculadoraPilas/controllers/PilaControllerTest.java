package com.CalculadoraDePilas.CalculadoraPilas.controllers;

import com.CalculadoraDePilas.CalculadoraPilas.PilaT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PilaController.class)
class PilaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPila() throws Exception {
        mockMvc.perform(get("/api/pila"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", containsInAnyOrder("Elemento 1", "Elemento 2", "Elemento 3")));
    }
}
