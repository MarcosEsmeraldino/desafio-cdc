package dev.desafiocdc.dtos;

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
) { }