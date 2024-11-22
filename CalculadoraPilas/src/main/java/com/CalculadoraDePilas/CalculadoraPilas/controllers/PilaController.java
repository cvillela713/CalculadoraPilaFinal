package com.CalculadoraDePilas.CalculadoraPilas.controllers;
import com.CalculadoraDePilas.CalculadoraPilas.PilaT;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PilaController {

    @GetMapping("/pila")
    public List<String> getPila() {
        PilaT<String> pila = new PilaT<>();
        pila.push("Elemento 1");
        pila.push("Elemento 2");
        pila.push("Elemento 3");

        return pila.getElements(); // Devolver los elementos como lista
    }
}
