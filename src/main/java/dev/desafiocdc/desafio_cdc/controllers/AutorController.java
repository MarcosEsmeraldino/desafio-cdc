package dev.desafiocdc.desafio_cdc.controllers;

import dev.desafiocdc.desafio_cdc.dtos.AutorRequestDTO;
import dev.desafiocdc.desafio_cdc.dtos.AutorResponseDTO;
import dev.desafiocdc.desafio_cdc.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping
    public AutorResponseDTO cadastrarAutor(AutorRequestDTO autor) {
        return service.cadastrarAutor(autor);
    }
}
