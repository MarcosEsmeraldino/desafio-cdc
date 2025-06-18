package dev.desafiocdc.services;

import dev.desafiocdc.client.autor.entities.Autor;
import dev.desafiocdc.client.autor.repositories.AutorRepository;
import dev.desafiocdc.dtos.AutorRequestDTO;
import dev.desafiocdc.dtos.AutorResponseDTO;
import dev.desafiocdc.handler.exceptions.EmailDuplicadoException;
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

        if (autorRepository.existsByEmail(request.email())) {
            throw new EmailDuplicadoException("E-mail já cadastrado");
        }

        var autor = request.toModel();
        autor = autorRepository.insert(autor);

        return AutorResponseDTO.from(autor);
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
