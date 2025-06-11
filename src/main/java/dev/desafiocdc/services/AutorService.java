package dev.desafiocdc.services;

import dev.desafiocdc.client.autor.entity.Autor;
import dev.desafiocdc.client.autor.repository.AutorRepository;
import dev.desafiocdc.dtos.AutorRequestDTO;
import dev.desafiocdc.dtos.AutorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public AutorResponseDTO cadastrarAutor(AutorRequestDTO request) {
        log.debug("service cadastrarAutor: {}", request);
        var autor = toEntity(request);
        autor = autorRepository.insert(autor);
        return toResponseDTO(autor);
    }

    private Autor toEntity(AutorRequestDTO dto) {
        var autor = new Autor();
        autor.setNome(dto.nome());
        autor.setEmail(dto.email());
        autor.setDescricao(dto.descricao());
        return autor;
    }

    private AutorResponseDTO toResponseDTO(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getCreatedAt(),
                autor.getNome(),
                autor.getEmail(),
                autor.getDescricao()
        );
    }
}
