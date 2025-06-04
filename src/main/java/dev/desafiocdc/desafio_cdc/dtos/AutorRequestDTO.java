package dev.desafiocdc.desafio_cdc.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AutorRequestDTO(
        @NotBlank
        String nome,

        @NotNull
        @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        String email,

        @NotNull
        @Size(max = 400)
        String descricao
) { }