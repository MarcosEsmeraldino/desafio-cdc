package dev.desafiocdc.services;

import dev.desafiocdc.client.mongodb.repositories.AutorRepository;
import dev.desafiocdc.dtos.AutorRequestDTO;
import dev.desafiocdc.dtos.AutorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public AutorResponseDTO cadastrarAutor(AutorRequestDTO request) {
        log.debug("service cadastrarAutor: {}", request);

        var autor = request.toModel();
        autor = autorRepository.save(autor);

        return AutorResponseDTO.from(autor);
    }
}
