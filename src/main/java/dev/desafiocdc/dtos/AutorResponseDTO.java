package dev.desafiocdc.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.desafiocdc.client.mongodb.entities.Autor;

import java.time.Instant;

public record AutorResponseDTO(
        String id,
        @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
        Instant instante,
        String nome,
        String email,
        String descricao
) {
    public static AutorResponseDTO from(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getCreatedAt(),
                autor.getNome(),
                autor.getEmail(),
                autor.getDescricao()
        );
    }
}
