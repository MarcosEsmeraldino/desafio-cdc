package dev.desafiocdc.services;

import dev.desafiocdc.client.mongodb.repositories.CategoriaRepository;
import dev.desafiocdc.dtos.CategoriaRequestDTO;
import dev.desafiocdc.dtos.CategoriaResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaResponseDTO cadastrarCategoria(CategoriaRequestDTO request) {
        log.debug("service cadastrarCategoria: {}", request);

        var categoria = request.toModel();
        categoria = categoriaRepository.save(categoria);

        return CategoriaResponseDTO.from(categoria);
    }
}
