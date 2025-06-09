package dev.desafiocdc.controllers;

import dev.desafiocdc.dtos.AutorRequestDTO;
import dev.desafiocdc.dtos.AutorResponseDTO;
import dev.desafiocdc.services.AutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping
    public AutorResponseDTO cadastrarAutor(AutorRequestDTO autor) {
        log.debug("controller cadastrarAutor: {}", autor);
        return service.cadastrarAutor(autor);
    }
}
