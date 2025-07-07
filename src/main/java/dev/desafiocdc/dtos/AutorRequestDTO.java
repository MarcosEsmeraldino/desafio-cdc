package dev.desafiocdc.dtos;

import dev.desafiocdc.client.mongodb.entities.Autor;
import dev.desafiocdc.validations.UniqueValue;
import jakarta.validation.constraints.*;

public record AutorRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @UniqueValue(domainClass = Autor.class, fieldName = "email", message = "E-mail já cadastrado")
        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(max = 400, message = "Descrição deve ter no máximo 400 caracteres")
        String descricao
) {
        public Autor toModel() {
                var autor = new Autor();
                autor.setNome(nome);
                autor.setEmail(email);
                autor.setDescricao(descricao);
                return autor;
        }
}