package dev.desafiocdc.dtos;

import dev.desafiocdc.client.categoria.entities.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome
) {

    public Categoria toModel() {
        var categoria = new Categoria();
        categoria.setNome(nome);
        return categoria;
    }
}
