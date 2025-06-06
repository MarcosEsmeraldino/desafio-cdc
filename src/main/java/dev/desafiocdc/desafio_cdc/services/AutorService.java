package dev.desafiocdc.desafio_cdc.services;

import dev.desafiocdc.desafio_cdc.client.autor.entity.Autor;
import dev.desafiocdc.desafio_cdc.client.autor.repository.AutorRepository;
import dev.desafiocdc.desafio_cdc.dtos.AutorRequestDTO;
import dev.desafiocdc.desafio_cdc.dtos.AutorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public AutorResponseDTO cadastrarAutor(AutorRequestDTO request) {
        var autor = toEntity(request);
        autor = autorRepository.save(autor);
        return toResponseDTO(autor);
    }

    private Autor toEntity(AutorRequestDTO dto) {
        var autor = new Autor();
        autor.setNome(dto.nome());
        autor.setEmail(dto.email());
        autor.setDescricao(dto.descricao());
        autor.setInstante(Instant.now());
        return autor;
    }

    private AutorResponseDTO toResponseDTO(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getInstante(),
                autor.getNome(),
                autor.getEmail(),
                autor.getDescricao()
        );
    }
}
