package dev.desafiocdc.dtos;

import java.time.Instant;

public record AutorResponseDTO(
        String id,
        Instant instante,
        String nome,
        String email,
        String descricao
) { }
