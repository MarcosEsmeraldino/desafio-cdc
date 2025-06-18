package dev.desafiocdc.dtos;

import dev.desafiocdc.client.autor.entities.Autor;

import java.time.Instant;

public record AutorResponseDTO(
        String id,
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
