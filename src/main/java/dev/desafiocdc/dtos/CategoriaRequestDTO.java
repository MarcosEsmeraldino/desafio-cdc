package dev.desafiocdc.dtos;

import dev.desafiocdc.client.mongodb.entities.Categoria;
import dev.desafiocdc.validations.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Nome já cadastrado")
        @NotBlank(message = "Nome é obrigatório")
        String nome
) {

    public Categoria toModel() {
        var categoria = new Categoria();
        categoria.setNome(nome);
        return categoria;
    }
}
