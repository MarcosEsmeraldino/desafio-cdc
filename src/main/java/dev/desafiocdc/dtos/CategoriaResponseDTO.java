package dev.desafiocdc.dtos;

import dev.desafiocdc.client.mongodb.entities.Categoria;

public record CategoriaResponseDTO(
        String id,
        String nome
) {

    public static CategoriaResponseDTO from(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }
}
