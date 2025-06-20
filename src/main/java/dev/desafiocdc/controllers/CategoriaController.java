package dev.desafiocdc.controllers;

import dev.desafiocdc.dtos.CategoriaRequestDTO;
import dev.desafiocdc.dtos.CategoriaResponseDTO;
import dev.desafiocdc.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public CategoriaResponseDTO cadastrarCategoria(@RequestBody @Valid CategoriaRequestDTO request) {
        log.debug("controller cadastrarCategoria: {}", request);
        return service.cadastrarCategoria(request);
    }
}
