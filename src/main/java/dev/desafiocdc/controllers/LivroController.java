package dev.desafiocdc.controllers;

import dev.desafiocdc.dtos.LivroRequestDTO;
import dev.desafiocdc.dtos.LivroResponseDTO;
import dev.desafiocdc.services.LivroService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping
    public LivroResponseDTO cadastrarLivro(@Valid @RequestBody LivroRequestDTO request) {
        log.debug("controller cadastrarLivro: {}", request);
        return service.cadastrarLivro(request);
    }

    @GetMapping
    public List<LivroResponseDTO> listarLivros() {
        return service.listarLivros();
    }
}
