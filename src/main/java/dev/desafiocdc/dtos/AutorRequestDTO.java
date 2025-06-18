package dev.desafiocdc.dtos;

import dev.desafiocdc.client.autor.entities.Autor;
import jakarta.validation.constraints.*;

public record AutorRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato de email inválido")
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